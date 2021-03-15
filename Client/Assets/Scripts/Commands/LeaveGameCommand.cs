using UnityEngine.SceneManagement;

public class LeaveGameCommand : Command {
    
    protected override void StartCommandExecution() {
        CancelCommands();
        SFS.SendLeaveRoom();
        SFS.ResetEvents();
        SceneManager.LoadScene("Lobby");
    }
}
