package flashcards;

import java.util.List;
import java.util.Map;

interface Card {

    void addCard(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes);

    void removeCard(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes);
}
