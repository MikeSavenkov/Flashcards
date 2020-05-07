package flashcards;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EditCard implements Card {

    @Override
    public void removeCard(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("The card:");
        logs.add("The card:");
        String card = scanner.nextLine();
        logs.add(card);
        if (cards.containsKey(card)) {
            cards.remove(card);
            mistakes.remove(card);
            System.out.println("The card has been removed");
            logs.add("The card has been removed");
        } else {
            System.out.println("Can't remove \"" + card + "\": there is no such card.");
            logs.add("Can't remove \"" + card + "\": there is no such card.");
        }
    }

    @Override
    public void addCard(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
        Scanner scanner = new Scanner(System.in);
        String term;
        String definition;
        if (cards.size() == 0) {
            System.out.println("The card:");
            logs.add("The card:");
            term = scanner.nextLine();
            logs.add(term);
            System.out.println("The definition of the card:");
            logs.add("The definition of the card:");
            definition = scanner.nextLine();
            logs.add(definition);
            cards.put(term, definition);
            mistakes.put(term, 0);
            System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
            logs.add("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
        } else {
            System.out.println("The card:");
            logs.add("The card:");
            if (cards.containsKey(term = scanner.nextLine())) {
                logs.add(term);
                System.out.println("The card " + "\"" + term + "\"" + " already exists.");
                logs.add("The card " + "\"" + term + "\"" + " already exists.");
                return;
            }
            logs.add(term);
            System.out.println("The definition of the card:");
            logs.add("The definition of the card:");
            if (cards.containsValue(definition = scanner.nextLine())) {
                logs.add(definition);
                System.out.println("The definition " + "\"" + definition + "\"" + " already exists.");
                logs.add("The definition " + "\"" + definition + "\"" + " already exists.");
                return;
            }
            logs.add(definition);
            cards.put(term, definition);
            mistakes.put(term, 0);
            System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
            logs.add("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
        }
    }

}
