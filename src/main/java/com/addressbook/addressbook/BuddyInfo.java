package com.addressbook.addressbook;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class BuddyInfo implements Serializable {
    private String name;
    private String number;

    @ManyToOne
    @JoinColumn(name = "addressbook_id")
    private AddressBook addressBook;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    BuddyInfo(){
        this.name = null;
        this.number = null;
    }

    BuddyInfo(String inputName, String inputNumber){
        this.name = inputName;
        this.number = inputNumber;
    }

    public void setAddressBook(AddressBook ab){
        this.addressBook = ab;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public String getNumber(){
        return this.number;
    }

    public void setName(String name){this.name = name;}

    public void setNumber(String number){this.number = number;}

}
