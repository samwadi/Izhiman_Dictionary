package com.izhiman.izhimansimpledictionary;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    Map<String, String> englishToArabic = new HashMap<>();
     Map<String, String> arabicToEnglish = new HashMap<>();

    public Dictionary() throws IOException {
        //LoadData();
    }

    // Method to add a word pair to the dictionary
    public void addWordPair(String englishWord, String arabicWord) {
        englishToArabic.put(englishWord, arabicWord);
        arabicToEnglish.put(arabicWord, englishWord);
    }

    // Method to search for the Arabic word by English word
    public String searchArabicByEnglish(String englishWord) {
        return englishToArabic.get(englishWord);
    }

    // Method to search for the English word by Arabic word
    public String searchEnglishByArabic(String arabicWord) {
        return arabicToEnglish.get(arabicWord);
    }
    public void LoadData() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("dictionary.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String englishWord = parts[0].trim();
                    String arabicWord = parts[1].trim();
                    addWordPair(englishWord, arabicWord);
                }
            }
        }
        System.out.println("data loaded");
    }
    public void saveDataToFile() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("dictionary.txt"))) {
            for (Map.Entry<String, String> entry : englishToArabic.entrySet()) {
                bw.write(entry.getKey() + ":" + entry.getValue());
                bw.newLine();
            }
        }
        System.out.println("data saved");
    }
}