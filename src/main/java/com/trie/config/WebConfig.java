package com.trie.config;

import com.trie.service.ITrie;
import com.trie.service.impl.TrieFirst;
import com.trie.service.impl.TrieSecond;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class WebConfig {
    @Qualifier("trieFirst")
    @Bean
    public ITrie trieFirst() {
        ITrie trie = new TrieFirst(readLines(System.getProperty("user.dir") + "/data/skill_dictionary.txt"));
        trie.init();
        return trie;
    }

    @Qualifier("trieSecond")
    @Bean
    public ITrie trieSecond(){
        ITrie trie = new TrieSecond(readLines(System.getProperty("user.dir") + "/data/skill_dictionary.txt"));
        trie.init();
        return trie;
    }

    private List<String> readLines(String path) {
        try (FileInputStream inputStream = new FileInputStream(path)) {
            return Arrays.stream(IOUtils.toString(inputStream, "UTF-8").split("\n"))
                    .map(String::trim).collect(Collectors.toList());
        } catch (IOException ex) {
            return Collections.emptyList();
        }
    }
}
