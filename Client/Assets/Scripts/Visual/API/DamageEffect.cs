using UnityEngine;
using System.Collections;
using UnityEngine.UI;
using UnityEngine.AddressableAssets;
using UnityEngine.Serialization;

public class DamageEffect : MonoBehaviour {
    
    public Sprite[] splashes;
    public Image damageImage;
    public CanvasGroup cg;
    public Text amountText;

    private void Awake() => damageImage.sprite = splashes[Random.Range(0, splashes.Length)];

    private IEnumerator ShowDamageEffect() {
        cg.alpha = 1f;
        yield return new WaitForSeconds(1f);
        while (cg.alpha > 0) {
            cg.alpha -= 0.05f;
            yield return new WaitForSeconds(0.05f);
        }
        Destroy(gameObject);
    }

    public static void CreateDamageEffect(Vector3 position, int amount) {
        GameObject newDamageEffect = Instantiate(Prefabs.Instance.damageEffect, position, Quaternion.identity) as GameObject;
        DamageEffect de = newDamageEffect.GetComponent<DamageEffect>();
        de.amountText.text = "-"+amount;
        de.StartCoroutine(de.ShowDamageEffect());
    }
}
