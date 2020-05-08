package flashcards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Exporting {

    public void exportToFile(String fileName, Map<String, String> cards, List<String> logs, Map<String, Integer> mistakes) {
        Scanner scanner = new Scanner(System.in);
        File file;
        if (fileName.equals("noArgs")) {
            System.out.println("File name:");
            logs.add("File name:");
            fileName = scanner.nextLine();  // C:\Games\file.txt
            logs.add(fileName);
            file = new File(fileName);
        }
        file = new File(fileName);
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
