using UnityEngine;
using UnityEngine.UI;

public class RopeVisual : MonoBehaviour {
        
        [SerializeField] private Slider slider;
        [SerializeField] private Text timeText;
        [SerializeField] private Text endTurnText;

        private float _timeRemaining;
        private float _timeMax;

        public bool Counting { get; set; }
        
        public void OnNewTurn(bool myTurn, float timeRemaining) {
                _timeMax = timeRemaining;
                _timeRemaining = timeRemaining;
                endTurnText.text = myTurn ? "Fin de tour" : "Tour de l'adversaire";
        }

        private void Update() {
                if (!Counting) return;
                _timeRemaining -= Time.deltaTime;
                timeText.text = ((int) _timeRemaining).ToString();
                slider.value = _timeRemaining / _timeMax;
        }
}