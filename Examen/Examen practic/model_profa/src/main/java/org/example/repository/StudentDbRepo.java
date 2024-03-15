package org.example.repository;

import org.example.domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StudentDbRepo implements Repository<Student> {
    private String url = "jdbc:postgresql://localhost:5432/facultate";
    private String username = "postgres";
    private String password = "postgres";

    private static StudentDbRepo instance = null;
    private StudentDbRepo() {
    }

    public static StudentDbRepo getInstance() {
        if(instance == null) {
            instance = new StudentDbRepo();
        }
        return instance;
    }

    @Override
    public List<Student> getAll() {
        List<Student> studenti = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from studenti");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String grupa = resultSet.getString("grupa");
                Double media = resultSet.getDouble("media");

                Student student = new Student(id,nume, prenume, grupa, media);

                studenti.add(student);
            }
            return studenti;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studenti;
    }

    @Override
    public Student getOne(Long id) { return null; }


    @Override
    public void verifyEntity(Student entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null");
        HashSet<Student> all = (HashSet<Student>) getAll();
        if (all.contains(entity)) {
            throw new IllegalArgumentException("the id must be unique");
        }
    }

    @Override
    public int size() {
        String sql = "SELECT COUNT(*) FROM studenti";

        int size = 0;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            size = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    @Override
    public void save(Student entity) {
        String sql = "insert into tablouri(id,nume,prenume,grupa,media)values(?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getNume());
            ps.setString(3, entity.getPrenume());
            ps.setString(4, entity.getGrupa());
            ps.setDouble(5, entity.getMedia());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Student delete(Long id) {
        return null;
    }

    @Override
    public Student update(Student oldEntity, Student newEntity) {
        return null;
    }
}
