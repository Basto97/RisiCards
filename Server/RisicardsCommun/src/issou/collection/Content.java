package issou.collection;

import io.github.cdimascio.dotenv.Dotenv;
import issou.collection.assets.*;
import issou.logic.objects.HeroPower;
import issou.logic.objects.caracters.Hero;
import issou.logic.objects.card.Card;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Content {

    private static final Map<String, HeroPowerAsset> heroPowers = new HashMap<>();
    private static final Map<String, HeroAsset> heros = new HashMap<>();
    private static final Map<String, CardAsset> cards = new HashMap<>();
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

            HttpClient httpclient = HttpClients.createDefault();
            HttpGet get = new HttpGet(url);
            HttpResponse response = httpclient.execute(get);
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String total = "", line = "";
            while ((line = rd.readLine()) != null)
               total +=line;

            JSONObject json  = new JSONObject(Objects.requireNonNull(total));

            JSONObject gameOptions = json.getJSONObject("gameOptions");
            loadGameOptions(gameOptions);

            JSONObject data = json.getJSONObject("data");

            JSONArray heroPowers = data.getJSONArray("heroPowers");
            loadHeroPowers(heroPowers);

            JSONArray heros = data.getJSONArray("heros");
            loadHeros(heros);
            JSONObject cards = data.getJSONObject("cards");
            JSONArray minions = cards.getJSONArray("minions");
            JSONArray spells = cards.getJSONArray("spells");
            loadMinions(minions);
            loadSpells(spells);
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

    public static String toStringContent(){
        StringBuilder sb = new StringBuilder();
        sb.append("Game Options : \n");
        sb.append("- Initial Draw : ").append(getInitialDraw()).append("\n");
        sb.append("- Max Cards : ").append(getMaxCardsHand()).append("\n");
        sb.append("- Max Mana : ").append(getMaxMana()).append("\n");
        sb.append("\n");
        sb.append("Hero Powers :\n");
        for (Map.Entry<String, HeroPowerAsset> entry : heroPowers.entrySet())
            sb.append("- ").append(entry.getValue()).append("\n");
        sb.append("\n");
        sb.append("Heros :\n");
        for (Map.Entry<String, HeroAsset> entry : heros.entrySet())
            sb.append("- ").append(entry.getValue()).append("\n");
        sb.append("\n");
        sb.append("Cards :\n");
        for (Map.Entry<String, CardAsset> entry : cards.entrySet())
            sb.append("- ").append(entry.getValue()).append("\n");
        return sb.toString();
    }
}