using UnityEngine;
using UnityEngine.UI;

public class CardManager : MonoBehaviour {
    
    [SerializeField] private CardManager previewManager;
    [SerializeField] private Text nameText;
    [SerializeField] private Text costText;
    [SerializeField] private Text descriptionText;
    [SerializeField] private Text healthText;
    [SerializeField] private Text attackText;
    [SerializeField] private Text typeText;
    [SerializeField] private Image cardGraphicImage;
    [SerializeField] private Image cardFaceGlowImage;

    public Card Card { get; private set; }

    public bool CanBePlayed {
        set {
            cardFaceGlowImage.enabled = value;
            if (previewManager != null)
                previewManager.CanBePlayed = value;
        }
    }
    
    public void ReadCard(CardAsset ca) => ReadCard(new Card(ca));
    public void ReadCard(Card ca) {
        Card = ca;
        cardGraphicImage.sprite = SpritesLoader.Get(ca.Name);
        nameText.text = ca.Name;
        costText.text = ca.baseCost.ToString();
        descriptionText.text = "Description";
        if (ca.minion) {
            attackText.text = ca.attack.ToString();
            healthText.text = ca.health.ToString();
            typeText.text = ca.type;
        } else
            typeText.text = "Sort";
        if (previewManager != null)
            previewManager.ReadCard(ca);
    }
}