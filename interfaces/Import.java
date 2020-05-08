package flashcards.interfaces;

import java.util.List;
import java.util.Map;

public interface Import {

    void importFromFile(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes);

    void importStart (String fileName, Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes);

}
