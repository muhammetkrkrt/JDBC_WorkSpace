package com.muhammetkarakurt.servise;

import com.muhammetkarakurt.entity.Person;
import com.muhammetkarakurt.repository.PersonRepository;

public class PersonServise {
    private final PersonRepository personRepository;

    public PersonServise() {
        personRepository = new PersonRepository() ;

    }

    public void insertPerson(Person person){
        if(person.getEmail().endsWith("@gmail.com")){
            personRepository.insertPerson(person);
        }else{
            System.out.println("Mail uygun degil veritabanina eklenmedi");
        }

    }

    public void getAllPerson() {
        personRepository.getAllPersons();
    }

    public void deleteAllRecords() {
        personRepository.deleteAllRecords();
    }

    public void updateEmail(String email, int id) {
        personRepository.updateEmail(email , id);
    }

    public void getPersonById(int byId) {
        personRepository.getPersonById(byId);
    }

    public void deletePerson(int byId1) {
        personRepository.deletePerson(byId1);
    }
}
