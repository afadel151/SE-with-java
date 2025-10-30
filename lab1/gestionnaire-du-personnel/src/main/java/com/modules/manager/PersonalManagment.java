package com.modules.manager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.modules.entities.Student;


public class PersonalManagment {
    private  Map<Integer, Student> students = new HashMap<>();
    private final String DATA_FILE = "./students.ser";
    public static void main(String[] args) {
        Student student = new Student("Akram", 1,"Computer Science", 2);
        System.out.println(student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values()); 
    }
    public Student chercherEtudiant(int id) {
        return students.get(id);
    }
    
    public boolean ajouterEtudiant(Student student) {
        try {
            students.put(student.getId(), student);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout de l'étudiant: ");
            return false;
        }
        return true;

    }
    public boolean ajouterEtudiant(String name, int id, String major, int year) {
        Student student = new Student(name, id, major, year);
        return ajouterEtudiant(student);
    }
    public void sauveguarderEtudiants() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des étudiants: " + e.getMessage());
        }
        
    }

    public void retrieveEtudiants() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            @SuppressWarnings("unchecked")
            Map<Integer, Student> loadedStudents = (Map<Integer, Student>) ois.readObject();
            students.clear();
            students.putAll(loadedStudents);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement des étudiants: " + e.getMessage());
        }
    }
}
