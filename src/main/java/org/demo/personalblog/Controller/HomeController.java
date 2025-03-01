package org.demo.personalblog.Controller;

import org.demo.personalblog.Model.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    private List<Article> articles = new ArrayList<>();

    @GetMapping("/home")
    public String displayArticles() {
        return "home";
    }

    @GetMapping("/create")
    public String displayArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "articleForm";
    }

    @PostMapping("/create")
    public String processArticleForm(@ModelAttribute Article article, Model model) {
        article.setId(articles.size() + 1);
        articles.add(article);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String showResultPage(Model model) {
        model.addAttribute("articles", articles);
        return "result";
    }
}
