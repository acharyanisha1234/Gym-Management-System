package Gym;

import java.io.Serializable;

public class GymMember implements Serializable {
    private static final long serialVersionUID = 1L;

    // Common attributes
    protected int id;
    protected String name;
    protected String location;
    protected String phone;
    protected String email;
    protected String gender;
    protected String dob;
    protected String trainer;
    protected boolean activeStatus = false;
    protected int attendance = 0;
    protected int loyaltyPoints = 0;

    // Constructor
    public GymMember(int id, String name, String location, String phone, String email, String gender, String dob, String trainer) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.trainer = trainer;
    }

    // Accessor methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getDob() {
        return dob;
    }

    public String getTrainer() {
        return trainer;
    }

    public boolean isActive() {
        return activeStatus;
    }

    public void setActiveStatus(boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public int getAttendance() {
        return attendance;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    // Method to mark attendance
    public void markAttendance() {
        attendance++;
        loyaltyPoints += 5; // Default loyalty points for all members
    }

    // Method to reset member details
    public void resetMember() {
        this.name = "";
        this.location = "";
        this.phone = "";
        this.email = "";
        this.gender = "";
        this.dob = "";
        this.trainer = "";
        this.activeStatus = false;
        this.attendance = 0;
        this.loyaltyPoints = 0;
    }

    // Method to display member details
    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Gender: " + gender);
        System.out.println("DOB: " + dob);
        System.out.println("Trainer: " + trainer);
        System.out.println("Active Status: " + (activeStatus ? "Active" : "Inactive"));
        System.out.println("Attendance: " + attendance);
        System.out.println("Loyalty Points: " + loyaltyPoints);
    }
}

