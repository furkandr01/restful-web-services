package com.furkanadar.rest.webservices.restfull_web_services.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.furkanadar.rest.webservices.restfull_web_services.dto.WorkDTO;
import com.furkanadar.rest.webservices.restfull_web_services.model.Author;
import com.furkanadar.rest.webservices.restfull_web_services.service.AuthorService;
import com.furkanadar.rest.webservices.restfull_web_services.service.WorkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final RestTemplate restTemplate;
    private final AuthorService authorService;
    private final WorkService workService;
    private final ObjectMapper objectMapper;

    public AuthorController(RestTemplate restTemplate, AuthorService authorService, WorkService workService, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.authorService = authorService;
        this.workService = workService;
        this.objectMapper = objectMapper;
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchAuthor(@RequestParam String name) {
        String apiUrl = "https://openlibrary.org/search/authors.json?q=" + name;

        try {
            String response = restTemplate.getForObject(apiUrl, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("API çağrısı başarısız: " + e.getMessage());
        }
    }

    @GetMapping("/{authorKey}/works")
    public ResponseEntity<List<WorkDTO>> getAuthorWorks(@PathVariable String authorKey) {
        String apiUrl = "https://openlibrary.org/authors/" + authorKey + "/works.json";
        String authorNameApiUrl = "https://openlibrary.org/authors/" + authorKey + ".json";

        try {
            String authorResponse = restTemplate.getForObject(authorNameApiUrl, String.class);
            String authorName = objectMapper.readTree(authorResponse).get("name").asText();

            String response = restTemplate.getForObject(apiUrl, String.class);
            List<WorkDTO> works = parseWorks(response);

            Author author = authorService.saveAuthor(authorName, authorKey);
            for (WorkDTO workDTO : works) {
                workService.saveWork(workDTO.getTitle(), author.getId());
            }

            return ResponseEntity.ok(works);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    private List<WorkDTO> parseWorks(String jsonResponse) throws Exception {
        return objectMapper.readValue(
                objectMapper.readTree(jsonResponse).get("entries").toString(),
                new TypeReference<List<WorkDTO>>() {}
        );
    }
}
