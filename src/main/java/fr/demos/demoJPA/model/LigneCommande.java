package fr.demos.demoJPA.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ligne_commande")
public class LigneCommande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    protected Long id;
    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;
    private int quantite;
    @ManyToOne
    @JoinColumn(name="commande_id")
    private Commande commande;

    public LigneCommande() {
    }

    public LigneCommande(Article article, int quantite) {
        this.article = article;
        this.quantite = quantite;
    }


    public Long getId() {
        return id;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setCommande(Commande c) {
        this.commande=c;
    }


}
