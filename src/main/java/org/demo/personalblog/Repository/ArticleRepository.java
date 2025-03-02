package org.demo.personalblog.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.demo.personalblog.Model.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class ArticleRepository implements IArticleRepository {
    private static final String FILE_PATH = "articles.json";
    private static final Logger logger = LoggerFactory.getLogger(ArticleRepository.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Article> localArticles = new ArrayList<>();

    public ArticleRepository() {
        objectMapper.registerModules(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.localArticles = getArticles();
    }

    @Override
    public List<Article> getArticles() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists() && file.createNewFile()) {
                logger.info("Created new file: {}", FILE_PATH);
                System.out.println("File created");
                return new ArrayList<>();
            }

            Article[] articles = objectMapper.readValue(file, Article[].class);
            return new ArrayList<>(Arrays.asList(articles));
        } catch (IOException e) {
            logger.debug("Loaded articles from file");
            System.out.println("Error in file handling: " + e.getMessage());
        }

        return new ArrayList<>();
    }

    @Override
    public Article getArticleById(int id) {
        return localArticles.stream().filter(article -> article.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Article createArticle(Article article) {
        if (article == null) {
            System.out.println("Article cannot be null");
            logger.info("Article cannot be null");
            return null;
        }

        if (localArticles.contains(article)) {
            logger.info("Article already exists");
            System.out.println("Article already exists");
            return null;
        }

        article.setId(generateNewId());
        article.setPublicationDate(LocalDateTime.now());
        localArticles.add(article);
        saveChanges();

        return article;
    }

    @Override
    public boolean updateArticle(int id, Article article) {
        Article existingArticle = localArticles.stream().filter(a -> a.getId() == id).findFirst().orElse(null);

        if (existingArticle == null) {
            logger.debug("The article with id {} does not exist", id);
            System.out.println("The article with id " + id + " does not exist");
            return false;
        }

        if (existingArticle.equals(article)) {
            logger.info("Please check if you made modification to the article");
            System.out.println("Please check if you made modification to the article");
            return false;
        }

        existingArticle.setTitle(article.getTitle());
        existingArticle.setContent(article.getContent());
        existingArticle.setPublicationDate(article.getPublicationDate());
        saveChanges();
        logger.info("Article updated");
        System.out.println("Article updated");
        return true;
    }

    @Override
    public boolean deleteArticle(int id) {
        boolean isDeleted = localArticles.removeIf(a -> a.getId() == id);
        saveChanges();
        return isDeleted;
    }

    private void saveChanges() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), localArticles);
        } catch (IOException e) {
            logger.debug("Error writing to the file: {}", e.getMessage());
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }

    private int generateNewId() {
        return localArticles.stream().mapToInt(Article::getId).max().orElse(0) + 1;
    }
}
