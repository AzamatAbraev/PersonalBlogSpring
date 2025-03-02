package org.demo.personalblog.Repository;

import org.demo.personalblog.Model.Article;

import java.util.List;

public interface IArticleRepository {
    List<Article> getArticles();
    Article getArticleById(int id);
    Article createArticle(Article article);
    boolean updateArticle(int id, Article article);
    boolean deleteArticle(int id);
}
