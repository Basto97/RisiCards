using UnityEngine;
using UnityEngine.UI;

public class RopeVisual : MonoBehaviour {
        public Slider slider;
        public Text timeText;
        public Text endTurnText;

        private float _timeRemaining;
        private float _timeMax;

        public void OnNewTurn(bool myTurn, float timeRemaining) {
                _timeMax = timeRemaining;
                _timeRemaining = timeRemaining;
                endTurnText.text = myTurn ? "Fin de tour" : "Tour de l'adversaire";
        }

        private void Update() {
                _timeRemaining -= Time.deltaTime;
                timeText.text = ((int) _timeRemaining).ToString();
                slider.value = _timeRemaining / _timeMax;
        }
}