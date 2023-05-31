package fr.demos.demoJPA.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    protected Long id;

    // cascade pour entraîner également la persistance des associations
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "commande")
    private List<LigneCommande> contenu = new ArrayList<>();

    public Commande() {
    }

    // constructeur ne prenant en compte qu'un seul article
    public Commande(Article a, int qte) {
        LigneCommande lc = new LigneCommande(a,qte);
        contenu.add(lc);
        // important de faire le lien entre commande et ligne commande
        lc.setCommande(this);

    }

}
