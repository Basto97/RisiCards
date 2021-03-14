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
    

    private bool _canBePlayedNow;
    public bool CanBePlayedNow {
        get => _canBePlayedNow;
        set {
            _canBePlayedNow = value;
            cardFaceGlowImage.enabled = value;
            if (previewManager != null)
                previewManager.CanBePlayedNow = value;
        }
    }

    public void ReadCardFromAsset(Card newCard) {
        this.card = newCard;
        nameText.text = newCard.Name;
        costText.text = newCard.baseCost.ToString();
        descriptionText.text = "Description";
        cardGraphicImage.sprite = SpritesLoader.Get(newCard.Name);

        if (newCard.minion) {
            typeText.text = newCard.type;
            attackText.text = newCard.attack.ToString();
            healthText.text = newCard.health.ToString();
        }
        else
            typeText.text = "Sort";
        if (previewManager == null) return;
        previewManager.ReadCardFromAsset(newCard);
    }
}