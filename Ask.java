package flashcards;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Ask {

    public void ask(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("How many times to ask?");
        logs.add("How many times to ask?");
        int count = scanner.nextInt();
        logs.add(String.valueOf(count));
        scanner.nextLine();
        String randomCard = "";
        for (int i = 0; i < count; i++) {

            randomCard = cards.keySet().toArray()[random.nextInt(cards.size())].toString();
            System.out.println("Print the definition of " + "\"" + randomCard + "\":");
            logs.add("Print the definition of " + "\"" + randomCard + "\":");
            String definition = scanner.nextLine();
            logs.add(definition);
            if (cards.get(randomCard).equals(definition)) {
                System.out.println("Correct answer.");
                logs.add("Correct answer.");
            } else if (cards.containsValue(definition)) {

                mistakes.put(randomCard, mistakes.get(randomCard) + 1);
                String correctCard = "";
                for (Map.Entry<String, String> entry : cards.entrySet()) {
                    if (entry.getValue().equals(definition)) {
                        correctCard = entry.getKey();
                        System.out.println("Wrong answer. The correct one is " + "\"" + cards.get(randomCard) + "\", " +
                                "you've just written the definition of " + "\"" + correctCard + "\".");
                        logs.add("Wrong answer. The correct one is " + "\"" + cards.get(randomCard) + "\", " +
                                "you've just written the definition of " + "\"" + correctCard + "\".");
                    }
                }
            } else {
                mistakes.put(randomCard, mistakes.get(randomCard) + 1);
                System.out.println("Wrong answer. The correct one is " + "\"" + cards.get(randomCard) + "\"");
                logs.add("Wrong answer. The correct one is " + "\"" + cards.get(randomCard) + "\"");
            }
        }
    }

}
