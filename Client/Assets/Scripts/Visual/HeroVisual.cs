using UnityEngine;
using UnityEngine.UI;
using DG.Tweening;
using UnityEngine.Serialization;

public class HeroVisual : MonoBehaviour {

    public Hero hero;
    public Text healthText;
    public Image portraitImage;
    public Image portraitBackgroundImage;

    public void ApplyLookFromAsset()
    {
        healthText.text = hero.health.ToString();
        portraitImage.sprite = SpritesLoader.Get(hero.name);
        // portraitBackgroundImage.sprite = hero.AvatarBGImage;
        // portraitBackgroundImage.color = hero.AvatarBGTint;
    }

    public void TakeDamage(int amount, int healthAfter) {
        if (amount <= 0) return;
        // TODO DamageEffect.CreateDamageEffect(transform.position, amount);
        healthText.text = healthAfter.ToString();
    }
}
