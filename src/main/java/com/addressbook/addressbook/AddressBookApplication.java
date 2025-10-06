package com.addressbook.addressbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class AddressBookApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AddressBookApplication.class, args);
	}


	@Bean
	public boolean demo(AddressBookRepo repo) {
		// Fetch all AddressBooks (assuming this fetches buddy info eagerly or is configured properly)
		Iterable<AddressBook> allBooks = repo.findAllWithBuddyInfos();

		boolean anyBooks = false;

		for (AddressBook book : allBooks) {
			System.out.println("AddressBook ID: " + book.getId());
			anyBooks = true;

			Collection<BuddyInfo> buddies = book.getBuddyInfos();
			if (buddies == null || buddies.isEmpty()) {
				System.out.println("  (No buddies in this address book)");
			} else {
				for (BuddyInfo buddy : buddies) {
					System.out.println("  - " + buddy.getName() + ": " + buddy.getNumber());
				}
			}
		}
		if(!anyBooks){
			System.out.println("No AddressBooks Found");
		}
		return anyBooks;
	}
}