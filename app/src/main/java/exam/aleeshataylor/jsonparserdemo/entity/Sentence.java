package exam.aleeshataylor.jsonparserdemo.entity;

import org.jetbrains.annotations.NotNull;

public class Sentence {
    String hitokoto;
    String author;

    public Sentence() {
    }

    public Sentence(String hitokoto, String author) {
        this.hitokoto = hitokoto;
        this.author = author;
    }

    public String getHitokoto() {
        return hitokoto;
    }

    public void setHitokoto(String hitokoto) {
        this.hitokoto = hitokoto;
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
        return "“" + hitokoto + "”";
    }
}
