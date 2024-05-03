package dev.hklm.fdbackend.Repositories;

import dev.hklm.fdbackend.Entities.Userboard;
import org.springframework.data.repository.CrudRepository;

/**
 * Datenbank-Tabelle für User
 */
public interface UserboardRepository extends CrudRepository<Userboard, Long> {
    Userboard findById(long id);

}