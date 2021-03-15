using System.Linq;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class LobbyController : MonoBehaviour {

    public Text loggedInText;
    public Transform gameListContent;

    public Text heroText;
    public Text cardsText;

    private void Awake() {
        loggedInText.text = "Logged in as " + SFS.MySelf.Name;
        SFS.OnRoomJoin(room => {
            if (!room.IsGame) {
                PopulateGamesList();
                return;
            }
            SFS.ResetEvents();
            SceneManager.LoadScene("Game");
        });
        SFS.OnRoomAdded(_ => PopulateGamesList());
        SFS.OnRoomRemoved(_ => PopulateGamesList());
        SFS.OnExtension("content", ContentLoader.Load);
        SFS.Req("content").SendToZone();
        SFS.SendJoinRoom("The Lobby");
    }
    
    public void OnLogoutButtonClick()  => SFS.Disconnect();
    public void OnGameItemClick(int roomId) => SFS.Req("join").Int("room", roomId).Str("hero",heroText.text).StrA("deck", cardsText.text.Split(',')).SendToZone();
    public void OnStartNewGameButtonClick() => SFS.Req("play").SendToZone();
    
    private void PopulateGamesList() {
        foreach (Transform child in gameListContent.transform) 
            Destroy(child.gameObject);
        foreach (var room in SFS.RoomManager.GetRoomList().Where(room => room.IsGame && !room.IsHidden && !room.IsPasswordProtected)) {
            GameListItem roomItem = Instantiate(Prefabs.Instance.gameListItem, gameListContent,false).GetComponent<GameListItem>();
            roomItem.nameLabel.text = room.Name;
            roomItem.button.onClick.AddListener(() => OnGameItemClick(room.Id));
        }
    }
}