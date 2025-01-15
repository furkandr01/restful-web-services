package com.furkanadar.rest.webservices.restfull_web_services.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.furkanadar.rest.webservices.restfull_web_services.model.Author;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}