package com.vodafone.apis.libraryapi.publisher.service;

import com.vodafone.apis.libraryapi.publisher.entity.PublisherEntity;
import com.vodafone.apis.libraryapi.publisher.exception.LibraryResourceAlreadyExistException;
import com.vodafone.apis.libraryapi.publisher.model.Publisher;
import com.vodafone.apis.libraryapi.publisher.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublshierService {
    @Autowired
    private PublisherRepository publisherRepository;

    public void addPublisher(Publisher publisherTobeAdded) throws LibraryResourceAlreadyExistException {
        PublisherEntity publisherEntity = new PublisherEntity(
                publisherTobeAdded.getName(),
                publisherTobeAdded.getEmailId(),
                publisherTobeAdded.getPhoneNumber());

        PublisherEntity addedPublisher = null;
        try {
            addedPublisher = publisherRepository.save(publisherEntity);
        } catch (DataIntegrityViolationException exception) {
            throw new LibraryResourceAlreadyExistException("Publisher Already exist !!");
        }
        publisherTobeAdded.setPublisherId(addedPublisher.getPublisherId());
    }

    public Publisher getPublisher(Integer publisherId) throws LibraryResourceAlreadyExistException {
        Publisher publisher = null;
            Optional<PublisherEntity> publisherEntity = publisherRepository.findById(publisherId);
            if(publisherEntity.isPresent()){
                publisher = createModelFromEntity(publisherEntity);
            } else {
                throw new LibraryResourceAlreadyExistException("Publisher Not Found  !!");
            }
        return publisher;
    }

    private Publisher createModelFromEntity(Optional<PublisherEntity> publisherEntity) {
        return new Publisher(publisherEntity.get().getPublisherId(), publisherEntity.get().getName(), publisherEntity.get().getEmailId(), publisherEntity.get().getPhoneNumber());
    }
}
