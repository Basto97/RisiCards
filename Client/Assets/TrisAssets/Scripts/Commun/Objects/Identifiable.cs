namespace TrisAssets.Scripts.Logic {
    public abstract class Identifiable {
        private int ID { get;  }
        protected Identifiable(int id) {
            ID = id;
        }

        private static int _idClientSide = -10000;

        protected Identifiable() {
            ID = _idClientSide++;
        }
    }
}