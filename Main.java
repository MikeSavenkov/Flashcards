package flashcards;

import flashcards.interfaces.Import;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> mistakes = new LinkedHashMap<>();
        Map<String, String> cards = new LinkedHashMap<>();
        List<String> logs = new ArrayList<>();
        List<String> arguments = Arrays.stream(args).collect(Collectors.toList());

        checkArgs(arguments, cards, logs, mistakes);
    }
    static void checkArgs(List<String> args,
                                Map<String, String> cards,
                                List<String> logs,
                                Map<String, Integer> mistakes) {

        Start start = new Start();
        Import importing = new Imports();

        String fileNameImport = "";
        String fileNameExport = "";

        if (args.size() > 2) {
            if (args.get(0).equals("-import") && args.get(2).equals("-export")) {
                fileNameImport = args.get(1);
                start.start(fileNameImport, cards, logs, mistakes);
                fileNameExport = args.get(3);
                start.start(fileNameExport, cards, logs, mistakes);

            } else if (args.get(0).equals("-export") && args.get(2).equals("-import")) {
                fileNameImport = args.get(3);
                importing.importStart(fileNameImport, cards, logs, mistakes);
                fileNameExport = args.get(1);
                start.start(fileNameExport, cards, logs, mistakes);
            }

        } else if (args.size() == 2) {
            if (args.contains("-import")) {
                fileNameImport = args.get(1);
                importing.importStart(fileNameImport, cards, logs, mistakes);
                start.start("noArgs", cards, logs, mistakes);

            } else if (args.contains("-export")) {
                fileNameExport = args.get(1);
                start.start(fileNameExport, cards, logs, mistakes);

            }
        }
        start.start("noArgs", cards, logs, mistakes);
    }
}
