using UnityEngine;
using UnityEngine.UI;

public class HeroPowerVisual : MonoBehaviour {

   public Text costText;
   public Image image;
   
   public void ApplyLookFromAsset(HeroPower playerStateHeroPower) {
      costText.text = playerStateHeroPower.cost.ToString();
      image.sprite = SpritesLoader.Get(playerStateHeroPower.name);
   }
}
