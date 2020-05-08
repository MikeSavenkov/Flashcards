package flashcards;

import flashcards.interfaces.Export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Exports implements Export {

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

    @Override
    public void exportExit (String fileName,
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
