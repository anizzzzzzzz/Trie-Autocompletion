package com.trie.service.impl;

import com.trie.dto.TrieNode;
import com.trie.service.ITrie;

import java.util.*;

public class TrieSecond implements ITrie {
    public TrieNode root;
    private List<String> wordList;

    public TrieSecond(List<String> wordList){
        this.root = new TrieNode("");

        wordList.sort(String::compareTo);
        this.wordList = wordList;
    }

    @Override
    public void init() {
        this.wordList.forEach(line -> {
            insert(line.toLowerCase(), line);
            String[] words = line.split("\\s+");
            for(String word : words)
                insert(word, line);
        });
    }

    private void insert(String word, String line){
        TrieNode crawl = root;
        String[] charList = word.toLowerCase().split("");
        for(String aChar: charList){
            Map<String, TrieNode> child = crawl.getChildren();
            if(child.containsKey(aChar)){
                crawl = child.get(aChar);
                crawl.getWords().add(line);
            }
            else{
                TrieNode temp = new TrieNode(aChar);
                temp.getWords().add(line);
                child.put(aChar, temp);
                crawl = temp;
            }
        }
        crawl.setIsEnd(true);
    }

    @Override
    public Set<String> search(String sentence) {
        return Collections.emptySet();
    }

    @Override
    public Set<String> autoComplete(String text) {
        Set<String> words = new HashSet<>();
        String[] charList = text.split("");
        TrieNode crawl = root;
        boolean contains = true;

        for(String aChar : charList){
            Map<String, TrieNode> child = crawl.getChildren();
            if(child.containsKey(aChar)){
                crawl = child.get(aChar);
            }
            else{
                contains=false;
                break;
            }
        }
        if(contains)
            words.addAll(crawl.getWords());
        crawl = null;
        System.gc();
        return words;
    }
}
