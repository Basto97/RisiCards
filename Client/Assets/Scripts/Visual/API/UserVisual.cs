using UnityEngine;

public class UserVisual : MonoBehaviour {
        
        public HeroVisual heroVisual;
        public PoolVisual poolVisual;
        public HandVisual handVisual;
        public HeroPowerVisual heroPowerVisual;
        public DeckVisual deckVisual;

        public void Init(UserState user, bool isPlayer) {
                heroVisual.ApplyLookFromAsset(user.Hero);
                heroPowerVisual.ApplyLookFromAsset(user.HeroPower);
                poolVisual.UpdatePoolVisual(user.Pool);
                handVisual.Init(isPlayer);
        }
}
