import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public List<String> loadWords(String filePath) {
        List<String> words = new ArrayList<>();
        File file = new File(filePath);
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        return words;
    }
}
