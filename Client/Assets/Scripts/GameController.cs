using UnityEngine;
using UnityEngine.SceneManagement;
using Sfs2X;
using Sfs2X.Core;
using Sfs2X.Entities.Data;

public class GameController : MonoBehaviour {
	
	private GameAPI _gameAPI;

	private void Awake() {
		_gameAPI = GetComponent<GameAPI>();
		/*
		_sfs.AddEventListener(SFSEvent.EXTENSION_RESPONSE, OnExtensionResponse);
	
		SmartFoxConnection.Send("readyToStartGame");
		*/
	}


	private void OnExtensionResponse(BaseEvent evt) {
		string cmd = (string)evt.Params["cmd"];
		switch (cmd) {
			case "startGame":
				_gameAPI.NewGame((SFSObject)evt.Params["params"]);
				break;
			case "draw":
				_gameAPI.Draw((SFSObject)evt.Params["params"]);
				break;
			case "opponantDraw":
				_gameAPI.OpponantDraw((SFSObject)evt.Params["params"]);
				break;
			case "newTurn":
				_gameAPI.NewTurn((SFSObject)evt.Params["params"]);
				break;
		}
	}

	public void OnLeaveGameButtonClick() => new LeaveGameCommand().AddToQueue();
}