package fr.demos.demoJPA.controller;

import fr.demos.demoJPA.dao.ArticleRepository;
import fr.demos.demoJPA.model.Article;
import fr.demos.demoJPA.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private DemoService service;

    @Autowired
    private ArticleRepository rep;


    @GetMapping()
    public List<Article> rechercheTous(){
        return rep.findAll();
    }

    // recherche en fonction de la référence (avec un paramètre de type ?ref=)
    @GetMapping(params = {"ref"})
    public Article rechercheParRef(@RequestParam String ref){
        return service.rechercheRef(ref);
    }

    // recherche des articles dans un intervalle de prix (avec deux paramètres de type ?prixMini= & prixMaxi=)
    @GetMapping(params = {"prixMini, prixMaxi"})
    public List<Article> rechercheParTranchedePrix(@RequestParam double prixMini, @RequestParam double prixMaxi){
        return service.recherchePrix(prixMini, prixMaxi);
    }

    // recherche en fonction de l'identifiant BD (avec indication dans le path /id)
    @GetMapping(path = {"/{id}"})
    public <Optional> java.util.Optional<Article> rechercheParId(@PathVariable  Long id){
        return rep.findById(id);
    }

    // recherche en fonction d'une partie de la désignation (avec un paramètre de type ?designation=)
    @GetMapping(params = {"designation"})
    public List<Article> rechercheParDesignation(@RequestParam String designation){
        return service.rechercheTexte(designation);
    }






}
