# Student-Management-System
A simple Student Management System built in Java using Serialization and File Handling to store and manage student records persistently without using a database.

Features
Add new student records
View all students
Search student by details
Update student information
Delete student records
Sort students based on marks
Display topper
Count total students
Export data to a readable text file

Technologies Used
Java
Object-Oriented Programming (OOP)
File Handling
Serialization & Deserialization

Project Structure
Student.java → Defines the student class and attributes
Main.java → Contains the menu-driven program logic
FileHandler.java → Handles file operations (serialization/deserialization)
students.dat → Binary file used to store serialized student objects
students.txt → Human-readable file generated from .dat data

How It Works
Student objects are serialized and stored in students.dat
Data is retrieved using deserialization when needed
The system ensures persistent storage without using a database
students.txt is generated to make the stored data readable

Java Serialization
File handling concepts
Building menu-driven console applications
