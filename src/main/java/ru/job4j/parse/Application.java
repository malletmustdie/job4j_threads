package ru.job4j.parse;

import java.io.File;

public class Application {

    public static void main(String[] args) {
        File inputFile = new File("src/main/resources/input-test.txt");
        File outputFile = new File("src/main/resources/output-test.txt");
        InputContent inputContent = new InputContentImpl();
        OutputContent outputContent = new OutputContentImpl();
        ParseFile parser = new ParseFile(inputFile, inputContent, outputContent);

        showInputContent(parser, inputFile);
        showInputContentWithoutUnicode(parser, inputFile);

        String output = "Some output for file";
        saveToFile(parser, outputFile, output);
    }

    private static void showInputContent(ParseFile parser, File inputFile) {
        String content = getInputContent(parser, inputFile);
        System.out.println(content);
    }

    private static void showInputContentWithoutUnicode(ParseFile parser, File inputFile) {
        String content = getInputContentWithoutUnicode(parser, inputFile);
        System.out.println(content);
    }

    private static void saveToFile(ParseFile parser, File file, String content) {
        parser.saveContent(content, file);
    }

    private static String getInputContent(ParseFile parser, File inputFile) {
        return parser.getContent(inputFile, data -> true);
    }

    private static String getInputContentWithoutUnicode(ParseFile parser, File inputFile) {
        return parser.getContent(inputFile, data -> data < 0x80);
    }

}
