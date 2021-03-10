using System;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using Sfs2X;
using Sfs2X.Core;
using Sfs2X.Entities;
using Sfs2X.Requests;

public class GameController : MonoBehaviour {

	public Animator chatPanelAnim;
	public ScrollRect chatScrollView;
	public Text chatText;
	public CanvasGroup chatControls;
	public Text stateText;
	public Button restartButton;

	private enum GameState {
		WaitingForPlayers = 0,
		Running,
		GameWon,
		GameLost,
		GameTie,
		GameDisrupted
	};

	private SmartFox _sfs;
	private bool _shuttingDown;
	private static readonly int PanelOpen = Animator.StringToHash("panelOpen");

	private void Awake() {
		Application.runInBackground = true;
		
		if (SmartFoxConnection.IsInitialized) {
			_sfs = SmartFoxConnection.Connection;
		} else {
			SceneManager.LoadScene("Login");
			return;
		}

		_sfs.AddEventListener(SFSEvent.CONNECTION_LOST, OnConnectionLost);
		_sfs.AddEventListener(SFSEvent.PUBLIC_MESSAGE, OnPublicMessage);
		_sfs.AddEventListener(SFSEvent.USER_ENTER_ROOM, OnUserEnterRoom);
		_sfs.AddEventListener(SFSEvent.USER_EXIT_ROOM, OnUserExitRoom);
		
		SetCurrentGameState(GameState.WaitingForPlayers);
	}
	
	private void Update() => _sfs?.ProcessEvents();
	private void OnApplicationQuit() => _shuttingDown = true;

	public void OnChatTabClick() => chatPanelAnim.SetBool(PanelOpen, !chatPanelAnim.GetBool(PanelOpen));

	public void OnSendMessageButtonClick() {
		InputField msgField = chatControls.GetComponentInChildren<InputField>();

		if (msgField.text == "") return;
		
		_sfs.Send(new PublicMessageRequest(msgField.text));
		
		msgField.text = "";
		msgField.ActivateInputField();
		msgField.Select();
	}

	public void OnSendMessageKeyPress() {
		if (Input.GetKeyDown(KeyCode.Return) || Input.GetKeyDown(KeyCode.KeypadEnter))
			OnSendMessageButtonClick();
	}

	public void OnRestartButtonClick() {
		
	}
	public void OnLeaveGameButtonClick() {
		_sfs.RemoveAllEventListeners();;
		_sfs.Send(new LeaveRoomRequest());
		SceneManager.LoadScene("Lobby");
	}
	
	public void SetPlayerTurnMessage(string turnMsg) => 	stateText.text = turnMsg;
	public void SetGameInterrupted() => 	SetCurrentGameState(GameState.GameDisrupted);
	public void SetStartGame() {
		PrintSystemMessage("Game started! May the best player win");
		SetCurrentGameState(GameState.Running);
		restartButton.interactable = false;
	}
	public void SetGameOver(string result) {
		switch (result)
		{
			case "win":
				SetCurrentGameState(GameState.GameWon);
				PrintSystemMessage("Game over!\nCongratulations, you won!");
				break;
			case "loss":
				SetCurrentGameState(GameState.GameLost);
				PrintSystemMessage("Game over!\nToo bad, you've lost!");
				break;
			default:
				SetCurrentGameState(GameState.GameTie);
				PrintSystemMessage("Game over!\nIt's a tie!");
				break;
		}
		restartButton.interactable = true;
	}


	// Private helper methods
	private void SetCurrentGameState(GameState state)
	{
		switch (state)
		{
			case GameState.WaitingForPlayers:
				stateText.text = "Waiting for your opponent";
				break;
			case GameState.Running:
				// Nothing to do; the state text is updated by the TrisGame instance
				break;
			case GameState.GameDisrupted:
				stateText.text = "Opponent disconnected; waiting for new player";
				break;
			case GameState.GameWon:
				stateText.text = "GAME OVER";
				stateText.text += "\nYou've lost!";
				break;
			case GameState.GameLost:
				stateText.text = "GAME OVER";
				stateText.text += "\nYou won!";
				break;
			case GameState.GameTie:
				stateText.text = "GAME OVER";
				stateText.text += "\nIt's a tie!";
				break;
			default:
				throw new ArgumentOutOfRangeException(nameof(state), state, null);
		}
	}
	private void PrintSystemMessage(string message) {
		chatText.text += "<color=#808080ff>" + message + "</color>\n";
		Canvas.ForceUpdateCanvases();
		chatScrollView.verticalNormalizedPosition = 0;
	}
	private void PrintUserMessage(User user, string message) {
		chatText.text += "<b>" + (user == _sfs.MySelf ? "You" : user.Name) + ":</b> " + message + "\n";
		Canvas.ForceUpdateCanvases();
		chatScrollView.verticalNormalizedPosition = 0;
	}
	
	// SmartFoxServer event listeners
	private void OnPublicMessage(BaseEvent evt) => PrintUserMessage((User) evt.Params["sender"], (string) evt.Params["message"]);
	private void OnUserEnterRoom(BaseEvent evt) => PrintSystemMessage("User " + ((User) evt.Params["user"]).Name + " entered the room");
	private void OnUserExitRoom(BaseEvent evt) => PrintSystemMessage("User " + ((User) evt.Params["user"]).Name + " left the room");
	private void OnConnectionLost(BaseEvent evt) {
		_sfs.RemoveAllEventListeners();
		if (_shuttingDown) return;
		SceneManager.LoadScene("Login");
	}
}