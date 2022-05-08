package com.example.cinema.repository;

import com.example.cinema.entity.Show;
import com.example.cinema.security.entity.User;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, DynamicType.Builder.FieldDefinition.Optional<Show> {
    User findUserByUsername(String username);
    boolean existsUserByUsernameAndPassword(String username, String password);
}
