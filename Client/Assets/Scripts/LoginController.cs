using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using Sfs2X;
using Sfs2X.Util;
using Sfs2X.Core;

public class LoginController : MonoBehaviour {
	
	public string host = "127.0.0.1";
	public int port = 9933;
	public string zone = "Risicards";

	public InputField nameInput;
	public Button loginButton;
	public Text errorText;

	private SmartFox _sfs;

	private void Awake() {
		SpritesLoader.Get("");
		Application.runInBackground = true;
		EnableLoginUI(true);
	}
	
	private void Update() => _sfs?.ProcessEvents();

	// ui
	
	public void OnLoginButtonClick() {
		EnableLoginUI(false);
		
		_sfs = new SmartFox();
		
		_sfs.AddEventListener(SFSEvent.CONNECTION, OnConnection);
		_sfs.AddEventListener(SFSEvent.CONNECTION_LOST, OnConnectionLost);
		_sfs.AddEventListener(SFSEvent.LOGIN, OnLogin);
		_sfs.AddEventListener(SFSEvent.LOGIN_ERROR, OnLoginError);
		
		_sfs.Connect(new ConfigData {Host = host, Port=port, Zone = zone});
	}
	
	// helpers

	private void EnableLoginUI(bool enable) {
		nameInput.interactable = enable;
		loginButton.interactable = enable;
		errorText.text = "";
	}
	
	private void Reset() {
		_sfs.RemoveAllEventListeners();
		EnableLoginUI(true);
	}
	
	// events

	private void OnConnection(BaseEvent evt) {
		if ((bool)evt.Params["success"]) {
			SmartFoxConnection.Connection = _sfs;
			_sfs.Send(new Sfs2X.Requests.LoginRequest(nameInput.text));
		} else {
			Reset();
			errorText.text = "Connection failed; is the server running at all?";
		}
	}
	
	private void OnConnectionLost(BaseEvent evt) {
		Reset();
		string reason = (string) evt.Params["reason"];
		if (reason != ClientDisconnectionReason.MANUAL) 
			errorText.text = "Connection was lost; reason is: " + reason;
	}
	
	private void OnLogin(BaseEvent evt) {
		Reset();
		SceneManager.LoadScene("Lobby");
	}
	
	private void OnLoginError(BaseEvent evt) {
		_sfs.Disconnect();
		Reset();
		errorText.text = "Login failed: " + (string) evt.Params["errorMessage"];
	}
}