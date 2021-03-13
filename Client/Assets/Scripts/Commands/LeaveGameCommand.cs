using Sfs2X.Requests;
using UnityEngine.SceneManagement;

public class LeaveGameCommand : Command {
    
    protected override void StartCommandExecution() {
        SmartFoxConnection.sfs.RemoveAllEventListeners();;
        SmartFoxConnection.sfs.Send(new LeaveRoomRequest());
        StopCommands();
        CommandExecutionComplete();
        SceneManager.LoadScene("Lobby");
    }
}
