package com.proj.user.infrastructure.repositories;

import com.javanauta.project.infraestructure.entity.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, Long> {

}
