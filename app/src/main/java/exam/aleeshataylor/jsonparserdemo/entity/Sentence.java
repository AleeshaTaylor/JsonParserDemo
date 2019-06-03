package exam.aleeshataylor.jsonparserdemo.entity;

import org.jetbrains.annotations.NotNull;

public class Sentence {
    String phrase;
    String author;

    public Sentence() {
    }

    public Sentence(String phrase) {
        this.phrase = phrase;
    }

    public Sentence(String phrase, String author) {
        this.phrase = phrase;
        this.author = author;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @NotNull
    @Override
    public String toString() {
        return "“" + phrase + "”\n\t——" + author;
    }
}
