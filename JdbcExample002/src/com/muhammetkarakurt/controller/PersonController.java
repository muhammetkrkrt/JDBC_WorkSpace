package com.muhammetkarakurt.controller;

import com.muhammetkarakurt.entity.Person;
import com.muhammetkarakurt.servise.PersonServise;

public class PersonController {

    private  final PersonServise personServise;

    public PersonController() {
        personServise = new PersonServise();
    }

    public void insertPerson(Person person){
        personServise.insertPerson(person);
    }
    public void getAllPerson(){
        personServise.getAllPerson();
    }

    public void deleteAllRecords() {
        personServise.deleteAllRecords();
    }

    public void updateEmail(String email , int id){
        personServise.updateEmail(email , id);
    }

    public void getPersonById(int byId) {
        personServise.getPersonById(byId);
    }

    public void deletePerson(int byId1) {
        personServise.deletePerson(byId1);
    }
}
