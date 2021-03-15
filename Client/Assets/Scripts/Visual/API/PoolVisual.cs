using UnityEngine;
using UnityEngine.UI;

public class PoolVisual : MonoBehaviour {
    
    private int _totalCrystals;
    private int _availableCrystals;
    
    public int TotalCrystals {
        set {
            if (value > crystals.Length)
                _totalCrystals = crystals.Length;
            else if (value < 0)
                _totalCrystals = 0;
            else
                _totalCrystals = value;
            for (int i = 0; i < crystals.Length; i++)
                if (i < _totalCrystals) {
                    if (crystals[i].color == Color.clear)
                        crystals[i].color = Color.gray;
                } else
                    crystals[i].color = Color.clear;
            progressText.text = $"{_availableCrystals.ToString()}/{_totalCrystals.ToString()}";
        }
    }
    
    public int AvailableCrystals {
        set {
            if (value > _totalCrystals)
                _availableCrystals = _totalCrystals;
            else if (value < 0)
                _availableCrystals = 0;
            else
                _availableCrystals = value;
            for (int i = 0; i < _totalCrystals; i++) 
                crystals[i].color = i < _availableCrystals ? Color.white : Color.gray;
            progressText.text = $"{_availableCrystals.ToString()}/{_totalCrystals.ToString()}";
        }
    }
    
    [SerializeField] private Image[] crystals;
    [SerializeField] private Text progressText;

    public void UpdatePoolVisual(Pool pool) {
        TotalCrystals = pool.totalMana;
        AvailableCrystals = pool.currentMana;
    }
}
