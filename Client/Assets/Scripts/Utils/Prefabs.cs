using UnityEngine;

public class Prefabs : MonoBehaviour {
    
    public static Prefabs Instance;
    
    private void Awake() => Instance = this;
    
    public GameObject minionCard;
    public GameObject spellCard;
    public GameObject damageEffect;
    public GameObject cardBack;

    // Lobby
    public GameObject gameListItem;
    
}