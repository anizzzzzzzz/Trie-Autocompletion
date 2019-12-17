package com.trie.dto;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

public class TrieNode {
    private String value;
    private HashMap<String, TrieNode> children;
    private boolean isEnd;
    private Set<String> words;

    public TrieNode(String word){
        this.value = word;
        this.children = new HashMap<>();
        this.isEnd = false;
        this.words = new LinkedHashSet<>();
    }

    public String getValue(){return value;}

    public HashMap<String, TrieNode> getChildren(){return children;}

    public boolean isEnd(){return isEnd;}

    public void setIsEnd(boolean isEnd){this.isEnd = isEnd;}

    public Set<String> getWords(){return words;}
}
