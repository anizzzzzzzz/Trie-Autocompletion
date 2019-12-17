package com.trie.service.impl;

import com.trie.dto.TrieNode;
import com.trie.service.ITrie;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TrieFirst implements ITrie {
    private TrieNode root;
    private List<String> wordList;
    private Pattern pattern = Pattern.compile("^([\\W]{0,2})(\\w)([\\w\\S]*)");

    public TrieFirst(List<String> wordList){
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

    public Set<String> search(String sentence){
        List<String> charList = Arrays.asList(sentence.split(""));
        Collections.reverse(charList);
        Set<String> matchedWords = new HashSet<>();

        Stack<String> stack = new Stack<>();
        stack.addAll(charList);

        while(!stack.empty()){
            TrieNode crawl = root;
            List<String> word = new ArrayList<>();
            int prevMatch = 0;

            for(int i=1; !stack.empty(); i++){
                String aChar = stack.pop();
                Map<String, TrieNode> child = crawl.getChildren();
                if (child.containsKey(aChar)){
                    word.add(aChar);
                    crawl = child.get(aChar);
                    if(crawl.isEnd())
                        prevMatch = i;
                }
                else break;
            }
            if(word.size() > 0){
                word = word.subList(0, prevMatch);
                String matchedWord = word.stream().reduce((s1, s2) -> s1+s2).orElse("");
                if(!matchedWord.isEmpty())
                    matchedWords.add(matchedWord);
            }
        }
        return matchedWords;
    }

    public Set<String> autoComplete(String text){
        List<String> words = new ArrayList<>();
        if(!text.isEmpty()){
            String toLowerCase = text.toLowerCase();
            String toUpperCase = text.toUpperCase();
            String toTitleCase = convertIntoTitleCase(text);

            autoComplete(text, words);
            if(!text.equals(toLowerCase))
                autoComplete(text.toLowerCase(), words);
            if(!text.equals(toUpperCase))
                autoComplete(text.toUpperCase(), words);
            if(!text.equals(toTitleCase))
                autoComplete(convertIntoTitleCase(text), words);
        }
        words.sort(String::compareTo);
        return new HashSet<>(words);
    }

    /**
     * @param text : String
     *             - text to be converted into title Case
     *             - for eg; java -> Java, JAVA -> Java
     *                       .net -> .Net, .NET -> .Net
     * */
    private String convertIntoTitleCase(String text){
        String[] words = text.split("[\\s]");
        StringBuilder sb = new StringBuilder();
        for(String aWord : words){
            Matcher matcher = this.pattern.matcher(aWord);
            if(matcher.matches()){
                StringBuilder wo = new StringBuilder();

                for(int i=1; i<=matcher.groupCount(); i++)
                    wo.append(i==2?matcher.group(i).toUpperCase():matcher.group(i).toLowerCase());

                sb.append(wo.toString()).append(" ");
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(" "));
        return sb.toString();
    }

    /**
     * @param word : String
     *             - Prefix based on which the autocompletion is carried out.
     * */
    private void autoComplete(String word, List<String> words){
        String[] charList = word.split("");
        List<String> prefix = new ArrayList<>();

        TrieNode crawl = root;
        Map<String, TrieNode> child = crawl.getChildren();
        boolean matches = true;

        for (String aChar : charList) {
            if (child.containsKey(aChar)) {
                crawl = child.get(aChar);
                child = crawl.getChildren();
                prefix.add(aChar);
            } else {
                matches = false;
                break;
            }
        }

        if(matches)
            searchForAutocomplete(crawl, words, prefix);
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
            if(!words.contains(word))
                words.add(word);
        }
        prefix.remove(prefix.size()-1);
    }
}
