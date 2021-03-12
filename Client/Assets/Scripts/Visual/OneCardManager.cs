using UnityEngine;
using UnityEngine.UI;

public class OneCardManager : MonoBehaviour {

    public Card card;
    public OneCardManager previewManager;
    public Text nameText;
    public Text costText;
    public Text descriptionText;
    public Text healthText;
    public Text attackText;
    public Text typeText;
    public Image cardGraphicImage;
    public Image cardFaceGlowImage;

    private void Awake() {
        if (card != null)
            ReadCardFromAsset();
    }

    private bool _canBePlayedNow;
    public bool CanBePlayedNow {
        get => _canBePlayedNow;
        set {
            _canBePlayedNow = value;
            cardFaceGlowImage.enabled = value;
        }
    }

    public void ReadCardFromAsset() {
        nameText.text = card.name;
        costText.text = card.cost.ToString();
        descriptionText.text = "Description";
        cardGraphicImage.sprite = SpritesLoader.Get(card.name);

        if (card.minion) {
            typeText.text = card.type;
            attackText.text = card.attack.ToString();
            healthText.text = card.health.ToString();
        }
        else
            typeText.text = "Sort";
        if (previewManager == null) return;
        previewManager.card = card;
        previewManager.ReadCardFromAsset();
    }
}