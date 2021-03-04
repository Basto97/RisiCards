package issou.cli;

import issou.commun.collection.Content;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Decks {

    private static final Scanner sc = new Scanner(System.in);

    public static void loadDecks() {
        try {
            File myObj = new File("decks.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                setDeck(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void GererDecks(){
        System.out.println(decks());
    }

    public static List<Integer> getDeck(int index){
        return decks.get(index);
    }

    private static final List<List<Integer>> decks = new ArrayList<>();

    private static void setDeck(String newDeck){
        String[] elems = newDeck.split(" ");

        List<Integer> deck = new ArrayList<>();

        for(String elem : elems){
            deck.add(Integer.parseInt(elem));
        }
        decks.add(deck);
    }


    private static String decks(){
        StringBuilder sb = new StringBuilder();
        sb.append("DECKS : \n");
        for(int i = 0 ; i < decks.size() ; i++)
            sb.append(i).append(" - ").append(getDeckS(i)).append("\n");
        sb.append("\n");
        return sb.toString();
    }

    private static String getDeckS(int index){
        StringBuilder s = new StringBuilder();
        for(int i : decks.get(index))
            s.append(Content.Instance().getCardName(i)).append(", ");
        return s.toString();
    }
}
