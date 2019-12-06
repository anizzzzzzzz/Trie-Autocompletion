package com.trie.service;

import java.util.List;

public interface ITrie {
    /**
     * Initializing the trie structure data.
     * */
    void init();

    /**
     * @param word : String
     *             - Search a word in dictionary
     *             - will return exact word if the given word is present in dictionary.
     *
     * @return : List<String>
     *     - List of words
     * */
    List<String> search(String word);

    /**
     * @param word : String
     *             - Prefix based on which the autocompletion is carried out.
     *
     * @return : List<String>
     *     - List of words
     * */
    List<String> autoComplete(String word);
}
