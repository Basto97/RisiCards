package issou.collection;

import issou.collection.assets.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static issou.collection.LoadFile.readJsonFromUrl;

public class Content implements IContent {

    private static final IContent Instance = new Content();
    private static final Lock lock = new ReentrantLock();
    private static boolean refresh = true;

    private static final Map<Integer, HeroPowerAsset> heroPowers = new HashMap<>();
    private static final Map<Integer, CardAsset> cards = new HashMap<>();
    private static final Map<Integer, CharacterAsset> characters = new HashMap<>();
    private static int initialDraw;
    private static int maxCardsHand;
    private static int maxMana;

    public void AskRefresh(){
        refresh = true;
    }

    public static IContent Instance(){
        lock.lock();
        try{
            if(refresh){
                load();
                refresh = false;
            }
            return Instance;
        } finally {
            lock.unlock();
        }
    }

    private static void load(){
        heroPowers.clear();
        cards.clear();
        characters.clear();
        try {
            JSONObject json  = readJsonFromUrl();
            loadGameOptions(json.getJSONObject("gameOptions"));
            json = json.getJSONObject("data");
            loadCharacters(json.getJSONArray("characters"));
            loadHeroPowers(json.getJSONArray("heroPowers"));
            json = json.getJSONObject("cards");
            loadMinions(json.getJSONArray("minions"));
            loadSpells(json.getJSONArray("minions"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadGameOptions(JSONObject gameOptions){
        initialDraw = gameOptions.getInt("initialDraw");
        maxCardsHand = gameOptions.getInt("maxCardsHand");
        maxMana = gameOptions.getInt("maxMana");
    }

    private static void loadHeroPowers(JSONArray heroPowers) {
        for (int i = 0; i < heroPowers.length(); i++) {
            JSONObject json = heroPowers.getJSONObject(i);
            heroPowers.put(json.getInt("id"), new HeroPowerAsset(json));
        }
    }

    private static void loadCharacters(JSONArray characters) {
        for (int i = 0; i < characters.length(); i++) {
            JSONObject json = characters.getJSONObject(i);
            characters.put(json.getInt("id"), new CharacterAsset(json));
        }
    }

    private static void loadMinions(JSONArray minions) {
        for (int i = 0; i < minions.length(); i++) {
            JSONObject json = minions.getJSONObject(i);
            cards.put(json.getInt("id"), new MinionCardAsset(json));
        }
    }

    private static void loadSpells(JSONArray spells) {
        for (int i = 0; i < spells.length(); i++) {
            JSONObject json = spells.getJSONObject(i);
            cards.put(json.getInt("id"), new SpellCardAsset(json));
        }
    }

    @Override
    public CardAsset getCardAsset(int id) {
        return cards.get(id).clone();
    }

    @Override
    public CharacterAsset getCharacterAsset(int id) {
        return characters.get(id).clone();
    }

    @Override
    public HeroPowerAsset getHeroPowerAsset(int id) {
        return heroPowers.get(id).clone();
    }

    @Override
    public int initialDraw() {
        return initialDraw;
    }

    @Override
    public int maxCardsHand() {
        return maxCardsHand;
    }

    @Override
    public int maxMana() {
        return maxMana;
    }
}
