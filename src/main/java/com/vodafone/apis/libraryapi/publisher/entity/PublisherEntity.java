package com.vodafone.apis.libraryapi.publisher.entity;

import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "publisher")
public class PublisherEntity {


    @Id @Column(name = "Publisher_Id") @Setter
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisherId_generator")
    @SequenceGenerator(name = "publisherId_generator", sequenceName = "publisher_sequence", allocationSize = 50)
    private int publisherId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email_Id")
    private String emailId;

    @Column(name = "Phone_Number")
    private String phoneNumber;

    public PublisherEntity(String name, String emailId, String phoneNumber) {
        this.name = name;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }
}
