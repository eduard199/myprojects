package com.Eduardo;


import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

enum StatusEnum
{
    SCHEDULED,
    CANCELED,
    FINISHED
}


public class Flight
{
    private String name;
    private String destination;
    private Date departureDate;
    private int flightDuration;
    private int maxUserCapacity;
    private StatusEnum status;
    private Set<User> flightUserList=new HashSet<>();
    private Scanner scanner=new Scanner(System.in);
    Format format=new SimpleDateFormat("yyyy.MM.dd HH:mm");

    public Flight(String name, String destination, String departureDate, int flightDuration, int maxUserCapacity)
    {
        this.name = name;
        this.destination = destination;
        this.flightDuration = flightDuration;
        this.maxUserCapacity = maxUserCapacity;
        setDate(departureDate);
    }
    public void setDate(String departureDate)
    {
        try
        {
            this.departureDate=(Date) format.parseObject(departureDate);
        }catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    public void addUser(Set<User> users) throws UnexistingFlightName,MaxUserCapacityAchieved,UnexistingUserName,DuplicateUserName {
     //   if (getStatus() == StatusEnum.FINISHED) {
      //      throw new UnexistingFlightName("Cannot add a user to a finished flight");
      //  }
        if (getDepartureDate().before(new Date()) && getStatus() == StatusEnum.SCHEDULED) {
            throw new UnexistingFlightName("Cannot add a user to a flight that is already taken off");
        }
        if (flightUserList.size() >= getMaxUserCapacity()) {
            throw new MaxUserCapacityAchieved("The maximum capacity for this airplane it was achieved");
        }
        boolean var=true;
        System.out.println("Want to add users to the flight "+getName()+" ?\n 0:NO 1:YES");
        int ok1=scanner.nextInt();
        scanner.nextLine();
        if(ok1==0)
        {
            return;
        }
        while(var) {
            System.out.println("Enter the first name of the user that you want to add it to the user flight list for the flight "+getName());
            String fname = scanner.nextLine();
            System.out.println("Enter the last name of the user that you want to add it to the user flight list for the flight "+getName());
            String lname = scanner.nextLine();
            User user1 = null;
            for (User user : users) {
                if (user.getFname().equals(fname) && user.getLname().equals(lname)) {
                    user1 = user;
                }
            }
            if (user1 == null) {
                throw new UnexistingUserName("User name does not exists");
            }
            if (flightUserList.add(user1)) {
                System.out.println(user1.getFname() + " " + user1.getLname() + " added successfully to the flight " + getName());
            } else {
                throw new DuplicateUserName("This user name already exists on this flight");
            }
            System.out.println("Want to add another user to the flight "+getName()+"?\n0:NO  1:YES");
            int ok=scanner.nextInt();
            scanner.nextLine();
            if(ok==0)
            {
                var=false;
            }
        }
    }
    public void removeUser() throws UnexistingUserName{
        if(flightUserList.size()==0)
        {
            throw new UnexistingUserName("There are no users added to this flight: "+getName());
        }
        Iterator<User> userIterator=flightUserList.iterator();
        System.out.println("Enter the first name of the user that you want to add it to the user flight list");
        String fname = scanner.nextLine();
        System.out.println("Enter the last name of the user that you want to add it to the user flight list");
        String lname = scanner.nextLine();
        while(userIterator.hasNext())
        {
            if(userIterator.next().getFname().equals(fname)&&userIterator.next().getLname().equals(lname))
            {
                userIterator.remove();
                System.out.println(fname+" "+lname+" was removed from the flight "+getName());
                return;
            }
        }
        throw new UnexistingUserName(lname+" "+fname+" is not added to the flight");
    }
    public void readUsers() throws UnexistingUserName
    {
        if(flightUserList.size()==0)
        {
            throw new UnexistingUserName("There are no users registered to flight: "+getName());
        }
        System.out.println("The flight "+getName()+" has the following users: ");
        for(User user:flightUserList)
        {
            System.out.println(user.toString());
        }

    }
    public boolean checkUser(User user1)
    {
        for(User user:flightUserList)
        {
            if(user.getFname().equals(user1.getFname())&&user.getLname().equals(user1.getLname()))
            {
                return true;
            }
        }
        return false;

    }
    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public int getFlightDuration() {
        return flightDuration;
    }

    public int getMaxUserCapacity() {
        return maxUserCapacity;
    }

    public StatusEnum getStatus() {
        return status;
    }
    public void setStatus(StatusEnum status)
    {
        this.status = status;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof Flight))
            return false;
        Flight flight = (Flight) o;
        return Objects.equals(getName(), flight.getName());
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(getName());
    }
}
