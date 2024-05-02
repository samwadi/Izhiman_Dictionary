package com.izhiman.izhimansimpledictionary;

import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.IOException;

public class HelloController {
    @FXML
    private Label welcomeText,addingText;
    //public Dictionary dictionary;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

  //  @FXML
    //private Label welcomeText;

    public HelloController() throws IOException {
    }
    @FXML
    private TextField englishWordInput;

    @FXML
    private TextField arabicWordInput;

    @FXML
    private TextField searchInput;
    @FXML
    private Pane backgroundPane;
    @FXML
    private TableView<WordPair> dictionaryTable;

    private final Dictionary dictionary = new Dictionary();

    private final ObservableList<WordPair> wordPairs = FXCollections.observableArrayList();

    public void addWord(ActionEvent event) {
        String englishWord = englishWordInput.getText().trim();
        String arabicWord = arabicWordInput.getText().trim();
        if (!englishWord.isEmpty() && !arabicWord.isEmpty()) {
            dictionary.addWordPair(englishWord, arabicWord);
            englishWordInput.clear();
            arabicWordInput.clear();
            try {
                dictionary.saveDataToFile();
                addingText.setText("Word added successfully to dictionary!");
            } catch (IOException e) {
                System.err.println("Error saving data to file: " + e.getMessage());
                addingText.setText("Error saving data to file");
            }
            loadData();
        }else {
            addingText.setText("Please enter valid data!");
        }
    }

    public void searchWord(ActionEvent event) {
        String query = searchInput.getText().trim();
        if (!query.isEmpty()) {
            String englishTranslation = dictionary.searchEnglishByArabic(query);
            String arabicTranslation = dictionary.searchArabicByEnglish(query);
            if (arabicTranslation != null) {
                wordPairs.clear();
                wordPairs.add(new WordPair(query, englishTranslation));
            } else if (englishTranslation != null) {
                wordPairs.clear();
                wordPairs.add(new WordPair(arabicTranslation, query));
            } else {
                wordPairs.clear();
            }
        } else {
            loadData();
        }
    }

    @FXML
    public void initialize() {
//          if (dictionaryTable.getColumns().isEmpty()) { // Check if columns are already added

        TableColumn<WordPair, String> englishColumn = new TableColumn<>("English");
        englishColumn.setCellValueFactory(new PropertyValueFactory<>("english"));
        TableColumn<WordPair, String> arabicColumn = new TableColumn<>("Arabic");
        arabicColumn.setCellValueFactory(new PropertyValueFactory<>("arabic"));

        dictionaryTable.getColumns().addAll(englishColumn, arabicColumn);
        loadData();
        dictionaryTable.setItems(wordPairs);

      //  }
        ////////////////////////////////////////
        // Create a rectangle for the moving object
        Rectangle object = new Rectangle(50, 15, javafx.scene.paint.Color.RED);

        // Add the object to the backgroundPane
        backgroundPane.getChildren().add(object);

        // Create a TranslateTransition to move the object horizontally
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(3), object);
        translateTransition.setFromX(-50);
        translateTransition.setToX(85); // Width of the backgroundPane
        translateTransition.setCycleCount(javafx.animation.Animation.INDEFINITE); // Repeat indefinitely
        translateTransition.setAutoReverse(false);

        // Start the animation
        translateTransition.play();
    }

    private void loadData() {
        try {
            dictionary.LoadData();
            wordPairs.clear();
            for (String englishWord : dictionary.englishToArabic.keySet()) {
                String arabicWord = dictionary.englishToArabic.get(englishWord);
                wordPairs.add(new WordPair(englishWord, arabicWord));
            }
        } catch (IOException e) {
            System.err.println("Error loading data from file: " + e.getMessage());
        }
    }

    public static class WordPair {
        private final String english;
        private final String arabic;

        public WordPair(String english, String arabic) {
            this.english = english;
            this.arabic = arabic;
        }

        public String getEnglish() {
            return english;
        }

        public String getArabic() {
            return arabic;
        }
    }
}