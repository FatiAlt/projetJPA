package fr.demos.demoJPA.controller;

import fr.demos.demoJPA.dao.ArticleRepository;
import fr.demos.demoJPA.exceptions.StockException;
import fr.demos.demoJPA.model.Article;
import fr.demos.demoJPA.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/commandes")
public class CommandeController {
    @Autowired
    private DemoService service;

    @Autowired
    private ArticleRepository rep;

    // essai de la création de commande
    // code de test avec System.out.println (à corriger avec vrai site web)
    @PostMapping()
    public void essaiCreationCommande() {
        System.out.println("appel création commande");
        // première commande devant échouer car stock insuffisant
        Optional<Article> a1 = rep.findById(3L);
        if (a1.isPresent()) {
            try {
                System.out.println("commande de "+a1.get().getDesignation());
                service.creeCommande(a1.get(), 7);
            }
            catch (StockException ex){
                System.out.println(ex.getMessage());
            }
        }
        else {
            System.out.println("pb optional 1");
        }
        // deuxième commande correcte
        Optional<Article> a2 = rep.findById(2L);
        if (a2.isPresent()) {
            try {
                System.out.println("commande de "+a2.get().getDesignation());
                service.creeCommande(a2.get(), 1);
            }
            catch (StockException ex){
                System.out.println(ex.getMessage());
            }
        }
        else {
            System.out.println("pb optional 2");
        }
    }
}