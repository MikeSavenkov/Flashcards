package flashcards.interfaces;

import java.util.List;
import java.util.Map;

public interface Export {

    void exportToFile(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes);

    void exportExit (String fileName, Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes);

}
