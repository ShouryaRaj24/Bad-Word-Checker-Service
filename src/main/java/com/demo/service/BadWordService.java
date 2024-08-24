package com.demo.service;


import com.demo.exception.BadWordLoadException;
import com.demo.exception.CheckSentenceException;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Data
@Slf4j
public class BadWordService {

    @Value("${badword.file.location}")
    private String BAD_WORD_FILE_LOCATION;

    private Set<String> badWordSet = null;

    @PostConstruct
    private void loadAtStartup() {
        // Load Bad Words right after bean is created.
        // can't be done in contstrutor since we need the file location from the @Value
        try {
            loadBadWords();
        }
        catch (BadWordLoadException e) {
            log.error("loadAtStartup BadWordLoadException {}", e.getMessage());
        }
    }


    public void loadBadWords() throws BadWordLoadException {
        try {
            // Read all the words from the file, each word is on its own line
            List<String> badWordList = Files.readAllLines(Paths.get(BAD_WORD_FILE_LOCATION));

            // Concert all words to lowerCase
            List <String> lowercaseBadWordList = badWordList.stream().map(String::toLowerCase).collect(Collectors.toList());

            // Make this a Set not a List, this will reduce duplicates
            badWordSet = Set.copyOf(lowercaseBadWordList);

            log.info("loadBadWords found " + badWordSet.size() + " bad words");

         }
        catch (Exception e) {
            log.error("loadBadWords Exception reading bad words {}", e.getMessage());

            throw new BadWordLoadException("loadBadWords Exception reading bad words e: " + e.getMessage());
        }

    }

    public Set checkSentence(String sentence) throws CheckSentenceException {
        log.info("checkSentence started. sentence: {}", sentence);

        try {
            // Convert incoming sentence to lowerCase and check againsts bad words
            return containsWords(sentence.toLowerCase(), badWordSet);
        }
        catch (Exception e) {
            throw new CheckSentenceException("Failed to check sentence");
        }
    }

    private Set containsWords(String sentence, Set<String> badWords) throws Exception {

        log.info("containsWords started.");

        // If we have any bad words then there can't be a match
        if (badWordSet == null) {
            return new HashSet<String>();
        }

        Set<String> wordsInSentence = new HashSet<>(Arrays.asList(sentence.split(" ")));

        log.info("containsWords wordsInSentence size: {}", wordsInSentence.size());
        log.info("containsWords badWords size: {}", badWords.size());

        Set<String> matches = badWords.stream().filter(e -> wordsInSentence.contains(e)).collect(Collectors.toSet());

        log.info("containsWords matches size: {}", matches.size());

        log.info("containsWords matches {}", matches.toString());

        return matches;
    }

}
