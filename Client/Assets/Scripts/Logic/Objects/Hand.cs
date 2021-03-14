using System;
using System.Collections.Generic;
using Sfs2X.Entities.Data;

[Serializable]
public class Hand {
    public List<Card> cards = new List<Card>();

    public Hand(ISFSArray arr) {
        for(int i = 0 ; i <arr.Count; i++)
            cards.Add(new Card(arr.GetSFSObject(i)));
    }

    public void AddCard(Card c) {
        cards.Add(c);
    }
}