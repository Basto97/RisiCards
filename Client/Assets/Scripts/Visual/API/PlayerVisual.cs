using UnityEngine;

public class PlayerVisual : MonoBehaviour {
        
        public HeroVisual heroVisual;
        public PoolVisual poolVisual;
        public HandVisual handVisual;
        public HeroPowerVisual heroPowerVisual;
        public DeckVisual deckVisual;

        public void Init(Player player) {
                heroVisual.ApplyLookFromAsset(player.Hero);
                heroPowerVisual.ApplyLookFromAsset(player.HeroPower);
                poolVisual.TotalCrystals = player.Pool.totalMana;
                poolVisual.AvailableCrystals = poolVisual.TotalCrystals;
        }

        public void Init(Opponant opponant) {
                heroVisual.ApplyLookFromAsset(opponant.Hero);
                heroPowerVisual.ApplyLookFromAsset(opponant.HeroPower);
                poolVisual.TotalCrystals = opponant.Pool.totalMana;
                poolVisual.AvailableCrystals = poolVisual.TotalCrystals;
        }
}
