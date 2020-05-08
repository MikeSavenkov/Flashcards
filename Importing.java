package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Importing {

    public void importFromFile(String fileName,
                               Map<String, String> cards,
                               List<String> logs, Map<String,
                               Integer> mistakes) {
        Scanner scanner = new Scanner(System.in);
        File file;
        if (fileName.equals("noArgs")) {
            System.out.println("File name:");
            logs.add("File name:");
            fileName = scanner.nextLine();  //  C:\Games\file.txt
            logs.add(fileName);
        }
        file = new File(fileName);
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
}
