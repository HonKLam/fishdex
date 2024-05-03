package dev.hklm.fdbackend.Repositories;

import dev.hklm.fdbackend.Entities.Diary;
import org.springframework.data.repository.CrudRepository;

/**
 * Datenbank-Tabelle für Timeline
 */
public interface DiaryRepository extends CrudRepository<Diary, Long> {
    Diary findById(long id);

}
