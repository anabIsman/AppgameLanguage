package org.imie.appgamelanguage;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.imie.appgamelanguage.model.ReaderFileCSV;
import org.imie.appgamelanguage.model.LanguageManager;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainFrameController {
    @FXML
    private TableView<String[]> jTable1;
    @FXML
    private LanguageManager languageManager;
    private List<ComboBox<String>> comboBoxes = new ArrayList<>();
    private Score appscore;
    @FXML
    private void handleBtnChoixFichier() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier CSV");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Fichiers CSV", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            ReaderFileCSV readerFileCSV = new ReaderFileCSV(selectedFile);
            languageManager = readerFileCSV.getLanguageManager();
            TableColumn<String[], String> columnL1 = creerColonne(languageManager.getLanguage1().getName(), 0);
            TableColumn<String[], String> columnL2 = creerColonne(languageManager.getLanguage2().getName(), 1);
            ObservableList<String[]> data = FXCollections.observableArrayList();

            for (String item : languageManager.getLanguage1().getWordList()) {
                data.add(new String[]{item, ""});
            }
            ArrayList<String> aleatoireMotsLangue2 = new ArrayList<>();
            aleatoireMotsLangue2.addAll(languageManager.getLanguage2().getWordList());
            Collections.shuffle(aleatoireMotsLangue2);

            columnL2.setCellFactory(param -> new ComboBoxTableCell<>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (!empty) {
                        ComboBox<String> comboBox = new ComboBox<>();
                        comboBox.getItems().addAll(aleatoireMotsLangue2);
                        setGraphic(comboBox);
                        comboBoxes.add(comboBox);
                    }
                }
            });
            jTable1.setItems(data);
            jTable1.getColumns().addAll(columnL1, columnL2);
        }
    }

    private TableColumn<String[], String> creerColonne(String nom, int index) {
        TableColumn<String[], String> colonne = new TableColumn<>(nom);
        colonne.setCellValueFactory(param -> {
            String[] row = param.getValue();
            return row != null && row.length > index ? new SimpleStringProperty(row[index]) : null;
        });
        return colonne;
    }

    @FXML
    private void handleBtnAfficherScore() {
        ArrayList<String> selectedValues = new ArrayList<>();
        for (ComboBox<String> comboBox : comboBoxes) {
            String selectedValue = comboBox.getValue();
            if (selectedValue != null) {
                System.out.println("Valeur sélectionnée : " + selectedValue);
                selectedValues.add(selectedValue);
            }
        }
        System.out.println("Les valeurs sélectionnées : "+selectedValues);
        int nombreMotsCommuns = countCommonWords(languageManager.getLanguage2().getWordList(), selectedValues);
        System.out.println("Vous avez trouvez : " + nombreMotsCommuns+"/"+selectedValues.size());

        appscore = new Score(nombreMotsCommuns+"/"+selectedValues.size());
        Stage successStage = new Stage();
        try {
            appscore.start(successStage);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public int countCommonWords(List<String> list1, List<String> list2) {
        int count = 0;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i).equals(list2.get(i))) {
                count++;
            }
        }
        return count;
    }
}