package ru.job4j.parse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.function.Predicate;

public class InputContentImpl implements InputContent {

    private static final Logger LOG = LoggerFactory.getLogger(InputContentImpl.class.getName());

    @Override
    public String getContent(File file, Predicate<Integer> filter) {
        String output = "";
        try (InputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test(data)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            LOG.error("get content without unicode error: " + e.getMessage());
        }
        return output;
    }

}
