package com.vodafone.apis.libraryapi.publisher.controller;

import com.vodafone.apis.libraryapi.publisher.exception.LibraryResourceAlreadyExistException;
import com.vodafone.apis.libraryapi.publisher.exception.LibraryResourceNotFoundException;
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

    Publisher publisher = null;

    @GetMapping(path = "/{publisherId}")
    public ResponseEntity getPublisher(@PathVariable Integer publisherId) throws LibraryResourceAlreadyExistException {
        publisher = publshierService.getPublisher(publisherId);
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addPublisher(@RequestBody Publisher publisher) throws LibraryResourceAlreadyExistException {
        publshierService.addPublisher(publisher);
        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }

    //TODO: Update the method of mapping to be Patch instead of Put , partial update
    @PutMapping(path = "/{publisherId}")
    public ResponseEntity updatePublisher(@PathVariable Integer publisherId, @RequestBody Publisher newPublisher) throws LibraryResourceNotFoundException {
        publshierService.updatePublisher(publisherId, newPublisher);
        return new ResponseEntity<>(publisher, HttpStatus.NO_CONTENT);
    }
}
