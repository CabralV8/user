package com.proj.user.infrastructure.repositories;


import com.proj.user.infrastructure.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // Anotação para indicar que essa interface é um componente de repositório
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    /**
     * Método de consulta por e-mail. Usado para autenticação do usuário.
     * O Spring Data JPA cria a implementação automaticamente com base no nome do método.
     *
     * @param email e-mail do usuário
     * @return Optional com o usuário, se encontrado
     */
    Optional<User> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}