package com.vodafone.apis.libraryapi.publisher.controller;

import com.vodafone.apis.libraryapi.publisher.exception.LibraryResourceAlreadyExistException;
import com.vodafone.apis.libraryapi.publisher.exception.LibraryResourceNotFoundException;
import com.vodafone.apis.libraryapi.publisher.model.Publisher;
import com.vodafone.apis.libraryapi.publisher.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publshierService;

    Publisher publisher = null;

    @GetMapping(path = "/{publisherId}")
    public ResponseEntity getPublisher(@PathVariable Integer publisherId) throws LibraryResourceNotFoundException {

        //,@RequestHeader(value = "trace-id", defaultValue = "") String traceId)
        publisher = publshierService.getPublisher(publisherId);
        //we can add trace-id to be taken in request-header , to trace your request
//        if (traceId.isEmpty())
//            traceId = UUID.randomUUID().toString();
//        System.out.println(traceId);
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addPublisher(@RequestBody Publisher publisher) throws LibraryResourceAlreadyExistException {
        publshierService.addPublisher(publisher); // How the id added after save ?!
        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }

    //TODO: Update the method of mapping to be Patch instead of Put , partial update
    @PutMapping(path = "/{publisherId}")
    public ResponseEntity updatePublisher(@PathVariable Integer publisherId, @RequestBody Publisher newPublisher) throws LibraryResourceNotFoundException {
        publshierService.updatePublisher(publisherId, newPublisher);
        return new ResponseEntity<>(publisher, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{publisherId}")
    public ResponseEntity deletePublisher(@PathVariable Integer publisherId) throws LibraryResourceNotFoundException {
        publshierService.deletePublisher(publisherId);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/search")
    public ResponseEntity searchPublisher(@RequestParam String name) throws LibraryResourceNotFoundException {
        //validation on requestParam and PATHVARIBALE
        List<Publisher> publishers = null;
        publishers = publshierService.searchPublisher(name);
        return new ResponseEntity(publishers, HttpStatus.OK);
    }

    //TODO
//    @PatchMapping
//    public ResponseEntity updatePublisherUsingPatch(@RequestBody Publisher updatedPubisher){
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
