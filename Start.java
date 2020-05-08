package flashcards;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Start {

    void start(String fileName, Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
        Scanner scanner = new Scanner(System.in);
        String action = "";
        while (!action.equals("exit")) {
            System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            logs.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            action = scanner.nextLine();
            logs.add(action);
            switch (action) {
                case "add":
                    new EditCard().addCard(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "remove":
                    new EditCard().removeCard(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "import":
                    new Importing().importFromFile(fileName, cards, logs, mistakes);
                    System.out.println();
                    break;
                case "export":
                    new Exporting().exportToFile(fileName, cards, logs, mistakes);
                    System.out.println();
                    break;
                case "ask":
                    new Ask().ask(cards, logs, mistakes);
                    System.out.println();
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    logs.add("Bye bye!");
                    if (!fileName.equals("noArgs")) {// если придет аргумент
                        new Exporting().exportToFile(fileName, cards, logs, mistakes);
                    }
                    break;
                case "log":
                    new Log().log(logs);
                    System.out.println();
                    break;
                case "hardest card":
                    new StatisticCard().hardestCard(logs, mistakes);
                    System.out.println();
                    break;
                case "reset stats":
                    new StatisticCard().resetStats(logs, mistakes);
                    System.out.println();
                    break;
                case "show":
                    new StatisticCard().show(cards);
                    System.out.println();
                    break;
            }
        }
    }

}
