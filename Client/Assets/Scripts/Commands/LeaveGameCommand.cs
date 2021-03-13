using Sfs2X.Requests;
using UnityEngine.SceneManagement;

public class LeaveGameCommand : Command {
    
    protected override void StartCommandExecution() {
        StopCommands();
        CommandExecutionComplete();
        SmartFoxConnection.sfs.Send(new LeaveRoomRequest());
        SmartFoxConnection.sfs.RemoveAllEventListeners();
        SceneManager.LoadScene("Lobby");
    }
}
