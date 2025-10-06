package com.addressbook.addressbook;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Entity
public class AddressBook {
    @OneToMany(mappedBy = "addressBook", cascade = CascadeType.ALL)
    private Collection<BuddyInfo> buddyInfos;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    AddressBook(){
        this.buddyInfos = new ArrayList<>();
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void addBuddy(String name, String number, String address){
        BuddyInfo buddy = new BuddyInfo(name, number, address);
        buddy.setAddressBook(this);
        buddyInfos.add(buddy);
    }

    public void addBuddy(BuddyInfo buddy){
        buddy.setAddressBook(this);
        buddyInfos.add(buddy);
    }

    public void removeBuddy(Long id){
        Iterator<BuddyInfo> iterator = buddyInfos.iterator();
        while(iterator.hasNext()){
            BuddyInfo x = iterator.next();
            if(x.getId().equals(id)){
                iterator.remove();
                x.setAddressBook(null);
                break;
            }
        }
    }

    public int getBuddyCount() {
        return buddyInfos.size();
    }

    public Collection<BuddyInfo> getBuddyInfos(){
        return buddyInfos;
    }

    public String toString(){
        StringBuilder retStr = new StringBuilder();
        retStr.append("ID: " + this.getId());
        for(BuddyInfo b : buddyInfos){
            retStr.append("Name: " + b.getName() + " #: " + b.getNumber() +  " Address: " + b.getAddress() + "\n");
        }
        return retStr.toString();
    }
}