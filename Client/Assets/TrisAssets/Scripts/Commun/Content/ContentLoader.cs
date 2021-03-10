using System.Collections.Generic;
using System.Linq;
using Sfs2X.Entities.Data;
using UnityEngine;

public static class ContentLoader {
    
    private static Load _load;
    
    public static void Load(ISFSObject ob) {
        _load = new Load();
        _load.gameOptions = new GameOptions {
            initialDraw = ob.GetInt("initialDraw"),
            maxCardsHand = ob.GetInt("maxCardsHand"),
            maxMana = ob.GetInt("maxMana")
        };

        _load.data = new Data();

        _load.data.heroPowers = new Dictionary<string, HeroPowerAsset>();
        ISFSArray heroPowersSfs = ob.GetSFSArray("herosPowers");
        for (int i = 0; i < heroPowersSfs.Count; i++) {
            HeroPowerAsset asset = new HeroPowerAsset(heroPowersSfs.GetSFSObject(i));
            _load.data.heroPowers.Add(asset.name, asset);
        }

        _load.data.heros = new Dictionary<string, HeroAsset>();
        ISFSArray heros = ob.GetSFSArray("heros");
        for (int i = 0; i < heros.Count; i++) {
            HeroAsset asset = new HeroAsset(heros.GetSFSObject(i));
            _load.data.heros.Add(asset.name, asset);
        }

        _load.data.cards = new Dictionary<string, CardAsset>();
        ISFSArray cards = ob.GetSFSArray("cards");
        for (int i = 0; i < cards.Count; i++) {
            ISFSObject card = cards.GetSFSObject(i);
            string cardType = card.GetUtfString("cardType");
            CardAsset cardAsset;
            if (cardType.Equals("minion"))
                cardAsset = new MinionCardAsset(card);
            else
                cardAsset = new SpellCardAsset(card);
            _load.data.cards.Add(cardAsset.name, cardAsset);
        }
        Debug.Log(_load);
    }

    public static GameOptions GetGameOptions() => _load.gameOptions;
    public static HeroPowerAsset GetHeroPowerAsset(string name) => _load.data.heroPowers[name];
    public static HeroAsset GetHeroAsset(string name) => _load.data.heros[name];
    public static CardAsset GetCardAsset(string name) => _load.data.cards[name];
}

public abstract class CardAsset {
    public string name;
    public int cost;
    public string[] special;
    public string[] effects;

    protected CardAsset(ISFSObject ob) {
        name = ob.GetUtfString("name");
        cost = ob.GetInt("cost");
        special = ob.GetUtfStringArray("special");
        effects = ob.GetUtfStringArray("effects");
    }

    public override string ToString() {
        string specialS = special.Aggregate("", (current, t) => current + t+",");
        string effectsS = effects.Aggregate("", (current, t) => current + t+",");
        return $"{nameof(name)}: {name}, {nameof(cost)}: {cost}, {nameof(special)}: {specialS}, {nameof(effects)}: {effectsS}";
    }
}
public class HeroAsset
{
    public string name;
    public string heroPower;
    public int health;
    public int startMana;

    public HeroAsset(ISFSObject ob) {
        name = ob.GetUtfString("name");
        heroPower = ob.GetUtfString("heroPower");
        health = ob.GetInt("health");
        startMana = ob.GetInt("startMana");
    }

    public override string ToString() => $"{nameof(name)}: {name}, {nameof(heroPower)}: {heroPower}, {nameof(health)}: {health}, {nameof(startMana)}: {startMana}";
}
public class HeroPowerAsset {
    public string name;
    public int cost;

    public HeroPowerAsset(ISFSObject ob) {
        name = ob.GetUtfString("name");
        cost = ob.GetInt("cost");
    }

    public override string ToString() => $"{nameof(name)}: {name}, {nameof(cost)}: {cost}";
}
public class MinionCardAsset : CardAsset {
    
    public int health;
    public int attack;
    public string type;

    public MinionCardAsset(ISFSObject ob): base(ob){
        health = ob.GetInt("health");
        attack = ob.GetInt("attack");
        type = ob.GetUtfString("type");
    }

    public override string ToString() => $"{base.ToString()}, {nameof(health)}: {health}, {nameof(attack)}: {attack}, {nameof(type)}: {type}";
}
public class SpellCardAsset : CardAsset {
    public string targetType;
    public SpellCardAsset(ISFSObject ob) : base(ob ) => targetType = ob.GetUtfString("targetType");
    public override string ToString() => $"{base.ToString()}, {nameof(targetType)}: {targetType}";
}

public class GameOptions {
    public int initialDraw;
    public int maxCardsHand;
    public int maxMana;

    public override string ToString() {
        return $"{nameof(initialDraw)}: {initialDraw}, {nameof(maxCardsHand)}: {maxCardsHand}, {nameof(maxMana)}: {maxMana}";
    }
}

internal class Load {
    public GameOptions gameOptions;
    public Data data;

    public override string ToString() {
        return $"{nameof(gameOptions)}: {gameOptions}, {nameof(data)}: {data}";
    }
}

internal class Data {
    public Dictionary<string, HeroPowerAsset> heroPowers;
    public Dictionary<string, HeroAsset> heros;
    public Dictionary<string, CardAsset> cards;

    public override string ToString() {
        string heroPowersS = heroPowers.Aggregate("", (current, t) => current + t.Value+",");
        string herosS = heros.Aggregate("", (current, t) => current + t.Value+",");
        string cardsS = cards.Aggregate("", (current, t) => current + t.Value+",");
        return $"{nameof(heroPowers)}: {heroPowersS}, {nameof(heros)}: {herosS}, {nameof(cards)}: {cardsS}";
    }
}