package issou.commun.collection;

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

    private static final Map<HeroPowerType, IHeroPowerAsset> heroPowers = new HashMap<>();
    private static final Map<HeroType, IHeroAsset> heros = new HashMap<>();
    private static final Map<Integer, ICardAsset> cards = new HashMap<>();
    private static final Map<Integer, String> cardNames = new HashMap<>();
    private static int initialDraw;
    private static int maxCardsHand;
    private static int maxMana;

    static {
        Runnable drawRunnable = () -> {
            refresh = APIConnection.getRefresh();
        };
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        exec.scheduleAtFixedRate(drawRunnable , 10, 10, MINUTES);
        Instance(); // to load
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
        heros.clear();

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
        HeroType.load(characters);
        Content.loadHeros(characters);

        JSONObject cards = data.getJSONObject("cards");
        JSONArray minions = cards.getJSONArray("minions");
        JSONArray spells = cards.getJSONArray("spells");
        int nbMinions = loadMinions(minions);
        loadSpells(spells, nbMinions);

        loadCardNames(minions, spells);
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

    @Override
    public ICard getCard(int id) {
        return new Card(cards.get(id));
    }
    @Override
    public IHero getHero(HeroType type) {
        return new Hero(heros.get(type));
    }

    @Override
    public IHeroPower getHeroPower(HeroType type) {
        return new HeroPower(heroPowers.get(heros.get(type).getHeroPowerType()));
    }

    @Override
    public IHeroPower getHeroPower(HeroPowerType type) {
        return new HeroPower(heroPowers.get(type));
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
    public String getCardName(int id) {
        return cardNames.get(id);
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