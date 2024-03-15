package org.example.service;

import org.example.domain.Student;
import org.example.repository.StudentDbRepo;

import java.util.List;

public class StudentService {
    StudentDbRepo repo = StudentDbRepo.getInstance();

    private static StudentService instance = null;

    private StudentService() {
    }

    public static StudentService getInstance() {
        if(instance == null) {
            instance = new StudentService();
        }
        return instance;
    }

    public void add(Student s) {
        System.out.println(s);
        repo.save(s);
    }

    public void delete(Long l) {
        if(repo.getOne(l) == null)
            throw new RuntimeException("Id-ul nu exista!");
        repo.delete(l);
    }


    public List<Student> getAll() {
        return repo.getAll();
    }


    public Student getById(long id) {
        return repo.getOne(id);
    }


}
