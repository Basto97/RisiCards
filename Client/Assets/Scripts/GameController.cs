using UnityEngine;
using UnityEngine.SceneManagement;
using Sfs2X;
using Sfs2X.Core;
using Sfs2X.Entities.Data;

public class GameController : MonoBehaviour {

	private SmartFox _sfs;
	private bool _shuttingDown;
	private GameAPI _gameAPI;
	
	private void Awake() {
		Application.runInBackground = true;
		
		if (SmartFoxConnection.IsInitialized) {
			_sfs = SmartFoxConnection.Connection;
		} else {
			SceneManager.LoadScene("Login");
			return;
		}
		
		_gameAPI = FindObjectOfType<GameAPI>();
		_sfs.AddEventListener(SFSEvent.CONNECTION_LOST, evt => {
			_sfs.RemoveAllEventListeners();
			if (_shuttingDown) return;
			SceneManager.LoadScene("Login");
		});
		_sfs.AddEventListener(SFSEvent.EXTENSION_RESPONSE, OnExtensionResponse);
		SmartFoxConnection.Send("readyToStartGame");
	}
	private void Update() => _sfs?.ProcessEvents();
	private void OnApplicationQuit() => _shuttingDown = true;

	private void OnExtensionResponse(BaseEvent evt) {
		string cmd = (string)evt.Params["cmd"];
		switch (cmd) {
			case "startGame":
				_gameAPI.NewGame((SFSObject)evt.Params["params"]);
				break;
			case "draw":
				_gameAPI.Draw((SFSObject)evt.Params["params"]);
				break;
			case "newTurn":
				_gameAPI.NewTurn((SFSObject)evt.Params["params"]);
				break;
		}
	}

	public void OnLeaveGameButtonClick() => new LeaveGameCommand().AddToQueue();
}