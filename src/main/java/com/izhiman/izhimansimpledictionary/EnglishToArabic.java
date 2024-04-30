package com.izhiman.izhimansimpledictionary;

import java.util.Map;

public class EnglishToArabic {
    private Map<String,String> dictionary;
    private String ArabicWord;
    private String EnglishWord;
    // loud data from file and add data to file
    // when asked for world loud from map


    public EnglishToArabic() {
    }

    public EnglishToArabic(Map<String, String> dictionary, String arabicWord, String englishWord) {
        this.dictionary = dictionary;
        ArabicWord = arabicWord;
        EnglishWord = englishWord;
    }

    public Map<String, String> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<String, String> dictionary) {
        this.dictionary = dictionary;
    }

    public String getArabicWord() {
        return ArabicWord;
    }

    public void setArabicWord(String arabicWord) {
        ArabicWord = arabicWord;
    }

    public String getEnglishWord() {
        return EnglishWord;
    }

    public void setEnglishWord(String englishWord) {
        EnglishWord = englishWord;
    }
    boolean AddToFile(String en,String ar){
        return true;
    }
    void LoadMap(){
        //get file which is 2 wrods format and : in between
        //first is english second is arabic
        //split by ":"
        //add to map
        //load on start
    }
}
