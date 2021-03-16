using UnityEngine;
using UnityEngine.UI;

public class MinionManager : MonoBehaviour {
    public CardManager previewManager;
    public Text healthText;
    public Text attackText;
    public Image creatureGraphicImage;
    public Image creatureGlowImage;

    
    public bool CanAttackNow {
        set => creatureGlowImage.enabled = value;
    }
    
    // TODO use Minion in argument
    public void ReadMinionFromAsset(Card card) {
        creatureGraphicImage.sprite = SpritesLoader.Get(card.Name);
        attackText.text = card.attack.ToString();
        healthText.text = card.health.ToString();
        if (previewManager != null) 
            previewManager.ReadCard(card);
    }	

    public void TakeDamage(int amount, int healthAfter) {
        if (amount <= 0) return;
        // DamageEffect.CreateDamageEffect(transform.position, amount);
        healthText.text = healthAfter.ToString();
    }
}
