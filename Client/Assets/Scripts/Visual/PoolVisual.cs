using UnityEngine;
using UnityEngine.UI;

public class PoolVisual : MonoBehaviour {
    
    private int _totalCrystals;
    public int TotalCrystals {
        get => _totalCrystals;
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

    private int _availableCrystals;
    public int AvailableCrystals {
        get => _availableCrystals;
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
    
    public Image[] crystals;
    public Text progressText;

}
