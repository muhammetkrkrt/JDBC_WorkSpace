package com.muhammetkarakurt.repository;

import com.muhammetkarakurt.entity.Person;

public interface IPersonRepository {
    public void insertPerson(Person person);
    public void getAllPersons();
    public void deleteAllRecords() ;
    public void updateEmail(String email , int id);
    public void getPersonById(int byId);
    public void deletePerson(int byId1);
}
