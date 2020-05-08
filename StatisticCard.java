package flashcards;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class StatisticCard {

    public void resetStats(List<String> logs, Map<String, Integer> mistakes) {

        mistakes.entrySet().forEach(x -> x.setValue(0));
        System.out.println("Card statistics has been reset");
        logs.add("Card statistics has been reset");

    }

    public void show(Map<String, String> map) {

        map.entrySet().forEach(System.out::println);

    }

    public void hardestCard(List<String> logs, Map<String, Integer> mistakes) {

        int max = mistakes.values().stream().max(Integer::compareTo).get();

        List<String> terms = mistakes.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        if (terms.size() == 1 && max != 0) {
            System.out.println("The hardest card is \"" + terms.get(0) + "\". You have " + max + " errors answering it.");
            logs.add("The hardest card is \"" + terms.get(0) + "\". You have " + max + " errors answering it.");
        } else if (max == 0 || terms.size() == 0) {
            System.out.println("There are no cards with errors.");
            logs.add("There are no cards with errors.");
        } else {
            String joined = mistakes.keySet().stream()
                    .collect(joining("\", \"", "\"", "\""));
            System.out.println("The hardest cards are " + joined + ". You have " + max + " errors answering them.");
            logs.add("The hardest cards are " + joined + ". You have " + max + " errors answering them.");

        }
    }

}
