import java.io.*;
import java.util.*;

class LogFileAnalyzer {
    private Set<String> keywords;

    public LogFileAnalyzer(Set<String> keywords) {
        this.keywords = keywords;
    }

    public void analyzeLogFile(String inputFile, String outputFile) {
        Map<String, Integer> keywordCounts = new HashMap<>();
        
        for (String keyword : keywords) {
            keywordCounts.put(keyword, 0);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                for (String keyword : keywords) {
                    if (line.contains(keyword)) {
                        keywordCounts.put(keyword, keywordCounts.get(keyword) + 1);
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }

            writer.newLine();
            writer.write("Keyword Analysis Summary:");
            writer.newLine();
            for (Map.Entry<String, Integer> entry : keywordCounts.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error reading or writing file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Set<String> keywords = new HashSet<>(Arrays.asList("ERROR", "WARNING"));
        LogFileAnalyzer analyzer = new LogFileAnalyzer(keywords);
        
        analyzer.analyzeLogFile("log.txt", "analysis_output.txt");
        System.out.println("Log analysis complete. Results saved to analysis_output.txt");
    }
}
