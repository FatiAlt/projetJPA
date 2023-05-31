package fr.demos.demoJPA.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;

@Entity
@Table(name = "article")
@Inheritance(strategy=InheritanceType.JOINED)
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Article {

    // clé générée automatiquement dans la base
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    protected Long id;

    @Column(name = "reference", nullable = false, length = 30, unique = true)
    private String ref;
    private String designation;
    private double prixHT;
    private double tva = 0.2;

    // Stock est une classe embedded, sa valeur est insérée dans l'article et il n'y a pas de table associée
    @Embedded
    @AttributeOverrides({
        @AttributeOverride( name = "nombre", column = @Column(name = "stock"))
    })
    private Stock stock;

    // Un vendeur vend un article (cas de la MarketPlace)

    @ManyToOne()
    @JoinColumn(name="vendeur_id")
    private Vendeur vendeur;

    // constructeur utilisé par Hibernate pour instancier les objets lorsqu'il ramène des données de la base, ne doit pas servir autrement
    protected Article(){}

    public Article(String ref, String designation, double prixHT) {
        this.ref = ref;
        this.designation = designation;
        this.prixHT = prixHT;
        this.stock = new Stock();
    }
    public Long getId() {
        return id;
    }

    public String getRef() {
        return ref;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Stock getStock() {
        return stock;
    }

    public double getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(double prixHT) {
        this.prixHT = prixHT;
    }

    // Pas d'attribut TTC, la valeur est calculée
    // Le prix TTC apparaît cependant dans l'objet JSON. En effet la sérialisation vers JSON utilise les getter et setter.
    // il est possible de configurer la sérialisation pour qu'elle se base sur les champs (voir doc de ObjectMapper)
    public double getPrixTTC() {
        return prixHT*(1+tva);
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public Vendeur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Vendeur vendeur) {
        this.vendeur = vendeur;
    }

    @Override
    public String toString() {
        return "Article{" +
                "ref='" + ref + '\'' +
                ", designation='" + designation + '\'' +
                ", prixHT=" + prixHT +
                ", tva=" + tva +
                '}';
    }
}
