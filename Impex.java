package flashcards;

import java.util.List;
import java.util.Map;

public interface Impex {

    void importFromFile(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes);

    void exportToFile(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes);

}
