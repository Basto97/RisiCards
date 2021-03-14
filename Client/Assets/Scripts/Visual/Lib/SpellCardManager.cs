using UnityEngine;
using UnityEngine.UI;

public class SpellCardManager : MonoBehaviour {
    public Text nameText;
    public Text cost;
    public Text costModification;
    
    public Text specialText;
    public Text effectsText;
    
    public Text targetType;

    public void Init(CardAsset ca) {
        nameText.text = ca.name;
        cost.text = ca.cost.ToString();
        costModification.text = ('(' + 0 + ')').ToString();
        targetType.text = ca.targetType;
        foreach (string a in ca.effects) 
            effectsText.text += a + ',';
        foreach (int a in ca.special) 
            specialText.text += a + ',';
    }

    public void Init(Card c) {
        nameText.text = c.Name;
        cost.text = c.baseCost.ToString();
        costModification.text = ('(' + c.costModification + ')').ToString();
        targetType.text = c.targetType;
        foreach (string a in c.effects) 
            effectsText.text += a + ',';
        foreach (int a in c.special) 
            specialText.text += a + ',';
    }
}