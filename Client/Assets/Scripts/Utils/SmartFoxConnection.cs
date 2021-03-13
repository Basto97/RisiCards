using UnityEngine;
using Sfs2X;
using Sfs2X.Entities.Data;
using Sfs2X.Entities.Managers;
using Sfs2X.Requests;

public class SmartFoxConnection : MonoBehaviour
{
	private static SmartFoxConnection _mInstance; 
	public static SmartFox sfs;
	
	public static SmartFox Connection {
		get {
			if (_mInstance == null) 
				_mInstance = new GameObject("SmartFoxConnection").AddComponent(typeof(SmartFoxConnection)) as SmartFoxConnection;
			return sfs;
		}
		set {
			if (_mInstance == null) 
				_mInstance = new GameObject("SmartFoxConnection").AddComponent(typeof(SmartFoxConnection)) as SmartFoxConnection;
			sfs = value;
		} 
	}
	
	public static bool IsInitialized => sfs != null;
	
	private void OnApplicationQuit() { 
		if (sfs.IsConnected) 
			sfs.Disconnect();
	}

	public static void Send(string cmd) {
		sfs.Send(new ExtensionRequest(cmd, new SFSObject(), sfs.LastJoinedRoom));
	}
}