package com.zidio_connection.repository;
import com.zidio_connection.enums.Role;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zidio_connection.entity.Users;



@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserName(String userName);
    Optional<Users> findByUserEmail(String userEmail);

    long countByRole(Role role);
}
