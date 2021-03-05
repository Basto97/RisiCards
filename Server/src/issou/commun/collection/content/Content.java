package issou.commun.collection.content;

import io.github.cdimascio.dotenv.Dotenv;
import issou.commun.collection.assets.card.ICardAsset;
import issou.commun.collection.assets.card.MinionCardAsset;
import issou.commun.collection.assets.card.SpellCardAsset;
import issou.commun.collection.assets.enums.HeroPowerType;
import issou.commun.collection.assets.enums.HeroType;
import issou.commun.collection.assets.enums.MinionType;
import issou.commun.collection.assets.enums.TargetType;
import issou.commun.collection.assets.hero.HeroAsset;
import issou.commun.collection.assets.hero.IHeroAsset;
import issou.commun.collection.assets.heropower.HeroPowerAsset;
import issou.commun.collection.assets.heropower.IHeroPowerAsset;
import issou.commun.logic.caracters.hero.Hero;
import issou.commun.logic.caracters.hero.IHero;
import issou.commun.logic.objects.card.Card;
import issou.commun.logic.objects.card.ICard;
import issou.commun.logic.objects.heropower.HeroPower;
import issou.commun.logic.objects.heropower.IHeroPower;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Content implements IContent {

    public static final IContent Instance = new Content();

    private static final Map<HeroPowerType, IHeroPowerAsset> heroPowers = new HashMap<>();
    private static final Map<HeroType, IHeroAsset> heros = new HashMap<>();
    private static final Map<Integer, ICardAsset> cards = new HashMap<>();
    private static final Map<Integer, String> cardNames = new HashMap<>();
    private static int initialDraw;
    private static int maxCardsHand;
    private static int maxMana;

    static {
        try {
            Dotenv dotenv = Dotenv.load();
            String protocol = dotenv.get("API_SERVER_PROTOCOL");
            String host = dotenv.get("API_SERVER_HOST");
            String port = dotenv.get("API_SERVER_PORT");
            String cmd = dotenv.get("API_SERVER_CMD_DATA");
            String url =  protocol + "://" + host + ":"+ port + "/"+cmd;
            URL urlCmd = new URL(url);
            Request request = new Request.Builder().url(urlCmd).build();
            Response response = new OkHttpClient().newCall(request).execute();
            JSONObject json  = new JSONObject(Objects.requireNonNull(response.body()).string());

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
            HeroType.load(characters);
            Content.loadHeros(characters);

            JSONObject cards = data.getJSONObject("cards");
            JSONArray minions = cards.getJSONArray("minions");
            JSONArray spells = cards.getJSONArray("spells");
            int nbMinions = loadMinions(minions);
            loadSpells(spells, nbMinions);
            loadCardNames(minions, spells);
        }  catch (JSONException | IOException e){
            e.printStackTrace();
        }
    }

    private static void loadGameOptions(JSONObject gameOptions){
        Content.initialDraw = gameOptions.getInt("initialDraw");
        Content.maxCardsHand = gameOptions.getInt("maxCardsHand");
        Content.maxMana = gameOptions.getInt("maxMana");
    }
    private static void loadHeroPowers(JSONArray heroPowers) {
        for (int i = 0; i < heroPowers.length(); i++) {
            JSONObject json = heroPowers.getJSONObject(i);
            HeroPowerAsset heroPowerAsset =  new HeroPowerAsset(i, json);
            Content.heroPowers.put(heroPowerAsset.getHeroPowerType(), heroPowerAsset);
        }
    }
    private static void loadHeros(JSONArray charactersArray) {
        for (int i = 0; i < charactersArray.length(); i++) {
            JSONObject json = charactersArray.getJSONObject(i) ;
            HeroAsset heroAsset = new HeroAsset(i, json);
            Content.heros.put(heroAsset.getType(), heroAsset);
        }
    }
    private static int loadMinions(JSONArray minions) {
        int i;
        for (i = 0; i < minions.length(); i++) {
            JSONObject json = minions.getJSONObject(i);
            Content.cards.put(i, new MinionCardAsset(i,json));
        }
        return i;
    }
    private static void loadSpells(JSONArray spells, int idMinions) {
        for (int i = 0; i < spells.length(); i++) {
            JSONObject json = spells.getJSONObject(i);
            int id = idMinions + i;
            Content.cards.put(id, new SpellCardAsset(id, json));
        }
    }
    private static void loadCardNames(JSONArray minions, JSONArray spells){
        int i;
        for (i = 0; i < minions.length(); i++)
            Content.cardNames.put(i, minions.getJSONObject(i).getString("name"));
        for (int j = 0; j < spells.length(); j++)
            Content.cardNames.put(i+j, spells.getJSONObject(j).getString("name"));
    }

    public ICard getCard(int id) {
        return new Card(cards.get(id));
    }
    public IHero getHero(HeroType type) {
        return new Hero(heros.get(type));
    }
    public IHeroPower getHeroPower(HeroType type) {
        return new HeroPower(heroPowers.get(heros.get(type).getHeroPowerType()));
    }
    public IHeroPower getHeroPower(HeroPowerType type) {
        return new HeroPower(heroPowers.get(type));
    }
    public int initialDraw() {
        return initialDraw;
    }
    public int maxCardsHand() {
        return maxCardsHand;
    }
    public int maxMana() {
        return maxMana;
    }
    public String getCardName(int id) {
        return cardNames.get(id);
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Game Options : \n");
        sb.append("- Initial Draw : ").append(initialDraw()).append("\n");
        sb.append("- Max Cards : ").append(maxCardsHand()).append("\n");
        sb.append("- Max Mana : ").append(maxMana()).append("\n");
        sb.append("\n");
        sb.append("Hero Powers :\n");
        for (Map.Entry<HeroPowerType, IHeroPowerAsset> entry : heroPowers.entrySet())
            sb.append("- ").append(entry.getValue()).append("\n");
        sb.append("\n");
        sb.append("Heros :\n");
        for (Map.Entry<HeroType, IHeroAsset> entry : heros.entrySet())
            sb.append("- ").append(entry.getValue()).append("\n");
        sb.append("\n");
        sb.append("Cards :\n");
        for (Map.Entry<Integer, ICardAsset> entry : cards.entrySet())
            sb.append("- ").append(entry.getValue()).append("\n");
        return sb.toString();
    }
}