package com.Eduardo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDateTime;
import java.time.Month;

@ComponentScan("com.Eduardo")
public class Main
{

    public static void main(String[] args) throws DuplicateUserName,DuplicateFlightNumber,UnexistingUserName,UnexistingFlightName,MaxUserCapacityAchieved
    {
//        Flight flight1=new Flight("FL-124","Maldive",LocalDateTime.of(2019,Month.JANUARY,22,6,20,30),20000,140);
//        Flight flight2=new Flight("GB-151","Londra",LocalDateTime.of(2019,Month.JANUARY,22,12,20,50),30000,160);
//        Flight flight3=new Flight("NE-221","Bucharest",LocalDateTime.of(2019,Month.JANUARY,24,14,30,40),25000,150);
//        Flight flight4=new Flight("DE-379","Paris",LocalDateTime.of(2019,Month.JANUARY,24,16,40,30),22000,170);
//        User user1=new User("Eduard","Gabriel",LocalDateTime.of(1995,Month.SEPTEMBER,19,10,50));
//        User user2=new User("Ana","Maria",LocalDateTime.of(1996,Month.AUGUST,19,10,50));
//        User user3=new User("Mircea","Ionut",LocalDateTime.of(1994,Month.OCTOBER,20,11,40));
//        User user4=new User("Bogdan","Andrei",LocalDateTime.of(1993,Month.JULY,18,12,30));
//        User user5=new User("Diana","Elena",LocalDateTime.of(1997,Month.NOVEMBER,21,13,20));
//        User user6=new User("Sara","Elena",LocalDateTime.of(1998,Month.JUNE,17,14,10));
//        User user7=new User("Radu","Mihai",LocalDateTime.of(1992,Month.MAY,16,15,00));
//        User user8=new User("Doru","Stefan",LocalDateTime.of(1991,Month.DECEMBER,21,16,50));
//        AirportManager airportManager=new AirportManager();
//        airportManager.addFlight(flight1);
//        airportManager.addFlight(flight2);
//        airportManager.addFlight(flight3);
//        airportManager.addFlight(flight4);
//        airportManager.addUser(user1);
//        airportManager.addUser(user2);
//        airportManager.addUser(user3);
//        airportManager.addUser(user4);
//        airportManager.addUser(user5);
//        airportManager.addUser(user6);
//        airportManager.addUser(user7);
//        airportManager.addUser(user8);
//        airportManager.updateFlightStatus();
//        airportManager.readFlights();
//        airportManager.readUsers();
////        airportManager.removeFlight();
////        airportManager.removeUSer();
//        airportManager.addUserToFlightUserList(flight1);
//        airportManager.addUserToFlightUserList(flight2);
//        airportManager.addUserToFlightUserList(flight3);
//        airportManager.addUserToFlightUserList(flight4);
//        airportManager.flightsJoinedByUser();
//        airportManager.howManyFlightsUserJoined();
//        airportManager.flightsScheduled();
//        airportManager.howManyFlightsScheduled();
//        airportManager.flightsLanded();
//        airportManager.howManyFlightsLanded();
//        airportManager.userVisitedDestinations();
//        airportManager.howManyDestinationsUserVisited();
        ApplicationContext context=new ClassPathXmlApplicationContext("airport-application-context.xml");
        User user=context.getBean(User.class);
        System.out.println(user.toString());
        Flight flight=context.getBean(Flight.class);
        System.out.println(flight.getName());
        AirportManager airportManager=(AirportManager)context.getBean("airportManager");
        airportManager.addUser(user);
        airportManager.readUsers();
        airportManager.addFlight(flight);
        airportManager.updateFlightStatus();
        airportManager.readFlights();
        airportManager.removeUSer();
    }
}
