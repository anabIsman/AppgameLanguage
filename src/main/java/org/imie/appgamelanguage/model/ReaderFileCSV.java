package org.imie.appgamelanguage.model;

import java.io.*;

public class ReaderFileCSV {
    private File file;
    private LanguageManager languageManager;

    public ReaderFileCSV(File file) {
        this.file = file;
        try {
            this.readFile();
        } catch (Exception e) {
            System.out.println("Erreur : "+e);
        }
    }

    private void readFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String[] titles = reader.readLine().split(";");
            Language language1 = new Language(titles[0]);
            Language language2 = new Language(titles[1]);
            languageManager = new LanguageManager(language1, language2);
            String line; // variable qui contient une ligne
            while ((line = reader.readLine()) != null){
                String[] words = line.split(";");
                language1.addWord(words[0]);
                language2.addWord(words[1]);
            }
        } catch (Exception e) {
            System.out.println("Erreur de la lecture du fichier");
        }
    }

    public LanguageManager getLanguageManager() {
        return languageManager;
    }
}
