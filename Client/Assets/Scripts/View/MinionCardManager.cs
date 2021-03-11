using UnityEngine;
using UnityEngine.UI;

public class MinionCardManager : MonoBehaviour {
    public Text nameText;
    public Text cost;
    public Text costModification;
    
    public Text specialText;
    public Text effectsText;
    
    public Text healthText;
    public Text healthModificationText;
    public Text attackText;
    public Text attackModificationText;
    public Text type;

    public void Init(CardAsset ca) {
        nameText.text = ca.name;
        cost.text = ca.cost.ToString();
        costModification.text = "(" + 0 + ")";
        healthText.text = ca.health.ToString();
        healthModificationText.text =  "(" + 0 + ")";
        attackModificationText.text =  "(" + 0 + ")";
        type.text = ca.type;
        attackText.text = ca.attack.ToString();
        foreach (string a in ca.effects) 
            effectsText.text += a + ',';
        foreach (int a in ca.special) 
            specialText.text += a + ',';
    }

    public void Init(Card c) {
        nameText.text = c.name;
        cost.text = c.cost.ToString();
        costModification.text ="(" + c.costModification + ")";
        healthText.text = c.health.ToString();
        healthModificationText.text ="(" + c.healthModification + ")";
        attackModificationText.text ="(" + c.attackModification + ")";
        type.text = c.type;
        attackText.text = c.attack.ToString();
        effectsText.text = "";
        specialText.text = "";
        foreach (string a in c.effects) 
            effectsText.text += a + ',';
        foreach (int a in c.special) 
            specialText.text += a + ',';
    }
}