package com.registroPonto.registroPonto.repositories;

import com.registroPonto.registroPonto.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsersRespository extends JpaRepository<Users, UUID> {


    Optional<Users> findByEmail(String email);

    Optional<Users> findById(UUID uuid);
    List<Users> findByName(String name);
}
