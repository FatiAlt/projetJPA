package fr.demos.demoJPA.dao;

import fr.demos.demoJPA.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    // requête générée à partir du nom de la méthode
    Article findByRef(String ref);

    // requête générée à partir du nom de la méthode
    List<Article> findByDesignationContains(String texte);

    // requête exprimée en JPQL
    @Query("SELECT a FROM Article a WHERE a.prixHT > ?1 and a.prixHT < ?2")
    List<Article> findAllBetweenPrix(double prixMini, double prixMaxi);

}