package ru.job4j.io;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Maxim Efimov"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBadParameter() {
        String path = "./data/bad_parameter.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenPairWithCommentAndEmptyLines() {
        String path = "./data/pair_with_comment_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Maxim Efimov"));
        assertThat(config.getNumberOfValues(), is(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutSeparator() {
        String path = "./data/pair_without_separator.properties";
        Config config = new Config(path);
        config.load();
    }
}