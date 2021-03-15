using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class LoginController : MonoBehaviour {

	public InputField nameInput;
	public Button loginButton;
	public Text errorText;

	private void Awake() => EnableLoginUI(true);
	
	public void OnLoginButtonClick() {
		EnableLoginUI(false);
		SFS.Connect(OnConnection, OnConnectionError);
	}
	
	private void OnConnection() => SFS.Login(nameInput.text, OnLogin, OnLoginError);
	private void OnConnectionError() {
		EnableLoginUI(true);
		errorText.text = "Connection failed; is the server running at all?";
	}
	
	private static void OnLogin() => SceneManager.LoadScene("Lobby");
	private void OnLoginError(string reason) {
		EnableLoginUI(false);
		errorText.text = "Login failed: " + reason;
	}

	private void EnableLoginUI(bool enable) {
		nameInput.interactable = enable;
		loginButton.interactable = enable;
		errorText.text = "";
	}
}