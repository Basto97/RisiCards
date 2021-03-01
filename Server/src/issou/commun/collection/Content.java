package issou.commun.collection;

import issou.commun.collection.assets.*;
import issou.commun.collection.assets.enums.CharacterType;
import issou.commun.collection.assets.enums.HeroPowerType;
import issou.commun.collection.assets.enums.MinionType;
import issou.commun.collection.assets.enums.TargetType;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static issou.commun.collection.APIConnection.getData;
import static java.util.concurrent.TimeUnit.MINUTES;

public class Content implements IContent {

    private static final IContent Instance = new Content();
    private static final Lock lock = new ReentrantLock();
    private static boolean refresh = true;

    private static final Map<HeroPowerType, HeroPowerAsset> heroPowers = new HashMap<>();
    private static final Map<CharacterType, CharacterAsset> characters = new HashMap<>();
    private static final Map<Integer, CardAsset> cards = new HashMap<>();
    private static int initialDraw;
    private static int maxCardsHand;
    private static int maxMana;

    static {
        Runnable drawRunnable = () -> {
            refresh = APIConnection.getRefresh();
        };
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(drawRunnable , 10, 10, MINUTES);
        Instance();
    }

    public static void main(String[] args){
        System.out.println(Instance());
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

        JSONObject json  = getData();

        JSONObject gameOptions = json.getJSONObject("gameOptions");
        loadGameOptions(gameOptions);

        JSONObject sync = json.getJSONObject("sync");

        JSONArray cardTypes = sync.getJSONArray("cardTypes");
        MinionType.load(cardTypes);

        JSONArray targets = sync.getJSONArray("targets");
        TargetType.load(targets);

        JSONObject data = json.getJSONObject("data");

        JSONArray heroPowers = data.getJSONArray("heroPowers");
        HeroPowerType.load(heroPowers);
        Content.loadHeroPowers(heroPowers);

        JSONArray characters = data.getJSONArray("characters");
        CharacterType.load(characters);
        Content.loadCharacters(characters);

        JSONObject cards = data.getJSONObject("cards");
        JSONArray minions = cards.getJSONArray("minions");
        JSONArray spells = cards.getJSONArray("spells");
        loadMinions(minions);
        loadSpells(spells);
    }

    private static void loadGameOptions(JSONObject gameOptions){
        Content.initialDraw = gameOptions.getInt("initialDraw");
        Content.maxCardsHand = gameOptions.getInt("maxCardsHand");
        Content.maxMana = gameOptions.getInt("maxMana");
    }

    private static void loadHeroPowers(JSONArray heroPowers) {
        for (int i = 0; i < heroPowers.length(); i++) {
            JSONObject json = heroPowers.getJSONObject(i);
            HeroPowerAsset heroPowerAsset =  new HeroPowerAsset(json);
            Content.heroPowers.put(heroPowerAsset.getType(), heroPowerAsset);
        }
    }

    private static void loadCharacters(JSONArray charactersArray) {
        for (int i = 0; i < charactersArray.length(); i++) {
            JSONObject json = charactersArray.getJSONObject(i) ;
            CharacterAsset characterAsset = new CharacterAsset(json);
            Content.characters.put(characterAsset.getType(), characterAsset);
        }
    }

    private static void loadMinions(JSONArray minions) {
        for (int i = 0; i < minions.length(); i++) {
            JSONObject json = minions.getJSONObject(i);
            Content.cards.put(json.getInt("id"), new MinionCardAsset(json));
        }
    }

    private static void loadSpells(JSONArray spells) {
        for (int i = 0; i < spells.length(); i++) {
            JSONObject json = spells.getJSONObject(i);
            Content.cards.put(json.getInt("id"), new SpellCardAsset(json));
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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Game Options : \n");
        sb.append("- Initial Draw : ").append(initialDraw()).append("\n");
        sb.append("- Max Cards : ").append(maxCardsHand()).append("\n");
        sb.append("- Max Mana : ").append(maxMana()).append("\n");
        sb.append("\n");
        sb.append("Hero Powers :\n");
        for (Map.Entry<HeroPowerType, HeroPowerAsset> entry : heroPowers.entrySet())
            sb.append("- ").append(entry.getKey().name()).append(" : ").append(entry.getValue()).append("\n");
        sb.append("\n");
        sb.append("Characters :\n");
        for (Map.Entry<CharacterType, CharacterAsset> entry : characters.entrySet())
            sb.append("- ").append(entry.getKey().name()).append(" : ").append(entry.getValue()).append("\n");
        sb.append("\n");
        sb.append("Cards :\n");
        for (Map.Entry<Integer, CardAsset> entry : cards.entrySet())
            sb.append("- ").append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
        sb.append("\n");

        return sb.toString();
    }
}
