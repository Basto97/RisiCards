public class GameOverCommand : Command{

    private Player _looser;

    public GameOverCommand(Player looser)
    {
        this._looser = looser;
    }

    protected override void StartCommandExecution()
    {
        // _looser.PArea.Portrait.Explode();
    }
}
