package ru.job4j.parse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class OutputContentImpl implements OutputContent {

    private static final Logger LOG = LoggerFactory.getLogger(OutputContentImpl.class.getName());

    @Override
    public void saveContent(File file, String content) {
        try (OutputStream o = new FileOutputStream(file)) {
            for (int i = 0; i < content.length(); i += 1) {
                o.write(content.charAt(i));
            }
        } catch (IOException e) {
            LOG.error("save content error: " + e.getMessage());
        }
    }

}
