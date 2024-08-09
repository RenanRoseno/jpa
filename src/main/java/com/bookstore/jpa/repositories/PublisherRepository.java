package com.bookstore.jpa.repositories;

import com.bookstore.jpa.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, UUID> {

}
