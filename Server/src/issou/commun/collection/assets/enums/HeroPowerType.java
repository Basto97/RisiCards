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
            int id = type.getInt("id");
            switch (type.getString("name")) {
                case "Boule de Feu" -> bouleDeFeuID = id;
                case "Boule de Glace" -> bouleDeGlaceID = id;
            }
        }
    }

    public static HeroPowerType Get(int id){
        if(id == bouleDeFeuID)
            return BouleDeFeu;
        if(id == bouleDeGlaceID)
            return BouleDeGlace;
        return BouleDeFeu;
    }
}
