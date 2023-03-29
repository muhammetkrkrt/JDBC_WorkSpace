package com.muhammetkarakurt.util;

import com.muhammetkarakurt.controller.PersonController;
import com.muhammetkarakurt.entity.Person;

import java.util.Date;
import java.util.Scanner;

public class Menu {

    private  static Scanner sc = new Scanner(System.in);

    private final PersonController personController;

    public Menu() {
        personController = new PersonController();
    }

    public void menu(){
        boolean check = true;
        while (check){
            System.out.println("----- - Data base islemleri --------");
            System.out.println("1 --> Data base veri ekleme.");
            System.out.println("2 --> Data base tum verileri goruntuleme.");
            System.out.println("3 --> Data base tum verileri silme.");
            System.out.println("4 --> Data base veri mail guncelleme.");
            System.out.println("5 --> Data basede id ile veri arama");
            System.out.println("6 --> Data basete id ile veri silme.");
            System.out.println("---------------------------------------------");
            System.out.println("Lutfen yapmak istediginiz secimi giriniz:");
            int secim = Integer.parseInt(sc.nextLine());

            switch (secim){
                case 1 :
                    Person person = new Person();
                    System.out.println("Lutfen kayit etmek istediginiz kisinin Ismini giriniz: ");
                    person.setFirstName(sc.nextLine());
                    System.out.println("Lutfen kayit etmek istediginiz kisinin Soyismini giriniz: ");
                    person.setLastName(sc.nextLine());
                    System.out.println("Lutfen kayit etmek istediginiz kisinin mail adresini giriniz: ");
                    person.setEmail(sc.nextLine());
                    person.setJoinedDate(new Date());
                    personController.insertPerson(person);
                    break;
                case 2:
                    personController.getAllPerson();
                    break;
                case 3 :
                    personController.deleteAllRecords();
                    break;
                case 4 :
                    System.out.println("Lutfen mailini degistirmek istediginiz kisinin ID sini giriniz: ");
                    int id = Integer.parseInt(sc.nextLine());
                    System.out.println("Lutfen guncel mail adresini giriniz");
                    String email = sc.nextLine();
                    personController.updateEmail(email ,id);
                    break;
                case 5 :
                    System.out.println("Lutfen listelemek istediginiz kisinin  ID sini giriniz: ");
                    int byId = Integer.parseInt(sc.nextLine());
                    personController.getPersonById(byId);
                    break;
                case 6 :
                    System.out.println("Lutfen silmek istediginiz verinin Id sini giriniz: ");
                    int byId1 = Integer.parseInt(sc.nextLine());
                    personController.deletePerson(byId1);
                    break;
                default:
                    System.out.println("Lutfen gecerli bir tercih giriniz...");
                    break;
            }
        }

    }
}
