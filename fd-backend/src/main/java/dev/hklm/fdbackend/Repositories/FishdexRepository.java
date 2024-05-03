package dev.hklm.fdbackend.Repositories;

import dev.hklm.fdbackend.Entities.Fishdex;
import org.springframework.data.repository.CrudRepository;

/**
 * Datenbank-Tabelle für Fishdex
 */
public interface FishdexRepository extends CrudRepository<Fishdex, Long> {
    Fishdex findById(long id);
}
