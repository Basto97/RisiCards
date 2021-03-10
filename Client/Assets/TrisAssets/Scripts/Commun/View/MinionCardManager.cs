using UnityEngine;
using UnityEngine.UI;

public class MinionCardManager : MonoBehaviour {
    public Text nameText;
    public Text manaCost;
    public Text healthText;
    public Text attackText;
    public Text powersText;

    public void Init(MinionCardAsset mca) {
        nameText.text = mca.name;
        manaCost.text = mca.cost.ToString();
        healthText.text = mca.health.ToString();
        attackText.text = mca.attack.ToString();
        foreach (string a in mca.effects) {
            powersText.text += a + ',';
        }
    }
}