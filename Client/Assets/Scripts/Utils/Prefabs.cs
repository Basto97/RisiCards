using UnityEngine;

public class Prefabs : MonoBehaviour {
    
    public static Prefabs Instance;
    
    public GameObject minionCard;
    public GameObject spellCard;
    public GameObject damageEffect;
    public GameObject cardBack;

    private void Awake() => Instance = this;
}