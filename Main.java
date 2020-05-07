package flashcards;

import java.io.*;
import java.util.*;

import static java.util.stream.Collectors.joining;

public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, Integer> mistakes = new LinkedHashMap<>();
        Map<String, String> cards = new LinkedHashMap<>();
        List<String> logs = new ArrayList<>();
        if (args.length != 0) {
            start(args, cards, logs, mistakes);
        } else {
            startNoArgs(cards, logs, mistakes);
        }
    }
    static void start(String[] args,
                                Map<String, String> cards,
                                List<String> logs,
                                Map<String, Integer> mistakes) {
        String fileNameImport = "";
        String fileNameExport = "";
        if (args.length < 3 && args[0].equals("-import")) {
            fileNameImport = args[1];
            importStart(fileNameImport, cards, logs, mistakes);
            startNoArgs(cards, logs, mistakes);
        }
        if (args.length < 3 && args[0].equals("-export")) {
            fileNameExport = args[1];
            startWithArgs(fileNameExport, cards, logs, mistakes);
        }
        if (args.length > 2 && args[0].equals("-import") && args[2].equals("-export")) {
            fileNameImport = args[1];
            importStart(fileNameImport, cards, logs, mistakes);
            fileNameExport = args[3];
            startWithArgs(fileNameExport, cards, logs, mistakes);
        }
        if (args.length > 2 && args[0].equals("-export") && args[2].equals("-import")) {
            fileNameImport = args[3];
            importStart(fileNameImport, cards, logs, mistakes);
            fileNameExport = args[1];
            startWithArgs(fileNameExport, cards, logs, mistakes);
        }

    }

    static void startWithArgs(String fileName, Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
        Scanner scanner = new Scanner(System.in);
        String action = "";
        while (!action.equals("exit")) {
            System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            logs.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            action = scanner.nextLine();
            logs.add(action);
            switch (action) {
                case "add":
                    Main.add(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "remove":
                    Main.remove(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "import":
                    Main.importFromFile(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "export":
                    Main.exportToFile(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "ask":
                    Main.ask(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    logs.add("Bye bye!");
                    exportExit(fileName, cards, logs, mistakes);
                    break;
                case "log":
                    new Log().log(logs);
                    System.out.println();
                    break;
                case "hardest card":
                    Main.hardestCard(logs, mistakes);
                    System.out.println();
                    break;
                case "reset stats":
                    Main.resetStats(logs, mistakes);
                    System.out.println();
                    break;
                case "show":
                    Main.show(cards);
                    System.out.println();
                    break;
            }
        }
    }

    static void startNoArgs(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
        Scanner scanner = new Scanner(System.in);
        String action = "";
        while (!action.equals("exit")) {
            System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            logs.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            action = scanner.nextLine();
            logs.add(action);
            switch (action) {
                case "add":
                    Main.add(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "remove":
                    Main.remove(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "import":
                    Main.importFromFile(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "export":
                    Main.exportToFile(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "ask":
                    Main.ask(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    logs.add("Bye bye!");
                    break;
                case "log":
                    new Log().log(logs);
                    System.out.println();
                    break;
                case "hardest card":
                    Main.hardestCard(logs, mistakes);
                    System.out.println();
                    break;
                case "reset stats":
                    Main.resetStats(logs, mistakes);
                    System.out.println();
                    break;
                case "show":
                    Main.show(cards);
                    System.out.println();
                    break;
            }
        }
    }

    static void importStart (String fileName,
                             Map<String, String> cards,
                             List<String> logs,
                             Map<String, Integer> mistakes) {
        File file = new File(fileName); // import.txt
        int count = 0;
        try (Scanner sc = new Scanner(file)) {
            sc.useDelimiter(":");
            while (sc.hasNext()) {
                String cardAndDefinition = sc.nextLine();
                String[] strings = cardAndDefinition.split(":");
                String card = strings[0];
                String definition = strings[1];
                String mistake = strings[2];
                cards.put(card, definition);
                mistakes.put(card, Integer.valueOf(mistake));
                count++;
            }
            System.out.println(count + " cards have been loaded.");
            logs.add(count + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            logs.add("File not found.");
        }
    }

    static void exportExit (String fileName,
                            Map<String, String> cards,
                            List<String> logs,
                            Map<String, Integer> mistakes) {
        File file = new File(fileName); //export.txt
        file.delete();
        int count = 0;
        for (Map.Entry<String, String> entry : cards.entrySet()) {
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(entry.getKey() + ":" + entry.getValue() + ":" + mistakes.get(entry.getKey()));
                writer.write("\n");
                count++;
            } catch (IOException e) {
                System.out.println();
            }
        }
        System.out.println(count + " cards have been saved.");
        logs.add(count + " cards have been saved.");
    }
}
