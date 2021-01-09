package com.vodafone.apis.libraryapi.publisher.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.vodafone.apis.libraryapi.publisher.controller.PublisherController;
import com.vodafone.apis.libraryapi.publisher.controller.email.EmailService;
import com.vodafone.apis.libraryapi.publisher.controller.sms.MobileService;
import com.vodafone.apis.libraryapi.publisher.entity.PublisherEntity;
import com.vodafone.apis.libraryapi.publisher.exception.LibraryResourceAlreadyExistException;
import com.vodafone.apis.libraryapi.publisher.exception.LibraryResourceNotFoundException;
import com.vodafone.apis.libraryapi.publisher.model.Publisher;
import com.vodafone.apis.libraryapi.publisher.repository.PublisherRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.*;

@Slf4j
@Service
public class PublisherService {

    final static String PUBLISHER_ADDED_MAIL_SUBJECT = "PUBLISHER ADDED SUCCESSFULLY";
    final static String PUBLISHER_ADDED_MAIL_BODY = "Dear Customer , <b>congratulation</b> ! your publisher has been added successfully";
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private MobileService mobileService;
    public void addPublisher(Publisher publisherTobeAdded) throws LibraryResourceAlreadyExistException {
        PublisherEntity publisherEntity = new PublisherEntity(
                publisherTobeAdded.getName(),
                publisherTobeAdded.getEmailId(),
                publisherTobeAdded.getPhoneNumber());

        PublisherEntity addedPublisher = null;
        try {
            addedPublisher = publisherRepository.save(publisherEntity);
            mobileService.sendSMS(publisherTobeAdded.getPhoneNumber());
            emailService.sendSimpleMessage(publisherTobeAdded.getEmailId(),PUBLISHER_ADDED_MAIL_SUBJECT,PUBLISHER_ADDED_MAIL_BODY );
        } catch (DataIntegrityViolationException e) {
            log.error("Publisher Already exist with name {} " , publisherTobeAdded.getName());
            throw new LibraryResourceAlreadyExistException("Publisher Already exist !!");
        }catch (MessagingException e){
            log.error("we could not send your email  {} " , e.getMessage());
        }
        publisherTobeAdded.setPublisherId(addedPublisher.getPublisherId());
    }

    public Publisher getPublisher(Integer publisherId) throws LibraryResourceNotFoundException {
        Publisher publisher;
        Optional<PublisherEntity> publisherEntity = publisherRepository.findById(publisherId);
        if (publisherEntity.isPresent()) {
            publisher = createModelFromEntity(publisherEntity);
        } else {
            log.error("Publisher Not Found for id {} " , publisherId);
            throw new LibraryResourceNotFoundException("Publisher Not Found  !!");
        }
        return publisher;
    }

    public void updatePublisher(Integer publisherId, Publisher newPublisher) throws LibraryResourceNotFoundException {
        newPublisher.setPublisherId(publisherId);
        Optional<PublisherEntity> oldPublisher = publisherRepository.findById(publisherId);
        PublisherEntity publisherWillBeSaved = oldPublisher.get();
        //The Update is optional only for Email or PhoneNumber
        if (oldPublisher.isPresent()) {
            if (newPublisher.getEmailId() != null) {
                publisherWillBeSaved.setEmailId(newPublisher.getEmailId());
            }
            if (newPublisher.getPhoneNumber() != null) {
                publisherWillBeSaved.setPhoneNumber(newPublisher.getPhoneNumber());
            }
            publisherRepository.save(publisherWillBeSaved);
        } else {
            log.error("Publisher Not Found for id {} " , publisherId);
            throw new LibraryResourceNotFoundException("Publisher Not found !!");
        }

    }

    public void deletePublisher(Integer publisherId) throws LibraryResourceNotFoundException {
        Optional<PublisherEntity> publisher = publisherRepository.findById(publisherId);
        if (publisher.isPresent()) {
            publisherRepository.deleteById(publisherId);
        } else {
            log.error("Publisher Not Found for publisher id {} " , publisherId);
            throw new LibraryResourceNotFoundException("Publisher Not found !!");
        }
    }

    public List<Publisher> searchPublisher(String name) throws LibraryResourceNotFoundException {
        List<PublisherEntity> publishersEntities = null;
        publishersEntities = publisherRepository.findByNameContaining(name);
        if (publishersEntities.isEmpty()) {
            log.error("Publisher Not Found for publisher name {} " , name);
            throw new LibraryResourceNotFoundException("Publishers Not Found !");
        }
        return createModelsFromEntities(publishersEntities);

    }

    private Publisher createModelFromEntity(Optional<PublisherEntity> publisherEntity) {
        return new Publisher(publisherEntity.get().getPublisherId(), publisherEntity.get().getName(), publisherEntity.get().getEmailId(), publisherEntity.get().getPhoneNumber());
    }

    private List<Publisher> createModelsFromEntities(List<PublisherEntity> publishersEntities) {
        List<Publisher> publishers = new ArrayList<Publisher>();
        for (int i = 0; i < publishersEntities.size(); i++) {
            publishers.add(createModelFromEntity(Optional.ofNullable(publishersEntities.get(i))));
        }
        return publishers;
//        return publishersEntities.stream().map(pe -> createModelFromEntity(Optional.ofNullable(pe))).collect(Collectors.toList());
    }
}
