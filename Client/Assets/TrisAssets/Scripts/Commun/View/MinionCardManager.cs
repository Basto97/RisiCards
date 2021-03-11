using UnityEngine;
using UnityEngine.UI;

public class MinionCardManager : MonoBehaviour {
    public Text nameText;
    public Text manaCost;
    public Text healthText;
    public Text attackText;
    public Text powersText;

    public void Init(CardAsset ca) {
        nameText.text = ca.name;
        manaCost.text = ca.cost.ToString();
        healthText.text = ca.health.ToString();
        attackText.text = ca.attack.ToString();
        foreach (string a in ca.effects) {
            powersText.text += a + ',';
        }
    }

    public void Init(Card c) {
        nameText.text = c.name;
        manaCost.text = c.cost.ToString();
        
    }
}