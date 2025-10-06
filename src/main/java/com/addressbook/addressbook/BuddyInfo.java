package com.addressbook.addressbook;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class BuddyInfo implements Serializable {
    private String name;
    private String number;
    private String address;

    @ManyToOne
    @JoinColumn(name = "addressbook_id")
    private AddressBook addressBook;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    BuddyInfo(){
        this.name = null;
        this.number = null;
        this.address = null;
    }

    BuddyInfo(String inputName, String inputNumber, String inputAddress){
        this.name = inputName;
        this.number = inputNumber;
        this.address = inputAddress;
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

    public void setAddress(String address) {this.address = address;}

    public String getAddress() {return this.address;}

}
