package com.vodafone.apis.libraryapi.publisher.repository;

import com.vodafone.apis.libraryapi.publisher.entity.PublisherEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends CrudRepository<PublisherEntity , Integer> {


}
