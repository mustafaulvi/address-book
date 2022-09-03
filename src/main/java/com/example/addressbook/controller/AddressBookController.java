package com.example.addressbook.controller;

import com.example.addressbook.model.AddressBook;
import com.example.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressBookController {

    private AddressBookService addressBookService;

    @GetMapping("/addressBooks")
    public ResponseEntity<List<AddressBook>> getAllAddressBooks() {
        return ResponseEntity.ok().body(addressBookService.getAllAddressBook());
    }

    @GetMapping("/addressBooks/{id}")
    public ResponseEntity<AddressBook> getAddressBookById(@PathVariable long id) {
        return ResponseEntity.ok().body(addressBookService.getAddressBookById(id));
    }

    @PostMapping("/addressBooks")
    public ResponseEntity<AddressBook> creatAddressBook(@RequestBody AddressBook addressBook) {
        return ResponseEntity.ok().body(addressBookService.creatAddressBook(addressBook));
    }

    @PutMapping("/addressBooks")
    public ResponseEntity<AddressBook> updateAddressBook(@RequestBody AddressBook addressBook) {
        return ResponseEntity.ok().body(addressBookService.updateAddressBook(addressBook));
    }

    @DeleteMapping("/addressBooks/{id}")
    public HttpStatus deleteAddressBook(@PathVariable long id) {
        this.addressBookService.deleteAddressBook(id);
        return HttpStatus.OK;
    }

    @Autowired
    public void setAddressBookService(AddressBookService addressBookService) {
        this.addressBookService = addressBookService;
    }
}
