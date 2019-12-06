package com.trie.service;

import com.trie.dto.TrieNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trie implements ITrie {
    private TrieNode root;
    private List<String> wordList;

    public Trie(List<String> wordList){
        this.root = new TrieNode("");

        wordList.sort(String::compareTo);
        this.wordList = wordList;
    }

    public void init(){
        for(String word : this.wordList){
            String[] charList = word.split("");
            TrieNode crawl = root;
            for(String aChar : charList){
                Map<String, TrieNode> child = crawl.getChildren();
                if(child.containsKey(aChar))
                    crawl = child.get(aChar);
                else{
                    TrieNode temp = new TrieNode(aChar);
                    child.put(aChar, temp);
                    crawl = temp;
                }
            }
            crawl.setIsEnd(true);
        }
    }

    public List<String> search(String word){
        String[] charList = word.split("");
        List<String> words = new ArrayList<>();

        TrieNode crawl = root;
        Map<String, TrieNode> child = crawl.getChildren();
        for(int i = 0; i<charList.length; i++){
            if(child.containsKey(charList[i])){
                crawl = child.get(charList[i]);
                child = crawl.getChildren();

                if(crawl.isEnd() && i == (charList.length-1))
                    words.add(word.substring(0, i+1));
            }
        }
        return words;
    }

    public List<String> autoComplete(String word){
        String[] charList = word.split("");
        List<String> words = new ArrayList<>();
        List<String> prefix = new ArrayList<>();

        TrieNode crawl = root;
        Map<String, TrieNode> child = crawl.getChildren();
        boolean match = true;

        for(String aChar : charList){
            if(child.containsKey(aChar)){
                crawl = child.get(aChar);
                child = crawl.getChildren();
                prefix.add(aChar);
            }
            else{
                match = false;
                break;
            }
        }
        if(match)
            searchForAutocomplete(crawl, words, prefix);

        return words;
    }

    /**
     * @param node : TrieNode (Object)
     *             - Trie structured data based on character
     *
     * @param words : List<String>
     *              - List of word
     *              - The word will be added to this list.
     *              - These words will be used for autocompletion.
     *
     * @param prefix : List<String>
     *               - List of characters
     *               - At first, it will contain the matched prefix which is entered by user. Autocompletion is initialized based on these prefix.
     *               - The prefix is added to the character after each node. Due to the difficulty in capturing the
     *               - The autocompletion is detected by using recursive method.
     *               - At the end of method, the last indexed prefix is removed from prefix list.
     *               - This removal step is necessary to maintain the list of prefix characted before the child-nodes.
     * */
    private void searchForAutocomplete(TrieNode node, List<String> words, List<String> prefix){
        for(Map.Entry<String, TrieNode> child : node.getChildren().entrySet()){
            prefix.add(child.getKey());
            searchForAutocomplete(child.getValue(), words, prefix);
        }

        if(node.isEnd()){
            String word = prefix.stream().reduce((s1, s2) -> s1 + s2).orElse("");
            words.add(word);
        }
        prefix.remove(prefix.size()-1);
    }
}
