package ru.job4j.parse;

import java.io.File;
import java.util.function.Predicate;

public interface InputContent {

    String getContent(File file, Predicate<Integer> filter);

}
