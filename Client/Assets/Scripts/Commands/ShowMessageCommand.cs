﻿public class ShowMessageCommand : Command {
    private readonly string _message;
    private readonly float _duration;

    public ShowMessageCommand(string message, float duration)
    {
        this._message = message;
        this._duration = duration;
    }

    protected override void StartCommandExecution()
    {
        // MessageManager.Instance.ShowMessage(_message, _duration, this);
    }
}
