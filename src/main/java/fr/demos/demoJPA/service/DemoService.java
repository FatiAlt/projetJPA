package fr.demos.demoJPA.service;

import fr.demos.demoJPA.dao.ArticleRepository;
import fr.demos.demoJPA.dao.CommandeRepository;
import fr.demos.demoJPA.exceptions.StockException;
import fr.demos.demoJPA.model.Article;
import fr.demos.demoJPA.model.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DemoService {
    @Autowired
    private ArticleRepository repArticle;
    @Autowired
    private CommandeRepository repCommande;


    public Article rechercheRef(String ref){
        return repArticle.findByRef(ref);
    }

    public List<Article> rechercheTexte(String texte){
        return repArticle.findByDesignationContains(texte);
    }

    public List<Article> recherchePrix(double prixMini, double prixMaxi){
        return repArticle.findAllBetweenPrix(prixMini,prixMaxi);
    }
    // encore en état de création : pour l'instant 1 seul article
    // rollback si runtime exception. On indique ici qu'il faut aussi faire un rollback en cas de StockException
    // meilleur endroit pour gérer les transactions que dans les repository, permet de rassembler dans la
    // transaction la sauvegarde de la commande et de l'article
    @Transactional(rollbackFor = StockException.class)
    public void creeCommande(Article a, int qte) throws StockException {
        Commande c = new Commande(a,qte);
        repCommande.save(c);
        // on complète l'exception avec la désignation de l'article
        try {
            a.getStock().decremente(qte);
        }
        catch(StockException ex){
            throw new StockException(ex.getMessage() + " " + a.getDesignation());
        }
        repArticle.save(a);


    }

}
