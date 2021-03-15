using System;
using System.Collections.Generic;

public abstract class Command {
    
    private static readonly Queue<Command> CommandQueue = new Queue<Command>();
    private static bool _playingQueue;

    public static Action executionQueueComplete;

    public void AddToQueue() {
        CommandQueue.Enqueue(this);
        if (!_playingQueue)
            PlayFirstCommandFromQueue();
    }
    
    public static void CommandExecutionComplete() {
        if (CommandQueue.Count > 0) {
            PlayFirstCommandFromQueue();
            return;
        }
        _playingQueue = false;
        executionQueueComplete?.Invoke();
    }
    
    private static void PlayFirstCommandFromQueue() {
        _playingQueue = true;
        CommandQueue.Dequeue().StartCommandExecution();
    }
    
    protected abstract void StartCommandExecution();
    
    protected static void CancelCommands() {
        CommandQueue.Clear();
        CommandExecutionComplete();
    }
}