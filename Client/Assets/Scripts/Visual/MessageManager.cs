using System;
using UnityEngine;
using System.Collections;
using UnityEngine.Serialization;
using UnityEngine.UI;

public class MessageManager : MonoBehaviour 
{
    public Text messageText;
    public GameObject messagePanel;

    public static MessageManager instance;

    private void Awake()
    {
        instance = this;
        messagePanel.SetActive(false);
    }

    public void ShowMessage(string message, float duration, Command com) => StartCoroutine(ShowMessageCoroutine(message, duration, com));

    private IEnumerator ShowMessageCoroutine(string message, float duration, Command com)
    {
        messageText.text = message;
        messagePanel.SetActive(true);
        yield return new WaitForSeconds(duration);
        messagePanel.SetActive(false);
        // TODO Command.CommandExecutionComplete();
    }
    
}
