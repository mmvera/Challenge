package com.challenge.disneyApp.repository;

import com.challenge.disneyApp.models.Characters;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CharactersRepository extends JpaRepository<Characters,Long>,
                                                JpaSpecificationExecutor<Characters> {

    Characters findByCharacterId(Long id);

    boolean existsByCharacterId(Long id);

    @Override
    List<Characters> findAll(Specification<Characters> specification);
}
