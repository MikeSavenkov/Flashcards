package flashcards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Log {

    void log(List<String> logs) {
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
}
