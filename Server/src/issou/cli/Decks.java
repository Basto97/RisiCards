package issou.cli;

import issou.commun.collection.Content;
import issou.commun.logic.objects.deck.Deck;
import issou.commun.logic.objects.deck.IDeck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Decks {

    private static final List<IDeck> decks = new ArrayList<>();
    public static final List<List<Integer>> decksInts = new ArrayList<>();

    public static void loadDecks() {
        try {
            File myObj = new File("decks.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] elems = data.split(" ");
                IDeck deck = new Deck();
                List<Integer> deckInt = new ArrayList<>();
                for(String elem : elems){
                    int cardInt = Integer.parseInt(elem);
                    deckInt.add(cardInt);
                    deck.addCard(Content.Instance().getCard(cardInt));
                }
                decksInts.add(deckInt);
                decks.add(deck);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void MontrerDecks(){
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for(IDeck deck : decks)
            sb.append(" ").append(i++).append(" : ").append(deck).append("\n");
        System.out.println(sb.toString());
    }
}
