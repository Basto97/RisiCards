using System;
using System.Collections.Generic;
using UnityEngine;
using Sfs2X;
using Sfs2X.Entities;
using Sfs2X.Entities.Data;
using Sfs2X.Entities.Managers;
using Sfs2X.Requests;
using Sfs2X.Util;
using UnityEngine.SceneManagement;
using static Sfs2X.Core.SFSEvent;

public class SFS : MonoBehaviour {
    
    public string host = "127.0.0.1";
    public int port = 9933;
    public string zone = "Risicards";
    
    private static SFS _instance;
    private static SmartFox _sfs;
    
    private static bool _shuttingDown;
    private static bool _firstAwake = true;
    private static string _sceneToLoadWhenLooseConnection;
    
    private void Awake() {
        Application.runInBackground = true;
        _instance = this;
        _sceneToLoadWhenLooseConnection ??= SceneManager.GetActiveScene().name;
        if (!_firstAwake) return;
        DontDestroyOnLoad(gameObject);
        _firstAwake = false;
    }
    private void Update() => _sfs?.ProcessEvents();
    private void OnApplicationQuit() {
        _shuttingDown = true;
        if (_sfs != null && _sfs.IsConnected) 
            _sfs.Disconnect();
    }
    
    public static void ResetEvents() {
        _sfs.RemoveAllEventListeners();
        _sfs.AddEventListener(CONNECTION_LOST, baseEvent => {
            _sfs.RemoveAllEventListeners();
            if(!_shuttingDown) SceneManager.LoadScene(_sceneToLoadWhenLooseConnection);
        });
        actions.Clear();
        _sfs.AddEventListener(EXTENSION_RESPONSE, evt =>  actions[(string)evt.Params["cmd"]]?.Invoke((SFSObject)evt.Params["params"]));
    }
    
    
    // Connection and Login

    public static void Connect(Action connection, Action connectionError) {
        _sfs = new SmartFox();
        _sfs.Connect(new ConfigData {Host = _instance.host, Port=_instance.port, Zone = _instance.zone});
        _sfs.AddEventListener(CONNECTION, evt => {
            if ((bool) evt.Params["success"]) {
                ResetEvents();
                connection.Invoke();
            } else connectionError.Invoke();
        } );
    }
    
    public static void Disconnect() => _sfs.Disconnect();
    
    public static void Login(string username, Action login, Action<string> loginError) {
        _sfs.Send(new LoginRequest(username));
        _sfs.AddEventListener(LOGIN, evt => login.Invoke());
        _sfs.AddEventListener(LOGIN_ERROR, evt => loginError.Invoke((string) evt.Params["errorMessage"]));
    }
    
    public static User MySelf => _sfs.MySelf;
    public static IRoomManager RoomManager => _sfs.RoomManager;


    // EVENTS
    
    public static void OnRoomAdded(Action<Room> roomAdded) => 
        _sfs.AddEventListener(ROOM_ADD, evt => roomAdded.Invoke((Room)evt.Params["room"]));
    
    public static void OnRoomJoin(Action<Room> roomJoined) => 
        _sfs.AddEventListener(ROOM_JOIN, evt => roomJoined.Invoke((Room)evt.Params["room"]));
    
    public static void OnRoomRemoved(Action<Room> roomRemoved) => 
        _sfs.AddEventListener(ROOM_REMOVE, evt => roomRemoved.Invoke((Room)evt.Params["room"]));
    
    private static Dictionary<string, Action<ISFSObject>> actions = new Dictionary<string, Action<ISFSObject>>();
    public static void OnExtension(string cmd, Action<ISFSObject> response) => actions.Add(cmd, response);

    
    // SEND

    public static void SendJoinRoom(string roomNameOrId) => _sfs.Send(new JoinRoomRequest(roomNameOrId));
    public static void SendLeaveRoom() => _sfs.Send(new LeaveRoomRequest());
    public static ISFSend Req(string cmd) => new SFSend(cmd);
    
    // Stuff
    
    private class SFSend : ISFSend {
        private readonly ISFSObject _obj = new SFSObject();
        private readonly string _cmd;

        internal SFSend(string cmd) => _cmd = cmd;

        public void Send() => _sfs.Send(new ExtensionRequest(_cmd, _obj, _sfs.LastJoinedRoom));
        public void SendToZone() => _sfs.Send(new ExtensionRequest(_cmd, _obj));

        public ISFSend Int(string key, int value) {
            _obj.PutInt(key,value);
            return this;
        }

        public ISFSend Str(string key, string value) {
            _obj.PutUtfString(key, value);
            return this;
        }

        public ISFSend StrA(string key, string[] value) {
            _obj.PutUtfStringArray(key, value);
            return this;
        }
    }

    public interface ISFSend {
        void Send();
        void SendToZone();
        ISFSend Int(string key, int value);
        ISFSend Str(string key, string value);
        ISFSend StrA(string key, string[] value);
    }
}