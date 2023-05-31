package fr.demos.demoJPA.dao;


import fr.demos.demoJPA.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

}
