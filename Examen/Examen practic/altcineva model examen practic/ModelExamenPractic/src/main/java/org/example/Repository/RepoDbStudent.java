package org.example.Repository;

import org.example.Domain.Student;

import java.sql.*;
import java.util.*;

public class RepoDbStudent {
    private String url;
    private String username;
    private String password;

    public RepoDbStudent(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Optional<Student> findById(long id) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM student");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long idStudent = resultSet.getLong("id");
                if (idStudent == id) {
                    String nume = resultSet.getString("nume");
                    String prenume = resultSet.getString("prenume");
                    String grupa = resultSet.getString("grupa");
                    Double media = resultSet.getDouble("media");
                    Student student = new Student(idStudent, nume, prenume, grupa, media);
                    return Optional.of(student);
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM student");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String grupa = resultSet.getString("grupa");
                Double media = resultSet.getDouble("media");

                Student student = new Student(id, nume, prenume, grupa, media);
                students.add(student);
            }
            connection.close();
            return students;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void save(Student student) {
        String sql = "INSERT INTO student (nume, prenume, grupa, media) values (?, ?, ?, ?)";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, student.getNume());
            ps.setString(2, student.getPrenume());
            ps.setString(3, student.getGrupa());
            ps.setDouble(4, student.getMedia());

            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Student> delete(long id) {
        String sql = "DELETE FROM student WHERE id = ?";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, id);

            ps.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void update(Student student) {
        String sql = "UPDATE student SET nume = ? WHERE id = ?\n" +
                "UPDATE student SET prenume = ? WHERE id = ?\n" +
                "UPDATE student SET grupa = ? WHERE id = ?\n" +
                "UPDATE student SET media = ? WHERE id = ?";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, student.getNume());
            ps.setLong(2, student.getId());
            ps.setString(3, student.getPrenume());
            ps.setLong(4, student.getId());
            ps.setString(5, student.getGrupa());
            ps.setLong(6, student.getId());
            ps.setDouble(7, student.getMedia());
            ps.setLong(8, student.getId());

            ps.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

















