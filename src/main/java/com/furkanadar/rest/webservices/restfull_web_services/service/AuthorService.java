package com.furkanadar.rest.webservices.restfull_web_services.service;

import com.furkanadar.rest.webservices.restfull_web_services.model.Author;
import com.furkanadar.rest.webservices.restfull_web_services.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(String name, String authorKey) {
        // Eğer aynı authorKey daha önce kayıtlıysa kaydetme
        Optional<Author> existingAuthor = authorRepository.findByName(name);
        if (existingAuthor.isPresent()) {
            return existingAuthor.get();
        }

        Author author = new Author();
        author.setName(name);  // Author name'i kaydet
        author.setAuthorKey(authorKey);  // AuthorKey'i kaydet
        return authorRepository.save(author);
    }
}
