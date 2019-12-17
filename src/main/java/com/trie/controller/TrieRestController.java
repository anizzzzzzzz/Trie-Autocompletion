package com.trie.controller;

import com.trie.dto.Skill;
import com.trie.service.ITrie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class TrieRestController {
    private final ITrie trieFirst;
    private final ITrie trieSecond;

    @Autowired
    public TrieRestController(@Qualifier("trieFirst") ITrie trieFirst, @Qualifier("trieSecond") ITrie trieSecond) {
        this.trieFirst = trieFirst;
        this.trieSecond = trieSecond;
    }

    @PostMapping("/search")
    public Set<String> searchSkillTrieFirst(@RequestBody Skill skill){
        return trieFirst.search(skill.getSkill());
    }

    @PostMapping("/autocomplete-first")
    public Set<String> autoCompleteTrieFirst(@RequestBody Skill skill){
        return trieFirst.autoComplete(skill.getSkill());
    }

    @PostMapping("/autocomplete-second")
    public Set<String> autoCompleteTrieSecond(@RequestBody Skill skill){
        return trieSecond.autoComplete(skill.getSkill());
    }
}
