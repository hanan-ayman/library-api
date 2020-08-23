package com.vodafone.apis.libraryapi.publisher.controller;

import com.vodafone.apis.libraryapi.publisher.exception.LibraryResourceAlreadyExistException;
import com.vodafone.apis.libraryapi.publisher.model.Publisher;
import com.vodafone.apis.libraryapi.publisher.service.PublshierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/publishers")
public class PublisherController {

    @Autowired
    private PublshierService publshierService;

    @GetMapping(path = "/{publisherId}")
    public Publisher getPublisher(@PathVariable Integer publisherId) {
        return new Publisher(publisherId, "Hanan", "hanan.ayman.said@gmail.com", "01017923959");
    }

    @PostMapping
    public ResponseEntity addPublisher(@RequestBody Publisher publisher) {
        try {
            publisher = publshierService.addPublisher(publisher);
        } catch (LibraryResourceAlreadyExistException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }
}
