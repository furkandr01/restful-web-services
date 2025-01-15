package com.furkanadar.rest.webservices.restfull_web_services.repository;

import com.furkanadar.rest.webservices.restfull_web_services.model.Work;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkRepository extends JpaRepository<Work, Long> {
    List<Work> findByAuthorId(Long authorID);
}