package com.trie.config;

import com.trie.service.Trie;
import org.apache.commons.io.IOUtils;
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
    @Bean
    public Trie trie() {
        Trie trie = new Trie(readLines(System.getProperty("user.dir") + "/data/skill_dictionary.txt"));
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

    public List<String> skills() {
        return readLines(System.getProperty("user.dir") + "/data/skill_dictionary.txt");
    }
}
