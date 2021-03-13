using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class MessageManager : MonoBehaviour 
{
    public Text messageText;
    public GameObject messagePanel;

    public static MessageManager Instance;

    private void Awake()
    {
        Instance = this;
        messagePanel.SetActive(false);
    }

    public void ShowMessage(string message, float duration) => StartCoroutine(ShowMessageCoroutine(message, duration));

    private IEnumerator ShowMessageCoroutine(string message, float duration)
    {
        messageText.text = message;
        messagePanel.SetActive(true);
        yield return new WaitForSeconds(duration);
        messagePanel.SetActive(false);
        Command.CommandExecutionComplete();
    }
    
}
