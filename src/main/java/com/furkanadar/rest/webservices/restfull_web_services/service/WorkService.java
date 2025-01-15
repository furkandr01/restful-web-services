package com.furkanadar.rest.webservices.restfull_web_services.service;

import com.furkanadar.rest.webservices.restfull_web_services.model.Work;
import com.furkanadar.rest.webservices.restfull_web_services.repository.WorkRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkService {
    private final WorkRepository workRepository;

    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public Work saveWork(String title, Long authorId) {
        Work work = new Work();
        work.setTitle(title);
        work.setAuthorId(authorId);
        return workRepository.save(work);
    }
}
