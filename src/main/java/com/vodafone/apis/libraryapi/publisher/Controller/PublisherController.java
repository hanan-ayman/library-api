package com.vodafone.apis.libraryapi.publisher.Controller;

import com.vodafone.apis.libraryapi.publisher.Model.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="/v1/publishers")
public class PublisherController {
    @GetMapping(path = "/{publisherId}")
    public Publisher getPublisher(@PathVariable String publisherId) {
        return new Publisher(publisherId , "Hanan" , "hanan.ayman.said@gmail.com" , "01017923959" );
    }
}
