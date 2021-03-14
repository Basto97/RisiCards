using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using Sfs2X.Entities.Data;

[Serializable]
public class Hand {

    private List<Card> _cards;

    public Card LastDrawedCard => _cards[_cards.Count - 1];

    public Hand(ISFSArray arr) {
        _cards = new List<Card>();
        for(int i = 0 ; i <arr.Count; i++)
            _cards.Add(new Card(arr.GetSFSObject(i)));
    }

    public void AddCard(Card c) => _cards.Add(c);
    public bool Contains(Card c) => _cards.Contains(c);
    public ReadOnlyCollection<Card> Cards => _cards.AsReadOnly();

}