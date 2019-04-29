package com.Eduardo;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
public class User
{
    private String fname;
    private String lname;
    private Date birthDate;
    Format format=new SimpleDateFormat("yyyy.MM.dd");

    public User(String fname, String lname, String birthDate)
    {
        this.fname = fname;
        this.lname = lname;
        setBirthDate(birthDate);
    }
    public void setBirthDate(String birthDate)
    {
        try
        {
            this.birthDate=(Date) format.parseObject(birthDate);
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
    }
    public String getFname()
    {
        return fname;
    }

    public String getLname()
    {
        return lname;
    }

    public Date getBirthDate()
    {
        return birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getFname(), user.getFname()) &&
                Objects.equals(getLname(), user.getLname());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getFname(), getLname());
    }

    @Override
    public String toString() {
        return "User{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
