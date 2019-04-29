package com.Eduardo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.*;
import java.util.*;


public class AirportManager
{
    private Scanner scanner=new Scanner(System.in);

    private Set<Flight> flights=new HashSet<>();

    private Set<User> users=new HashSet<>();
    public void addFlight(Flight flight) throws DuplicateFlightNumber
    {
        if(flights.add(flight)==false)
        {
            throw new DuplicateFlightNumber("This flight name already exists");
        }
        else
        {
            System.out.println("Flight "+flight.getName()+" added successfully");
        }
    }
    public void removeFlight() throws UnexistingFlightName
    {
        System.out.println("Give the name of the flight that you want removed:\n");
        String name=scanner.nextLine();
        Iterator<Flight> setIterator=flights.iterator();
        while(setIterator.hasNext())
        {
            Flight flight=setIterator.next();
            if(flight.getName().equals(name))
            {
                if(flights.remove(flight))
                {
                    System.out.println("Flight "+flight.getName()+" removed successfully");
                    return;
                }
            }
        }
        throw new UnexistingFlightName("Flight name does not exists");
    }
    public void readFlights()
    {
        for(Flight f:flights)
        {
            switch(f.getStatus())
            {
                case CANCELED:
                    System.out.println("The flight "+f.getName()+" was canceled.");
                    break;
                case FINISHED:
                    Date date =f.getDepartureDate();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);
                    cal.set(Calendar.SECOND, f.getFlightDuration());
                    date = cal.getTime();
                    System.out.println("The flight "+f.getName()+" has arrived at "+f.getDestination()+" at "+date);
                    break;
                case SCHEDULED:
                    if(f.getDepartureDate().before(new Date())) {
                        Date date1 = f.getDepartureDate();
                        Calendar cal1 = Calendar.getInstance();
                        cal1.setTime(date1);
                        cal1.set(Calendar.SECOND, f.getFlightDuration());
                        date1 = cal1.getTime();
                        System.out.println("The flight " + f.getName() + " has taken off at " + f.getDepartureDate() + " and is going to land at " + date1);
                    }
                    else {
                        System.out.println("The flight " + f.getName() + " is scheduled to take off on " + f.getDepartureDate());
                    }
                    break;
            }
        }
    }
    public void addUser(User user) throws DuplicateUserName
    {
        if(users.add(user)==false)
        {
            throw new DuplicateUserName("This user name already exists");
        }
        else
        {
            System.out.println("User "+user.getFname()+" "+user.getLname()+" added successfully");
        }
    }
    public void removeUSer() throws UnexistingUserName
    {
        System.out.println("Give the first name of the user that you want removed:\n");
        String fname=scanner.nextLine();
        System.out.println("Give the last name of the user that you want removed:\n");
        String lname=scanner.nextLine();
        Iterator<User> userIterator=users.iterator();
        while(userIterator.hasNext())
        {
            User user=userIterator.next();
            if(user.getFname().equals(fname)&&user.getLname().equals(lname))
            {
                if(users.remove(user))
                {
                    System.out.println(user.getFname()+" "+user.getLname()+" was removed.");
                    return;
                }
            }
        }
        throw new UnexistingUserName("User name does not exists");
    }
    public void readUsers()
    {
        for(User user:users)
        {
            System.out.println(user.getFname()+" "+user.getLname()+" ,date of birth "+user.getBirthDate());
        }
    }
    public void updateFlightStatus()
    {
        for(Flight flight:flights)
        {
            Date date =flight.getDepartureDate();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.SECOND, flight.getFlightDuration());
            date = cal.getTime();
            if(date.after(new Date()))
            {
                flight.setStatus(StatusEnum.SCHEDULED);
            }
            else
            {
                flight.setStatus(StatusEnum.FINISHED);
            }
        }
    }
    public void addUserToFlightUserList(Flight flight) throws UnexistingFlightName,MaxUserCapacityAchieved,UnexistingUserName,DuplicateUserName
    {
        for(Flight flight1:flights) {
            if (flight1.getName().equals(flight.getName())) {
                flight.addUser(getUsers());
                return;
            }
        }
        throw new UnexistingFlightName("Can not add user to "+flight.getName()+"\nFlight name does not exist");
    }
    public void removeUserFromFlightUserList(Flight flight) throws UnexistingUserName
    {
        flight.removeUser();
    }
    public void readUsersFromFlightUserList(Flight flight) throws UnexistingUserName
    {
        flight.readUsers();
    }
    public void flightsJoinedByUser() throws UnexistingUserName {
        User user1 = null;
        System.out.println("Enter the first name of the user for that you want to see the flights he joined:");
        String fname = scanner.nextLine();
        System.out.println("Enter the last name of the user for that you want to see the flights he joined:");
        String lname = scanner.nextLine();
        for (User user : users) {
            if (user.getLname().equals(lname) && user.getFname().equals(fname)) {
                user1 = user;
            }
        }
        if (user1 == null) {
            throw new UnexistingUserName("User name does not exists");
        }
        final User user2=user1;
        flights.stream().filter(s->s.checkUser(user2)).forEach(flight -> System.out.println(flight.getName()+ " "+
            flight.getDestination()+" "+flight.getStatus()));
    }
    public void howManyFlightsUserJoined() throws UnexistingUserName
    {
        User user1 = null;
        System.out.println("Enter the first name of the user for that you want to see how many flights he joined:");
        String fname = scanner.nextLine();
        System.out.println("Enter the last name of the user for that you want to see how many flights he joined:");
        String lname = scanner.nextLine();
        for (User user : users) {
            if (user.getLname().equals(lname) && user.getFname().equals(fname)) {
                user1 = user;
            }
        }
        if (user1 == null) {
            throw new UnexistingUserName("User name does not exists");
        }
        final User user2=user1;
        long count=flights.stream().filter(s->s.checkUser(user2)).count();
        System.out.println(user2.getFname()+" "+user2.getLname()+" joined "+count+" flights.");
    }
    public void userVisitedDestinations() throws UnexistingUserName
    {
        User user1 = null;
        System.out.println("Enter the first name of the user for that you want to see what destinations he visited:");
        String fname = scanner.nextLine();
        System.out.println("Enter the last name of the user for that you want to see what destinations he visited:");
        String lname = scanner.nextLine();
        for (User user : users) {
            if (user.getLname().equals(lname) && user.getFname().equals(fname)) {
                user1 = user;
            }
        }
        if (user1 == null) {
            throw new UnexistingUserName("User name does not exists");
        }
        final User user2=user1;
        System.out.println(user2.getFname()+" "+user2.getLname()+" visited the follow destinations:");
        flights
                .stream()
                .filter(s->s.checkUser(user2))
                .filter(s->s.getStatus().equals(StatusEnum.FINISHED))
                .forEach(flight -> System.out.println(flight.getDestination()));
    }
    public void howManyDestinationsUserVisited() throws UnexistingUserName
    {
        User user1 = null;
        System.out.println("Enter the first name of the user for that you want to see how many flights he joined:");
        String fname = scanner.nextLine();
        System.out.println("Enter the last name of the user for that you want to see how many flights he joined:");
        String lname = scanner.nextLine();
        for (User user : users) {
            if (user.getLname().equals(lname) && user.getFname().equals(fname)) {
                user1 = user;
            }
        }
        if (user1 == null) {
            throw new UnexistingUserName("User name does not exists");
        }
        final User user2=user1;
        long count=flights
                .stream()
                .filter(s->s.checkUser(user2))
                .filter(s->s.getStatus().equals(StatusEnum.FINISHED))
                .count();
        System.out.println(user2.getFname()+" "+user2.getLname()+" visited "+count+" destinations ");
    }
    public void flightsScheduled()
    {
        System.out.println("The flights scheduled in the future are:");
        flights
                .stream()
                .filter(s -> s.getStatus().equals(StatusEnum.SCHEDULED))
                .filter(s -> s.getDepartureDate().after(new Date()))
                .forEach(flight -> System.out.println(flight.getName()));
    }
    public void howManyFlightsScheduled()
    {
        long count=flights
                .stream()
                .filter(s -> s.getStatus().equals(StatusEnum.SCHEDULED))
                .filter(s -> s.getDepartureDate().after(new Date()))
                .count();
        System.out.println("The number of flights scheduled in the future is "+count);
    }
    public void flightsLanded()
    {
        System.out.println("The flights that landed are:\n");
        flights
                .stream()
                .filter(s -> s.getStatus().equals(StatusEnum.FINISHED))
                .forEach(flight -> System.out.println(flight.getName()+" landed at "+flight.getDestination()));
    }
    public void howManyFlightsLanded()
    {
        long count=
                flights
                .stream()
                .filter(s -> s.getStatus().equals(StatusEnum.FINISHED))
                .count();
        System.out.println("The number of flights that landed is "+count);
    }
    public Set<Flight> getFlights()
    {
        return flights;
    }

    public Set<User> getUsers()
    {
        return users;
    }
}
