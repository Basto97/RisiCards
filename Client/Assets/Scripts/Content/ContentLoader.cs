using System;
using System.Collections.Generic;
using Sfs2X.Entities.Data;
using UnityEngine;

public static class ContentLoader {
    private static Load _load;
    
    public static void Load(ISFSObject ob) {
        _load = new Load {
            gameOptions = new GameOptions {
                initialDraw = ob.GetInt("initialDraw"),
                maxCardsHand = ob.GetInt("maxCardsHand"),
                maxMana = ob.GetInt("maxMana")
            },
            data = new Data(ob)
        };
    }

    public static GameOptions GetGameOptions() => _load.gameOptions;
    public static HeroPowerAsset GetHeroPowerAsset(string name) => _load.data.heroPowers[name];
    public static HeroAsset GetHeroAsset(string name) => _load.data.heros[name];
    public static CardAsset GetCardAsset(string name) => _load.data.cards[name];
}

[Serializable]
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
}

[Serializable]
public class HeroPowerAsset {
    public string name;
    public int cost;
    public int[] special;
    public string targetType;

    public HeroPowerAsset(ISFSObject ob) {
        name = ob.GetUtfString("name");
        cost = ob.GetInt("cost");
        special = ob.GetIntArray("special");
        targetType = ob.GetUtfString("targetType");
    }
}

[Serializable]
public class CardAsset {
    public string name;
    public int cost;
    public int[] special;
    public string[] effects;
    public bool minion;
    public string hero;
    
    public int health;
    public int attack;
    public string type;
    
    public string targetType;

    public CardAsset(ISFSObject ob) {
        name = ob.GetUtfString("name");
        cost = ob.GetInt("cost");
        special = ob.GetIntArray("special");
        effects = ob.GetUtfStringArray("effects");
        minion = ob.GetBool("minion");
        hero = ob.GetUtfString("hero");
        if (minion) {
            health = ob.GetInt("health");
            attack = ob.GetInt("attack");
            type = ob.GetUtfString("type");
            targetType = "";
        } else {
            health = 0;
            attack = 0;
            type = "";
            targetType = ob.GetUtfString("targetType");
        }
    }
}

[Serializable]
public class Load {
    public GameOptions gameOptions;
    public Data data;
}

[Serializable]
public class GameOptions {
    public int initialDraw;
    public int maxCardsHand;
    public int maxMana;
}

[Serializable]
public class Data {
    public Dictionary<string, HeroPowerAsset> heroPowers;
    public Dictionary<string, HeroAsset> heros;
    public Dictionary<string, CardAsset> cards;

    public Data(ISFSObject ob) {
        heroPowers = new Dictionary<string, HeroPowerAsset>();
        ISFSArray heroPowersSfs = ob.GetSFSArray("herosPowers");
        for (int i = 0; i < heroPowersSfs.Count; i++) {
            HeroPowerAsset asset = new HeroPowerAsset(heroPowersSfs.GetSFSObject(i));
            heroPowers.Add(asset.name, asset);
        }

        heros = new Dictionary<string, HeroAsset>();
        ISFSArray herosArr = ob.GetSFSArray("heros");
        for (int i = 0; i < herosArr.Count; i++) {
            HeroAsset asset = new HeroAsset(herosArr.GetSFSObject(i));
            heros.Add(asset.name, asset);
        }

        cards = new Dictionary<string, CardAsset>();
        ISFSArray cardsArr = ob.GetSFSArray("cards");
        for (int i = 0; i < cardsArr.Count; i++) {
            ISFSObject card = cardsArr.GetSFSObject(i);
            CardAsset cardAsset = new CardAsset(card);
            cards.Add(cardAsset.name, cardAsset);
        }
    }
}