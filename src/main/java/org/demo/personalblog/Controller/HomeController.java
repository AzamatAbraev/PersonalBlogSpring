package org.demo.personalblog.Controller;

import org.demo.personalblog.Model.Article;
import org.demo.personalblog.Repository.IArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    private final IArticleRepository articleRepository;

    @Autowired
    public HomeController(IArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping("/create")
    public String displayArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "create";
    }

    @PostMapping("/create")
    public String createArticle(@ModelAttribute Article article, Model model) {
        articleRepository.createArticle(article);
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String showResultPage(Model model) {
        model.addAttribute("articles", articleRepository.getArticles());
        return "home";
    }

    @PostMapping("/update")
    public String updateArticle(@ModelAttribute Article article) {
        articleRepository.updateArticle(article.getId(), article);
        return "redirect:/home";
    }

    @GetMapping("/edit/{id}")
    public String displayUpdateForm(@PathVariable int id, Model model) {
        model.addAttribute("article", articleRepository.getArticleById(id));
        return "update";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteArticle(@PathVariable int id) {
        articleRepository.deleteArticle(id);
        return "redirect:/home";
    }

    @GetMapping("/article/{id}")
    public String viewArticleDetails(@PathVariable int id, Model model) {
        Article article = articleRepository.getArticleById(id);
        model.addAttribute("article", article);
        return "details";
    }
}
