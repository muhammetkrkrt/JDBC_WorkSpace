import com.muhammetkarakurt.controller.PersonController;
import com.muhammetkarakurt.entity.Person;
import com.muhammetkarakurt.repository.PersonRepository;
import com.muhammetkarakurt.util.Menu;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        /*Person person = new Person("Muhammet","Karakurt","karakurt@gmail",new Date());
        Person person1 = new Person("Ahmet","yaz","kahre@gmail",new Date());
        Person person2 = new Person("Ayse","Kis","kahre@gmail.com",new Date());*//*

        PersonController personController = new PersonController();
        personController.insertPerson(person2);*/

        Menu menu = new Menu();

        menu.menu();


    }
}