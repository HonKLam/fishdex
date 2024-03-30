package dev.hklm.fdbackend.Repositories;

import dev.hklm.fdbackend.Entities.Fishdex;
import org.springframework.data.repository.CrudRepository;

public interface FishdexRepository extends CrudRepository<Fishdex, Long> {
    Fishdex findById(long id);
}
