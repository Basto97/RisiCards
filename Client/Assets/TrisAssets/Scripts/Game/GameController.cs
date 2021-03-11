using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using Sfs2X;
using Sfs2X.Core;
using Sfs2X.Entities.Data;
using Sfs2X.Requests;

public class GameController : MonoBehaviour {
	
	public Text stateText;
	public Button restartButton;

	public State gameState;
	
	private SmartFox _sfs;
	private bool _shuttingDown;

	

	private void Awake() {
		Application.runInBackground = true;
		
		if (SmartFoxConnection.IsInitialized) {
			_sfs = SmartFoxConnection.Connection;
		} else {
			SceneManager.LoadScene("Login");
			return;
		}

		_sfs.AddEventListener(SFSEvent.CONNECTION_LOST, evt => {
			_sfs.RemoveAllEventListeners();
			if (_shuttingDown) return;
			SceneManager.LoadScene("Login");
		});
		_sfs.AddEventListener(SFSEvent.EXTENSION_RESPONSE, OnExtensionResponse);
	}
	private void Update() => _sfs?.ProcessEvents();
	private void OnApplicationQuit() => _shuttingDown = true;

	private void OnExtensionResponse(BaseEvent evt) {
		string cmd = (string)evt.Params["cmd"];
		if (cmd == "startGame") {
			SFSObject privateState = (SFSObject)evt.Params["params"];
			gameState = new State(privateState);
		}
	}

	public void OnLeaveGameButtonClick() {
		_sfs.RemoveAllEventListeners();;
		_sfs.Send(new LeaveRoomRequest());
		SceneManager.LoadScene("Lobby");
	}
	
	public void SetPlayerTurnMessage(string turnMsg) => stateText.text = turnMsg;
	public void SetStartGame() => restartButton.interactable = false;
}