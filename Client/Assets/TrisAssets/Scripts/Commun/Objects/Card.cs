namespace TrisAssets.Scripts.Logic {
    public class Card : Identifiable {

        private string Name { get;  }
        private int ManaCost { get; }
        
        public Card(int id, string name, int manaCost) : base(id) {
            Name = name;
            ManaCost = manaCost;
        }

        public Card(string name, int manaCost) {
            Name = name;
            ManaCost = manaCost;
        }
        
    }
}