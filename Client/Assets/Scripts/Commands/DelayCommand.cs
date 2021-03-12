using DG.Tweening;

public class DelayCommand : Command 
{
    private readonly float _delay;

    public DelayCommand(float timeToWait) {
        _delay = timeToWait;    
    }

    protected override void StartCommandExecution()
    {
        Sequence s = DOTween.Sequence();
        s.PrependInterval(_delay);
        s.OnComplete(CommandExecutionComplete);
    }
}
