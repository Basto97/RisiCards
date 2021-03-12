using UnityEngine;
using UnityEngine.UI;

public class HeroVisual : MonoBehaviour {
    
    public Text healthText;
    public Image portraitImage;
    public Image portraitBackgroundImage;

    public void ApplyLookFromAsset(Hero newHero) {
        healthText.text = newHero.health.ToString();
        portraitImage.sprite = SpritesLoader.Get(newHero.name);
        // portraitBackgroundImage.sprite = hero.AvatarBGImage;
        // portraitBackgroundImage.color = hero.AvatarBGTint;
    }

    public void TakeDamage(int amount, int healthAfter) {
        if (amount <= 0) return;
        // TODO DamageEffect.CreateDamageEffect(transform.position, amount);
        healthText.text = healthAfter.ToString();
    }
}
