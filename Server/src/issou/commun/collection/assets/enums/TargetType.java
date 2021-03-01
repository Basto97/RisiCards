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
            switch (type.getString("name")) {
                case "AllChars" -> allChardsID = i;
                case "MyChar" -> myCharID = i;
                case "EnemyChar" ->enemyCharID = i;
                case "AllMinions" ->allMinionsID = i;
                case "MyMinions" ->myMinionsID = i;
                case "EnemyMinions" -> enemyMinionsID= i;
                case "NoTarget" ->noTargetID = i;
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
        if(id == noTargetID)
            return NoTarget;
        System.err.println("Target Type "+id+" not knowed.");
        return NoTarget;
    }
}
