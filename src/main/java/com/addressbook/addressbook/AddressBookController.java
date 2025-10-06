package com.addressbook.addressbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import java.util.Optional;

@Controller
public class AddressBookController {
    @Autowired
    AddressBookRepo repo;

    @GetMapping("/alladdressbooks")
    public String allAddressBooks(Model model){
        Iterable<AddressBook> allBooks = repo.findAllWithBuddyInfos();
        model.addAttribute("alladdressbooks", allBooks);
        return "alladdressbooks";
    }

    @GetMapping("/addressbook/{id}")
    public String addressBookById(@PathVariable Long id, Model model){
        Optional<AddressBook> addressBook = repo.findByIdWithBuddyInfos(id);
        if(addressBook.isPresent()){
            model.addAttribute("addressbook", addressBook.get());
            model.addAttribute("newBuddy", new BuddyInfo());
            return "addressbookbyid";
        }else{
            return "addressbookbyid";
        }
    }

    @PostMapping("/addressbook/{id}")
    public String addBuddy(@PathVariable Long id, @ModelAttribute BuddyInfo newBuddy){
        Optional<AddressBook> addressBookPull = repo.findByIdWithBuddyInfos(id);
        if(addressBookPull.isPresent()){
            AddressBook addressBook = addressBookPull.get();
            addressBook.addBuddy(newBuddy.getName(), newBuddy.getNumber());
            repo.save(addressBook);
            String returnString = "redirect:/addressbook/%d".formatted(addressBook.getId());
            return returnString;
        }else{
            return "redirect:/error/404";
        }
    }

    @PostMapping("/addressbook/{id}/remove")
    public String removeBuddy(@PathVariable Long id, @RequestParam Long buddyId){
        Optional<AddressBook> addressBookPull = repo.findByIdWithBuddyInfos(id);
        if(addressBookPull.isPresent()){
            AddressBook addressBook = addressBookPull.get();
            addressBook.removeBuddy(buddyId);
            repo.save(addressBook);
            String returnString = "redirect:/addressbook/%d".formatted(addressBook.getId());
            return returnString;
        }else{
            return "redirect:/error/404";
        }
    }

    @GetMapping("/addressbook/new")
    public String createAddressBook(Model model){
        AddressBook newBook = new AddressBook();
        repo.save(newBook);
        String returnString = "redirect:/addressbook/%d".formatted(newBook.getId());
        return returnString;
    }
}
