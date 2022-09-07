package com.example.addressbook.controller;

import com.example.addressbook.exception.ResourceNotFoundException;
import com.example.addressbook.model.AddressBook;
import com.example.addressbook.service.AddressBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AddressBookControllerTest {

    @InjectMocks
    private AddressBookController addressBookController;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    AddressBookService addressBookService;


    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.standaloneSetup(addressBookController).build();
    }

    @Test
    public void getAddressBooksHasNothing() throws Exception{

        when(addressBookService.getAllAddressBook()).thenReturn(List.of());

        // then
        mockMvc.perform(MockMvcRequestBuilders.get("/addressBooks")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void addAddressBooks() throws Exception{

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(addressBookService.creatAddressBook(any(AddressBook.class))).thenReturn(new AddressBook());

        AddressBook addressBook=new AddressBook();
        addressBook.setId(2);
        addressBook.setName("Test NAme");
        addressBook.setSurName("Test Surname");

        AddressBook createdAddressBook = addressBookService.creatAddressBook(addressBook);

        assertEquals(addressBook.getId(),2L);
    }
}
