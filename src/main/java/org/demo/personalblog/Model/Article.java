package org.demo.personalblog.Model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Article {
    private int id;
    private String title;
    private String content;
    private LocalDateTime publicationDate;

    public Article() {}

    public Article(int id, String title, String content, LocalDateTime publicationDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publicationDate = publicationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title) && Objects.equals(content, article.content) && Objects.equals(publicationDate, article.publicationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, content, publicationDate);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publicationDate=" + publicationDate +
                '}';
    }
}
