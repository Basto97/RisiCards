using UnityEngine;

public class Prefabs : MonoBehaviour {
    
    public static Prefabs Instance;
    
    public GameObject minionCard;
    public GameObject spellCard;
    public GameObject damageEffect;

    private void Awake() => Instance = this;
}
