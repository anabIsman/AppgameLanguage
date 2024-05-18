package org.imie.appgamelanguage.model;

import java.util.ArrayList;
import java.util.List;

public class Language {
    private String name; // name = un langage est identifier par son nom
    private List<String> wordList; // wordlist est notre tab qui va stocker les mots

    public Language(String name) { // constructeur
        this.name = name; // initialise notre attribut
        wordList = new ArrayList<>();
    }

    public void addWord(String word) {
        wordList.add(word);
        wordList.indexOf(word);
    }

    public String getName() {
        return name;
    }

    public List<String> getWordList() {
        return wordList;
    }
}
