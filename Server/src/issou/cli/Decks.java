package issou.cli;

import issou.commun.collection.Content;
import issou.commun.logic.objects.Deck;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Decks {

    private static final List<Deck> decks = new ArrayList<>();
    public static final List<List<String>> decksStr = new ArrayList<>();

    public static void loadDecks() {
        try {
            File myObj = new File("decks.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] elems = data.split(" ");
                Deck deck = new Deck();
                List<String> deckStr = new ArrayList<>();
                for(String elem : elems){
                    deckStr.add(elem);
                    deck.addCard(Content.getCard(elem));
                }
                decksStr.add(deckStr);
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
        for(Deck deck : decks)
            sb.append(" ").append(i++).append(" : ").append(deck).append("\n");
        System.out.println(sb.toString());
    }
}
