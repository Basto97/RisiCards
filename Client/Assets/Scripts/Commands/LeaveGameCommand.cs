using Sfs2X.Requests;
using UnityEngine.SceneManagement;

public class LeaveGameCommand : Command {
    
    protected override void StartCommandExecution() {
        StopCommands();
        CommandExecutionComplete();
        // SmartFoxConnection.Send(new LeaveRoomRequest());
        // SmartFoxConnection.RemoveAllEventListeners();
        SceneManager.LoadScene("Lobby");
    }
}
