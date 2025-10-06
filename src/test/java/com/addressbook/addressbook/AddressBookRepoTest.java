package com.addressbook.addressbook;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class AddressBookRepoTest {

    @Autowired
    private AddressBookRepo addressBookRepo;

    @Test
    public void testStoreAndRetrieveAddressBook() {
        // Create a new AddressBook
        AddressBook ab = new AddressBook();
        ab.addBuddy("John Doe", "123-456-7890", "1 madeupway");
        ab.addBuddy("Jane Smith", "098-765-4321", "2 madeupway");

        // Save it
        AddressBook savedAB = addressBookRepo.save(ab);
        Long abId = savedAB.getId();
        assertNotNull(abId, "Saved AddressBook should have a non-null ID");

        // Fetch using the custom method (eager load buddies)
        Optional<AddressBook> optionalAB = addressBookRepo.findByIdWithBuddyInfos(abId);
        assertTrue(optionalAB.isPresent(), "Should retrieve the AddressBook by ID");

        AddressBook retrievedAB = optionalAB.get();

        // Validate data
        assertEquals(abId, retrievedAB.getId(), "IDs should match");
        assertNotNull(retrievedAB.getBuddyInfos(), "BuddyInfos should not be null");
        assertEquals(2, retrievedAB.getBuddyInfos().size(), "There should be 2 buddies");

        assertTrue(
                retrievedAB.getBuddyInfos().stream().anyMatch(b -> b.getName().equals("John Doe")),
                "Buddy 'John Doe' should be present"
        );
        assertTrue(
                retrievedAB.getBuddyInfos().stream().anyMatch(b -> b.getName().equals("Jane Smith")),
                "Buddy 'Jane Smith' should be present"
        );
    }
}
