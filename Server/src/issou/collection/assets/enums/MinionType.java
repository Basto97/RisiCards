package issou.collection.assets.enums;

public enum MinionType {
    PeupleElu,
    SansType,
    IslamoGauchiste;

    public static MinionType Get(int id){
        return switch (id) {
            case 1 -> PeupleElu;
            case 0 -> SansType;
            default -> IslamoGauchiste;
        };
    }
}
