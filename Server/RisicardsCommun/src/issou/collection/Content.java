package issou.collection;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import issou.collection.assets.*;
import issou.logic.objects.HeroPower;
import issou.logic.objects.caracters.Hero;
import issou.logic.objects.card.Card;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Content {

    private static final ISFSObject serializedContent = new SFSObject();;

    private static final Map<String, HeroPowerAsset> heroPowers = new HashMap<>();
    private static final Map<String, HeroAsset> heros = new HashMap<>();
    private static final Map<String, CardAsset> cards = new HashMap<>();
    private static int initialDraw;
    private static int maxCardsHand;
    private static int maxMana;

    static {
        try {

            StringBuilder total = new StringBuilder();
            File myObj = new File("risicards.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
                total.append(myReader.nextLine());
            myReader.close();

            JSONObject json  = new JSONObject(Objects.requireNonNull(total.toString()));

            JSONObject gameOptions = json.getJSONObject("gameOptions");
            loadGameOptions(gameOptions);

            JSONObject data = json.getJSONObject("data");

            JSONArray heroPowersJson = data.getJSONArray("heroPowers");
            loadHeroPowers(heroPowersJson);

            JSONArray herosJson = data.getJSONArray("heros");
            loadHeros(herosJson);
            JSONObject cardsJson = data.getJSONObject("cards");
            JSONArray minions = cardsJson.getJSONArray("minions");
            JSONArray spells = cardsJson.getJSONArray("spells");
            loadMinions(minions);
            loadSpells(spells);

            loadSFS();

        }  catch (JSONException | IOException e){
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
            HeroPowerAsset heroPowerAsset =  new HeroPowerAsset(json);
            Content.heroPowers.put(heroPowerAsset.getName(), heroPowerAsset);
        }
    }
    private static void loadHeros(JSONArray heros) {
        for (int i = 0; i < heros.length(); i++) {
            JSONObject json = heros.getJSONObject(i) ;
            HeroAsset heroAsset = new HeroAsset(json);
            Content.heros.put(heroAsset.getName(), heroAsset);
        }
    }
    private static void loadMinions(JSONArray minions) {
        for (int i = 0; i < minions.length(); i++) {
            JSONObject json = minions.getJSONObject(i);
            MinionCardAsset minionCardAsset = new MinionCardAsset(json);
            cards.put(minionCardAsset.getName(), minionCardAsset);
        }
    }
    private static void loadSpells(JSONArray spells) {
        for (int i = 0; i < spells.length(); i++) {
            JSONObject json = spells.getJSONObject(i);
            SpellCardAsset spellCardAsset = new SpellCardAsset(json);
            cards.put(spellCardAsset.getName(), spellCardAsset);
        }
    }

    private static void loadSFS(){
        serializedContent.putInt("initialDraw",initialDraw);
        serializedContent.putInt("maxCardsHand",maxCardsHand);
        serializedContent.putInt("maxMana",maxMana);

        ISFSArray heroPowersSfs = new SFSArray();
        for (Map.Entry<String, HeroPowerAsset> entry : heroPowers.entrySet())
            heroPowersSfs.addSFSObject(entry.getValue().toISFS());
        serializedContent.putSFSArray("herosPowers", heroPowersSfs);

        ISFSArray cardsSfs = new SFSArray();
        for (Map.Entry<String, CardAsset> entry : cards.entrySet())
            cardsSfs.addSFSObject(entry.getValue().toISFS());
        serializedContent.putSFSArray("cards", cardsSfs);

        ISFSArray herosSfs = new SFSArray();
        for (Map.Entry<String, HeroAsset> entry : heros.entrySet())
            herosSfs.addSFSObject(entry.getValue().toISFS());
        serializedContent.putSFSArray("heros", herosSfs);
    }

    public static boolean isCard(String name){
        return cards.containsKey(name);
    }
    public static boolean isHero(String name){
        return heros.containsKey(name);
    }
    public static Card getCard(String name) {
        return new Card(Content.cards.get(name));
    }
    public static Hero getHero(String name) {
        return new Hero(heros.get(name));
    }
    public static HeroPower getHeroPower(String name) {
        return new HeroPower(heroPowers.get(name));
    }
    public static int getInitialDraw() {
        return initialDraw;
    }
    public static int getMaxCardsHand() {
        return maxCardsHand;
    }
    public static int getMaxMana() {
        return maxMana;
    }
    public static ISFSObject getSerializedContent() {
        return serializedContent;
    }
}