package dev.hklm.fdbackend.Repositories;

import dev.hklm.fdbackend.Entities.TestEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<TestEntity, Long> {
    TestEntity findById(long id);
}
