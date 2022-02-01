package ru.job4j.parse;

import java.io.File;
import java.util.function.Predicate;

public class ParseFile {

    private final InputContent inputContent;
    private final OutputContent outputContent;

    public ParseFile(InputContent inputContent, OutputContent outputContent) {
        this.inputContent = inputContent;
        this.outputContent = outputContent;
    }

    public String getContent(File file, Predicate<Integer> filter) {
        return inputContent.getContent(file, filter);
    }

    public void saveContent(String content, File file) {
        outputContent.saveContent(file, content);
    }

}
