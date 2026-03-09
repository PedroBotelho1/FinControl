package com.pedro.fincontrol.repositories;

import com.pedro.fincontrol.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
