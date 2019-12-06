package com.trie.controller;

import com.trie.service.ITrie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrieRestController {
    private final ITrie trie;

    @Autowired
    public TrieRestController(ITrie trie) {
        this.trie = trie;
    }

    @GetMapping("/search")
    public List<String> searchSkill(@RequestParam("query") String text){
        return trie.search(text);
    }

    @GetMapping("/autocomplete")
    public List<String> autoComplete(@RequestParam("query") String text){
        return trie.autoComplete(text);
    }
}
