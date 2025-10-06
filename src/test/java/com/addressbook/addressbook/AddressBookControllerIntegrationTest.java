package com.addressbook.addressbook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional  // Roll back DB changes after each test
public class AddressBookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddressBookRepo repo;

    private AddressBook addressBook;
    private BuddyInfo buddy;

    @BeforeEach
    public void setup() {
        // Create an AddressBook with one BuddyInfo for testing
        addressBook = new AddressBook();
        buddy = new BuddyInfo("John Doe", "123-456-7890", "Frontenac House");
        addressBook.addBuddy(buddy);
        repo.save(addressBook);
    }

    @Test
    public void testGetAllAddressBooks() throws Exception {
        mockMvc.perform(get("/alladdressbooks"))
                .andExpect(status().isOk())
                .andExpect(view().name("alladdressbooks"))
                .andExpect(model().attributeExists("alladdressbooks"))
                .andExpect(model().attribute("alladdressbooks", hasItem(
                        hasProperty("id", is(addressBook.getId()))
                )));
    }

    @Test
    public void testGetAddressBookById() throws Exception {
        mockMvc.perform(get("/addressbook/" + addressBook.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("addressbookbyid"))
                .andExpect(model().attributeExists("addressbook"))
                .andExpect(model().attribute("addressbook", hasProperty("id", is(addressBook.getId()))))
                .andExpect(model().attributeExists("newBuddy"))
                .andExpect(model().attribute("addressbook", hasProperty("buddyInfos", containsInAnyOrder(
                        allOf(
                                hasProperty("name", is(buddy.getName())),
                                hasProperty("id", is(buddy.getId())),
                                hasProperty("number", is(buddy.getNumber())),
                                hasProperty("address", is(buddy.getAddress()))
                        )
                ))));
    }

    @Test
    public void testCreateNewAddressBook() throws Exception {
        mockMvc.perform(get("/addressbook/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/addressbook/*"));
    }

    @Test
    public void testAddBuddyToAddressBook() throws Exception {
        mockMvc.perform(post("/addressbook/" + addressBook.getId())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Jane Doe")
                        .param("number", "987-654-3210"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/addressbook/" + addressBook.getId()));
    }

    @Test
    public void testRemoveBuddyFromAddressBook() throws Exception {
        // Get ID of the buddy to remove
        Long buddyId = addressBook.getBuddyInfos().iterator().next().getId();

        mockMvc.perform(post("/addressbook/" + addressBook.getId() + "/remove")
                        .param("buddyId", buddyId.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/addressbook/" + addressBook.getId()));
    }

    @Test
    public void testInvalidAddressBookReturns404Redirect() throws Exception {
        Long invalidId = 999999L;

        mockMvc.perform(post("/addressbook/" + invalidId)
                        .param("name", "Ghost")
                        .param("number", "000-000-0000"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error/404"));

        mockMvc.perform(post("/addressbook/" + invalidId + "/remove")
                        .param("buddyId", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/error/404"));
    }
}

