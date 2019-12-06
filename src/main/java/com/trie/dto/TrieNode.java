package com.trie.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrieNode {
    private String value;
    private HashMap<String, TrieNode> children;
    private boolean isEnd;

    public TrieNode(String word){
        this.value = word;
        this.children = new HashMap<>();
        this.isEnd = false;
    }

    public String getValue(){return value;}

    public HashMap<String, TrieNode> getChildren(){return children;}

    public void setChildren(HashMap<String, TrieNode> children){
        this.children = children;
    }

    public boolean isEnd(){return isEnd;}

    public void setIsEnd(boolean isEnd){this.isEnd = isEnd;}

    public List<String> getWords(){
        List<String> list = new ArrayList<>();
        if(isEnd)
            list.add(value);

        if(children !=null){
            for(String aChar: children.keySet()){
                if(children.get(aChar) != null){
                    children.get(aChar).getWords();
                }
            }
        }
        return list;
    }
}
