package issou.collection.assets.enums;

public enum HeroPowerType {
    BouleDeFeu,
    BouleDeGlace;

    public static HeroPowerType Get(int id){
        return switch (id) {
            case 1 -> BouleDeFeu;
            case 2 -> BouleDeGlace;
            default -> BouleDeFeu;
        };
    }
}
