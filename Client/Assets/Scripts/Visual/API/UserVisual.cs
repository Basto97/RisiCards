using UnityEngine;

public class UserVisual : MonoBehaviour {
        
        public HeroVisual heroVisual;
        public PoolVisual poolVisual;
        public HeroPowerVisual heroPowerVisual;
        public DeckVisual deckVisual;
        
        public void Init(UserState user) {
                heroVisual.ApplyLookFromAsset(user.Hero);
                heroPowerVisual.ApplyLookFromAsset(user.HeroPower);
                poolVisual.UpdatePoolVisual(user.Pool);
                deckVisual.UpdateSize(user.DeckSize);
        }
}
