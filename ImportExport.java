package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ImportExport implements Impex{


    @Override
    public void importFromFile(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
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

    @Override
    public void exportToFile(Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
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
}
