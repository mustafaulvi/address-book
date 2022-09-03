package com.example.addressbook.repository;

import com.example.addressbook.model.AddressBook;
import org.springframework.data.repository.CrudRepository;

public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
}
