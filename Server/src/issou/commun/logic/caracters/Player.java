package issou.commun.logic.caracters;

import issou.commun.collection.assets.CardAsset;
import issou.commun.collection.assets.CharacterAsset;
import issou.commun.collection.assets.HeroPowerAsset;
import issou.commun.logic.objects.Deck;
import issou.commun.logic.objects.Hand;
import issou.commun.logic.objects.ManaPool;
import issou.commun.logic.objects.Table;

import java.util.Set;

public class Player extends CharacterAttacker {

    public Deck deck;
    public Hand hand;
    public Table table;
    public ManaPool manaPool;
    public HeroPowerAsset heroPower;

    public int heroId;

    public Player(CharacterAsset charAsset, Set<CardAsset> deck) {
        super(1,0,1,true);
       // this.heroId = charAsset.id;
        // this.manaPool = new ManaPool(charAsset.startMana);
        // this.deck = new Deck(deck);
        this.hand = new Hand();
        this.table = new Table();
        // this.heroPower = new HeroPower(charAsset);
    }

    public void onTurnStart() {
        manaPool.increaseManaMax(1);
        manaPool.resetCurrentMana();
        // heroPower.reset();
        table.onTurnStart();
    }

    public void onTurnEnd()
    {

    }


    /*

     public boolean CurrentTurn (){
        return TurnManager.Instance.WhoseTurn == this;
    }

    public void drawACard()
    {
        if (Deck.IsEmpty || Hand.IsFull) return;
        var card = new CardLogic(Deck.Draw()) {owner = this};
        var indexToPlaceACard = Hand.ReceveCard(card);
        new DrawACardCommand(Area ,card, indexToPlaceACard, fast, true).AddToQueue();
    }

    int
    public void drawFatigue(){

    }

    public void DrawAnExternalCard(CardAsset cardAsset)
    {
        if (Hand.IsFull) return;
        var card = new CardLogic(cardAsset) {owner = this};
        var indexToPlaceACard = Hand.ReceveCard(card);
        new DrawACardCommand(Area, card, indexToPlaceACard, true, false).AddToQueue();
    }

    public void PlayASpellFromHand(int idSpellCard, int idTarget)
        {
            Character target;
            if (idTarget < 0) target = null;
            else if (idTarget == ID) target = this;
            else if (idTarget == OtherPlayer.ID) target = OtherPlayer;
            else target = CreatureLogic.CreaturesCreatedThisGame[idTarget];
            PlayASpellFromHand(CardLogic.CardsCreatedThisGame[idSpellCard],target);
        }

        // TODO , change character to can use this function
        public void PlayASpellFromHand(Card playedCard, Character target)
        {
            manaPool.decreaseMana(playedCard.manaCost);
            playedCard.Effect?.ActivateEffect(playedCard.ca.specialSpellAmount, target);
            hand.removeCard(playedCard);
            new PlayASpellCardCommand(Area, playedCard).AddToQueue();
        }

        public void PlayACreatureFromHand(int id, int tablePos) {
            PlayACreatureFromHand(CardLogic.CardsCreatedThisGame[id], tablePos);
        }
        public void PlayACreatureFromHand(CardLogic playedCard, int tablePos)
        {
            ManaPool.DecreaseMana(playedCard.ManaCost);
            var newCreature = new CreatureLogic(this, playedCard.ca);
            Table.CreaturesOnTable.Insert(tablePos, newCreature);
            Hand.RemoveCard(playedCard);
            new PlayACreatureCommand(Area, playedCard, tablePos, newCreature.ID).AddToQueue();
        }
        */
        public void die()
        {
            // TODO TURNMANAGER
        }
}
