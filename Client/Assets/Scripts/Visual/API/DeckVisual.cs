using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class DeckVisual : MonoBehaviour {
    public Text sizeText;
    public GameObject canvas;

    public void UpdateSize(int size) {
        sizeText.text = size.ToString();
        canvas.SetActive(size != 0);
    }
}
