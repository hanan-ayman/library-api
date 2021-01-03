package com.vodafone.apis.libraryapi.publisher.controller;

import com.vodafone.apis.libraryapi.publisher.exception.LibraryResourceAlreadyExistException;
import com.vodafone.apis.libraryapi.publisher.exception.LibraryResourceNotFoundException;
import com.vodafone.apis.libraryapi.publisher.model.Publisher;
import com.vodafone.apis.libraryapi.publisher.service.PublisherService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/publishers")
@Slf4j
public class PublisherController {

//    static Logger log = LoggerFactory.getLogger(PublisherController.class);
    @Autowired
    private PublisherService publshierService;
    Publisher publisher = null;
    @GetMapping(path = "/{publisherId}")
    public ResponseEntity getPublisher(@PathVariable Integer publisherId ,@RequestHeader(value = "trace-id", defaultValue = "") String traceId) throws LibraryResourceNotFoundException {
        publisher = publshierService.getPublisher(publisherId);
        if (traceId.isEmpty())
            traceId = UUID.randomUUID().toString();
        log.info("the get publisher request with trace id: {} " , traceId);
        return new ResponseEntity<>(publisher, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addPublisher(@Valid @RequestBody Publisher publisher) throws LibraryResourceAlreadyExistException {
        publshierService.addPublisher(publisher);
        log.info("the add Publisher request : {} " , publisher.toString());
        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{publisherId}")
    public ResponseEntity updatePublisher(@PathVariable Integer publisherId, @Valid @RequestBody Publisher newPublisher) throws LibraryResourceNotFoundException {
        publshierService.updatePublisher(publisherId, newPublisher);
        log.info("the update Publisher with publisherId {} ,  request body : {} " , publisherId , publisher);
        return new ResponseEntity<>(publisher, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{publisherId}")
    public ResponseEntity deletePublisher(@PathVariable Integer publisherId) throws LibraryResourceNotFoundException {
        publshierService.deletePublisher(publisherId);
        log.info("the delete Publisher with publisherId {}",  publisherId );
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping(path = "/search")
    public ResponseEntity searchPublisher(@RequestParam String name) throws LibraryResourceNotFoundException {
        //validation on requestParam and PATHVARIBALE
        List<Publisher> publishers = null;
        publishers = publshierService.searchPublisher(name);
        log.info("the search Publisher with publisher name {}",  name );
        return new ResponseEntity(publishers, HttpStatus.OK);
    }

    //TODO: Update the method of mapping to be Patch instead of Put , partial update
//    @PatchMapping
//    public ResponseEntity updatePublisherUsingPatch(@RequestBody Publisher updatedPubisher){
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
