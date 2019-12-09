package com.trie.controller;

import com.trie.dto.Skill;
import com.trie.service.ITrie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TrieRestController {
    private final ITrie trie;

    @Autowired
    public TrieRestController(ITrie trie) {
        this.trie = trie;
    }

    @PostMapping("/search")
    public List<String> searchSkill(@RequestBody Skill skill){
        return trie.search(skill.getSkill());
    }

    @PostMapping("/autocomplete")
    public List<String> autoComplete(@RequestBody Skill skill){
        return trie.autoComplete(skill.getSkill());
    }
}
