package com.test.crud.data.repository;

import com.test.crud.data.entity.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface IncidentRepository  extends JpaRepository<IncidentEntity, Long> {

    IncidentEntity findByIdIncident(Long id);

    List<IncidentEntity> findAllByIdIncidentIsNotNullAndClosedAtIsNull();

    List<IncidentEntity> findAllByIdIncidentIsNotNullAndClosedAtIsNullOrderByIdIncidentDesc();


}
