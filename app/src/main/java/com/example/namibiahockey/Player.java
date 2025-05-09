package com.example.namibiahockey;

public class Player {
    private String id;  // Make sure this field exists
    private String name;
    private int age;
    private String position;
    private String contact;

    // Required empty constructor for Firestore
    public Player() {}

    // Add this setter method
    public void setId(String id) {
        this.id = id;
    }

    // Make sure all other getters exist
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPosition() { return position; }
    public String getContact() { return contact; }
}