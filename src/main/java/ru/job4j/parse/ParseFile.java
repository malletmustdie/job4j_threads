package ru.job4j.parse;

import java.io.File;
import java.util.function.Predicate;

public class ParseFile {

    private final File file;
    private final InputContent inputContent;
    private final OutputContent outputContent;

    public ParseFile(File file, InputContent inputContent, OutputContent outputContent) {
        this.file = file;
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
