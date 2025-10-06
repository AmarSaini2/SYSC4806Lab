package com.addressbook.addressbook;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AddressBookRepo extends CrudRepository<AddressBook, Integer>{
    @Query("SELECT ab FROM AddressBook ab LEFT JOIN FETCH ab.buddyInfos WHERE ab.id = :id")
    Optional<AddressBook> findByIdWithBuddyInfos(@Param("id") Long id);

    @Query("SELECT ab FROM AddressBook ab LEFT JOIN FETCH ab.buddyInfos")
    Iterable<AddressBook> findAllWithBuddyInfos();

}
