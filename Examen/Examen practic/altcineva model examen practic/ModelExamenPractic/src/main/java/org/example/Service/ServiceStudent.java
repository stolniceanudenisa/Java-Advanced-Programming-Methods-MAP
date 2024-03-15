package org.example.Service;

import org.example.Domain.Student;
import org.example.Repository.RepoDbStudent;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ServiceStudent {
    private RepoDbStudent repo;

    public ServiceStudent(RepoDbStudent repo) {
        this.repo = repo;
    }

    public Optional<Student> findById(long id) {
        return this.repo.findById(id);
    }

    public List<Student> findAll() {
        return this.repo.findAll();
    }

    public void add(Student student) throws Exception {
//        for (Student s : this.repo.findAll()) {
//            if (student.equals(s))
//                throw new Exception("Studentul exista deja!");
//        }

        this.repo.save(student);
    }

    public void delete(long id) {
        this.repo.delete(id);
    }

    public Student findStudent(String nume, String prenume) throws Exception {
        for (Student s : this.repo.findAll()) {
            if (Objects.equals(s.getNume(), nume)
                    && Objects.equals(s.getPrenume(), prenume)) {
                return s;
            }
        }
        throw new Exception("Nu exista studentul");
    }

    public List<Student> filterByGroup(String group) {
        List<Student> students = this.repo.findAll();
        List<Student> result = students.stream().filter(s -> Objects.equals(s.getGrupa(), group)).toList();
        return result;
    }

    public List<Student> filterByGroupGrade(String group, Double grade) {
        List<Student> students = this.repo.findAll();

        List<Student> result = students.stream().filter(s -> Objects.equals(s.getGrupa(), group)
                && grade < s.getMedia()).toList();
        return result;
    }
}
