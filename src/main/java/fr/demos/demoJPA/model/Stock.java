package fr.demos.demoJPA.model;

import fr.demos.demoJPA.exceptions.StockException;
import jakarta.persistence.Embeddable;

@Embeddable
public class Stock {
    private int nombre;

    public int getNombre() {
        return nombre;
    }

    public void incremente(int ajout) {
        nombre += ajout;
    }

    public void decremente(int ajout) throws StockException {
        if (ajout <= nombre) {
            nombre -= ajout;
        } else {
            throw new StockException("stock insuffisant");
        }
    }

}
