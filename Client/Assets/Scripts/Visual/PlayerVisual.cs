using UnityEngine;

public class PlayerVisual : MonoBehaviour {
        public HandVisual handVisual;
        public HeroVisual heroVisual;
        public PoolVisual poolVisual;
        public HeroPowerVisual heroPowerVisual;

        public void Init(PlayerState playerState) {
                heroVisual.ApplyLookFromAsset(playerState.hero);

                heroPowerVisual.ApplyLookFromAsset(playerState.heroPower);
                
                poolVisual.TotalCrystals = playerState.manaPool.totalMana;
                poolVisual.AvailableCrystals = poolVisual.TotalCrystals;
        }
}
