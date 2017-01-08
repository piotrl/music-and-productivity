package net.piotrl.music.rescuetime.activity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActionCrudRepository extends CrudRepository<ActionEntity, Integer> {

    Optional<ActionEntity> findFirstByName(String name);

}