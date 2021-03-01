package issou.commun.collection.assets.enums;

import org.json.JSONArray;
import org.json.JSONObject;

public enum TargetType {
    AllChars,
    MyChar,
    EnemyChar,
    AllMinions,
    MyMinions,
    EnemyMinions,
    NoTarget;

    private static int allChardsID;
    private static int myCharID;
    private static int enemyCharID;
    private static int allMinionsID;
    private static int myMinionsID;
    private static int enemyMinionsID;
    private static int noTargetID;

    public static void load(JSONArray types){
        for(int i = 0; i < types.length() ; i++){
            JSONObject type = types.getJSONObject(i);
            int id = type.getInt("id");
            switch (type.getString("name")) {
                case "AllChars" -> allChardsID = id;
                case "MyChar" -> myCharID = id;
                case "EnemyChar" ->enemyCharID = id;
                case "AllMinions" ->allMinionsID = id;
                case "MyMinions" ->myMinionsID = id;
                case "EnemyMinions" -> enemyMinionsID= id;
                case "NoTarget" ->noTargetID = id;
            }
        }
    }

    public static TargetType Get(int id){
        if(id == allChardsID)
            return AllChars;
        if(id == myCharID)
            return MyChar;
        if(id == enemyCharID)
            return EnemyChar;
        if(id == allMinionsID)
            return AllMinions;
        if(id == myMinionsID)
            return MyMinions;
        if(id == enemyMinionsID)
            return EnemyMinions;
        return NoTarget;
    }
}
