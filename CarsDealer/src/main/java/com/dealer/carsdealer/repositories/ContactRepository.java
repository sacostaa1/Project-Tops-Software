package com.dealer.carsdealer.repositories;

import com.dealer.carsdealer.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
