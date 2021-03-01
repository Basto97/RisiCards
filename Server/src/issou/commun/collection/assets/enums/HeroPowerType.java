package issou.commun.collection.assets.enums;

import org.json.JSONArray;
import org.json.JSONObject;

public enum HeroPowerType {
    BouleDeFeu,
    BouleDeGlace;

    private static int bouleDeFeuID;
    private static int bouleDeGlaceID;

    public static void load(JSONArray types){
        for(int i = types.length()-1; i >= 0; i--){
            JSONObject type = types.getJSONObject(i);
            switch (type.getString("name")) {
                case "Boule de Feu" -> bouleDeFeuID = i;
                case "Boule de Glace" -> bouleDeGlaceID = i;
            }
        }
    }

    public static HeroPowerType Get(int id){
        if(id == bouleDeFeuID)
            return BouleDeFeu;
        if(id == bouleDeGlaceID)
            return BouleDeGlace;
        System.err.println("Hero Power Type "+id+" not knowed.");
        return BouleDeGlace;
    }
}
