package com.trie.service;

import java.util.Set;

public interface ITrie {
    /**
     * Initializing the trie structure data.
     * */
    void init();

    /**
     * @param sentence : String
     *             - Search a word in dictionary
     *             - will return exact word if the given word is present in dictionary.
     *
     * @return : List<String>
     *     - List of words
     * */
    Set<String> search(String sentence);

    /**
     * @param text : String
     *             - Prefix based on which the autocompletion is carried out.
     *             - In order to make the autocompletion case insensitive, the user specified prefix is changed into
     *                  lower case, upper case and title case based on which it searches the tree.
     *
     * @return : List<String>
     *     - List of words
     * */
    Set<String> autoComplete(String text);
}
