package issou.commun.collection.assets.enums;

import org.json.JSONArray;
import org.json.JSONObject;

public enum MinionType {
    SansType,
    PeupleElu,
    IslamoGauchiste;

    private static int peupleEluID;
    private static int islamoGauchisteID;

    public static void load(JSONArray types){
        for(int i = 0; i < types.length() ; i++){
            JSONObject type = types.getJSONObject(i);
            int id = type.getInt("id");
            switch (type.getString("name")) {
                case "PeupleElu" -> peupleEluID = id;
                case "IslamoGauchiste" -> islamoGauchisteID = id;
            }
        }
    }

    public static MinionType Get(int id){
        if(id == peupleEluID)
            return PeupleElu;
        if(id == islamoGauchisteID)
            return IslamoGauchiste;
        return SansType;
    }

    public static int nbTypes(){
        return 3;
    }
}