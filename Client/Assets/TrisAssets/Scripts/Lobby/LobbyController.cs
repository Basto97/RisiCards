using System.Linq;
using Sfs2X;
using Sfs2X.Core;
using Sfs2X.Entities;
using Sfs2X.Entities.Data;
using Sfs2X.Requests;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;
using static Sfs2X.Core.SFSEvent;

public class LobbyController : MonoBehaviour {

		public Text loggedInText;
		public Transform gameListContent;
		public GameObject gameListItem;
		
		public Text heroText;
		public Text cardsText;

		private const string ExtensionID = "Risicards";
		private const string ExtensionClass = "issou.sfs.RisicardsExtension";

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

			loggedInText.text = "Logged in as " + _sfs.MySelf.Name;
			
			_sfs.AddEventListener(CONNECTION_LOST, OnConnectionLost);
			_sfs.AddEventListener(ROOM_JOIN, OnRoomJoin);
			_sfs.AddEventListener(ROOM_ADD, OnRoomAdded);
			_sfs.AddEventListener(ROOM_REMOVE, OnRoomRemoved);
			_sfs.AddEventListener(EXTENSION_RESPONSE, OnExtensionResponse);
			_sfs.Send(new ExtensionRequest("content", new SFSObject()));
			PopulateGamesList();

			_sfs.Send(new JoinRoomRequest("The Lobby"));
		}

		private void Update() => _sfs?.ProcessEvents();
		private void OnApplicationQuit() => _shuttingDown = true;

		public void OnLogoutButtonClick()  => _sfs.Disconnect();
		public void OnGameItemClick(int roomId) => _sfs.Send(new JoinRoomRequest(roomId));
		public void OnStartNewGameButtonClick() {
			ISFSObject obj = new SFSObject();
			obj.PutUtfString("hero",heroText.text);
			obj.PutUtfStringArray("cards", cardsText.text.Split(','));
			_sfs.Send(new ExtensionRequest("play", obj));
		}
		
		private void OnExtensionResponse(BaseEvent evt) {
			string cmd = (string)evt.Params["cmd"];
			ISFSObject sfso = (ISFSObject)evt.Params["params"];
			if (cmd == "content")
				ContentLoader.Load(sfso);
			
		}
	
		private void OnConnectionLost(BaseEvent evt) {
			_sfs.RemoveAllEventListeners();
			if (_shuttingDown) return;
			SceneManager.LoadScene("Login");
		}
	
		private void OnRoomJoin(BaseEvent evt) {
			Room room = (Room) evt.Params["room"];
			if (!room.IsGame) return;
			_sfs.RemoveAllEventListeners();
			SceneManager.LoadScene("Game");
		}
		
		private void OnRoomAdded(BaseEvent evt) {
			if (!((Room) evt.Params["room"]).IsGame) return;
			ClearGamesList();
			PopulateGamesList();
		}
	
		public void OnRoomRemoved(BaseEvent evt) {
			ClearGamesList();
			PopulateGamesList();
		}
		
		private void PopulateGamesList() {
			foreach (var room in _sfs.RoomManager.GetRoomList().
				Where(room => room.IsGame && !room.IsHidden && !room.IsPasswordProtected)) {
				GameObject newListItem = Instantiate(gameListItem,gameListContent,false);
				GameListItem roomItem = newListItem.GetComponent<GameListItem>();
				roomItem.nameLabel.text = room.Name;
				roomItem.button.onClick.AddListener(() => OnGameItemClick(room.Id));
			}
		}

		private void ClearGamesList() {
			foreach (Transform child in gameListContent.transform) 
				Destroy(child.gameObject);
		}
}