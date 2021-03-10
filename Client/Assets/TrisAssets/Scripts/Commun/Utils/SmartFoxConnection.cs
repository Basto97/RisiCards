using UnityEngine;
using Sfs2X;

public class SmartFoxConnection : MonoBehaviour
{
	private static SmartFoxConnection _mInstance; 
	private static SmartFox _sfs;
	
	public static SmartFox Connection {
		get {
			if (_mInstance == null) 
				_mInstance = new GameObject("SmartFoxConnection").AddComponent(typeof(SmartFoxConnection)) as SmartFoxConnection;
			return _sfs;
		}
		set {
			if (_mInstance == null) 
				_mInstance = new GameObject("SmartFoxConnection").AddComponent(typeof(SmartFoxConnection)) as SmartFoxConnection;
			_sfs = value;
		} 
	}
	
	public static bool IsInitialized => _sfs != null;
	
	private void OnApplicationQuit() { 
		if (_sfs.IsConnected) 
			_sfs.Disconnect();
	} 
}