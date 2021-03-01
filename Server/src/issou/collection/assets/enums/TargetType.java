package issou.collection.assets.enums;

public enum TargetType {
    AllChars,
    AllHeros;

    public static TargetType Get(int id){
        return switch (id) {
            case 1 -> AllChars;
            case 2 -> AllHeros;
            default -> AllChars;
        };
    }
}
