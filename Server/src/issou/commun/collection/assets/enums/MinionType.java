package issou.commun.collection.assets.enums;

import org.json.JSONArray;
import org.json.JSONObject;

public enum MinionType {
    SansType,
    PeupleElu,
    IslamoGauchiste;

    private static int peupleEluID;
    private static int islamoGauchisteID;
    private static int sansTypeID;

    public static void load(JSONArray types){
        int i;
        for(i = 0; i < types.length() ; i++){
            JSONObject type = types.getJSONObject(i);
            switch (type.getString("name")) {
                case "PeupleElu" -> peupleEluID = i;
                case "IslamoGauchiste" -> islamoGauchisteID = i;
                case "SansType" -> sansTypeID = i;
            }
        }
    }

    public static MinionType Get(int id){
        if(id == peupleEluID)
            return PeupleElu;
        if(id == islamoGauchisteID)
            return IslamoGauchiste;
        if(id == sansTypeID)
            return SansType;
        System.err.println("Minion Type "+id+" not knowed.");
        return SansType;
    }
}