package issou.commun.collection.assets.enums;

import org.json.JSONArray;
import org.json.JSONObject;

public enum CharacterType {
    MageNoir,
    ChevalierBlanc;

    private static int mageNoirID;
    private static int chevalierBlancID;

    public static void load(JSONArray types){
        for(int i = 0; i < types.length() ; i++){
            JSONObject type = types.getJSONObject(i);
            switch (type.getString("name")) {
                case "Mage Noir" -> mageNoirID = i;
                case "Chevalier Blanc" -> chevalierBlancID = i;
            }
        }
    }

    public static CharacterType Get(int id){
        if(id == mageNoirID)
            return MageNoir;
        if(id == chevalierBlancID)
            return ChevalierBlanc;
        System.err.println("Character Type "+id+" not knowed.");
        return MageNoir;
    }
}

