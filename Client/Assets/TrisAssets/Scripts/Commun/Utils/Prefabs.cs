using UnityEngine;

public class Prefabs : MonoBehaviour {
    
    public static Prefabs Instance;
    
    public GameObject MinionCard;
    public GameObject SpellCard;

    private void Awake() {
        Instance = this;
    }
}
