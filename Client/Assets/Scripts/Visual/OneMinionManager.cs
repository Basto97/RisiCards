﻿using UnityEngine;
using UnityEngine.Serialization;
using UnityEngine.UI;

public class OneMinionManager : MonoBehaviour 
{
    public Card card;
    public OneCardManager previewManager;
    public Text healthText;
    public Text attackText;
    public Image creatureGraphicImage;
    public Image creatureGlowImage;

    private void Awake() {
        if (card != null)
            ReadMinionFromAsset();
    }

    private bool _canAttackNow = false;
    public bool CanAttackNow
    {
        private get => _canAttackNow;
        set {
            _canAttackNow = value;
            creatureGlowImage.enabled = value;
        }
    }

    public void ReadMinionFromAsset() {
        creatureGraphicImage.sprite = SpritesLoader.Get(card.name);
        attackText.text = card.attack.ToString();
        healthText.text = card.health.ToString();
        if (previewManager == null) return;
        previewManager.card = card;
        previewManager.ReadCardFromAsset();
    }	

    public void TakeDamage(int amount, int healthAfter) {
        if (amount <= 0) return;
        // DamageEffect.CreateDamageEffect(transform.position, amount);
        healthText.text = healthAfter.ToString();
    }
}
