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
                    Main.log(logs);
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
                    Main.log(logs);
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

    static void add(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
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

    static void remove(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
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

    static void show(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }

    static void importFromFile(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("File name:");
        logs.add("File name:");
        String fileName = scanner.nextLine();  //  C:\Games\file.txt
        logs.add(fileName);
        File file = new File(fileName);
        int count = 0;
        try (Scanner sc = new Scanner(file)) {
            sc.useDelimiter(":");
            while (sc.hasNext()) {
                String cardAndDefinition = sc.nextLine();
                String[] strings = cardAndDefinition.split(":"); // import.txt
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

    static void exportToFile(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("File name:");
        logs.add("File name:");
        String path = scanner.nextLine();  // C:\Games\file.txt
        logs.add(path);
        File file = new File(path);
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
        System.out.println(count + " cards have been saved");
        logs.add(count + " cards have been saved");
    }

    static void ask(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
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

    static void log(List<String> logs) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("File name:");
        logs.add("File name:");
        String path = scanner.nextLine(); //C:\Games\log.txt
        logs.add(path);
        File file = new File(path);
        try (FileWriter writer = new FileWriter(file, true)) {
            for (String log : logs) {
                writer.write(log);
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println();
        }

        System.out.println("The log has been saved.");
    }

    static void hardestCard(List<String> logs, Map<String, Integer> mistakes) {
        List<String> terms = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (Integer mistake : mistakes.values()) {
            if (mistake > max) {
                max = mistake;
            }
        }
        for (Map.Entry<String, Integer> entry : mistakes.entrySet()) {
            if (entry.getValue() == max) {
                terms.add(entry.getKey());
            }
        }

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

    static void resetStats(List<String> logs, Map<String, Integer> mistakes) {
        for (Map.Entry<String, Integer> entry : mistakes.entrySet()) {
            entry.setValue(0);
        }
        System.out.println("Card statistics has been reset");
        logs.add("Card statistics has been reset");
    }
}
