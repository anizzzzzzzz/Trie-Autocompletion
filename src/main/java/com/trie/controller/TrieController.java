package com.trie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrieController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/trie-one")
    public String trieFirst(){
        return "trie-first";
    }

    @GetMapping("/trie-two")
    public String trieSecond(){
        return "trie-second";
    }
}
