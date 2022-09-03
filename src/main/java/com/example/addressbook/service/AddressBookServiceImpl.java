package com.example.addressbook.service;

import com.example.addressbook.exception.ResourceNotFoundException;
import com.example.addressbook.model.AddressBook;
import com.example.addressbook.repository.AddressBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressBookServiceImpl implements AddressBookService {


    private AddressBookRepository addressBookRepository;

    @Override
    public AddressBook creatAddressBook(AddressBook addressBook) {
        return addressBookRepository.save(addressBook);
    }

    @Override
    public AddressBook updateAddressBook(AddressBook addressBook) {
        Optional<AddressBook> addressBookDb = addressBookRepository.findById(addressBook.getId());
        if (addressBookDb.isPresent()) {
            AddressBook addressBookUpdated = addressBookDb.get();
            addressBookUpdated.setName(addressBook.getName());
            addressBookUpdated.setSurName(addressBook.getSurName());
            addressBookUpdated.setAddress(addressBook.getAddress());
            addressBookUpdated.setNote(addressBook.getNote());
            addressBookUpdated.setEmail(addressBook.getEmail());
            addressBookUpdated.setWebsite(addressBook.getWebsite());
            addressBookUpdated.setTelephone(addressBook.getTelephone());
            addressBookRepository.save(addressBookUpdated);
            return addressBookUpdated;
        } else {
            throw new ResourceNotFoundException("(update) Address Book Record not Found with id" + addressBook.getId());
        }
    }

    @Override
    @Transactional
    public List<AddressBook> getAllAddressBook() {
        return (List<AddressBook>) addressBookRepository.findAll();
    }

    @Override
    public AddressBook getAddressBookById(long id) {
        Optional<AddressBook> addressBookDb = addressBookRepository.findById(id);
        if (addressBookDb.isPresent()) {

            return addressBookDb.get();

        } else {
            throw new ResourceNotFoundException("(GetById)Address Book Record not Found with id" + id);
        }
    }

    @Override
    public void deleteAddressBook(long id) {

        Optional<AddressBook> addressBookDb = addressBookRepository.findById(id);
        if (addressBookDb.isPresent()) {
            addressBookRepository.delete(addressBookDb.get());

        } else {
            throw new ResourceNotFoundException("(Delete) Address Book Record not Found with id" + id);
        }

    }

    @Autowired
    public void setAddressBookRepository(AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }
}
