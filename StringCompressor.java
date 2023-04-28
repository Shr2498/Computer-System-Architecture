import java.io.*;

public class StringCompressor {

    public static String compress(String str) {
        StringBuilder compressed = new StringBuilder();

        int[] asciiTable = new int[256]; // ASCII table has 256 characters
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            asciiTable[c]++;
        }

        for (int i = 0; i < asciiTable.length; i++) {
            if (asciiTable[i] > 0) {
                compressed.append(i); // append the ASCII value
                compressed.append(asciiTable[i]); // append its count
            }
        }

        return compressed.toString();
    }

    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String compressed = compress(line);
                System.out.println("Compressed: " + compressed);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }
}
