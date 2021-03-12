using System.Collections.Generic;
using System.Linq;

public class Command {
    private static readonly Queue<Command> CommandQueue = new Queue<Command>();
    private static bool _playingQueue = false;

    public virtual void AddToQueue() {
        CommandQueue.Enqueue(this);
        if (!_playingQueue)
            PlayFirstCommandFromQueue();
    }

    protected virtual void StartCommandExecution() {
        // list of everything that we have to do with this command (draw a card, play a card, play spell effect, etc...)
        // there are 2 options of timing : 
        // 1) use tween sequences and call CommandExecutionComplete in OnComplete()
        // 2) use coroutines (IEnumerator) and WaitFor... to introduce delays, call CommandExecutionComplete() in the end of coroutine
    }

    public static void CommandExecutionComplete() {
        if (CommandQueue.Count > 0)
            PlayFirstCommandFromQueue();
        else
            _playingQueue = false;
        /*
        if (TurnManager.Instance.whoseTurn != null)
            TurnManager.Instance.whoseTurn.HighlightPlayableCards();*/
    }

    private static void PlayFirstCommandFromQueue() {
        _playingQueue = true;
        CommandQueue.Dequeue().StartCommandExecution();
    }

    public static bool CardDrawPending() => CommandQueue.OfType<DrawACardCommand>().Any();
}