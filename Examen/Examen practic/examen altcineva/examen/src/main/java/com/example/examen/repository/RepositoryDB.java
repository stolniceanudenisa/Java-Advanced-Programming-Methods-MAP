package com.example.examen.repository;

import com.example.examen.domain.Tablou;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryDB implements Repository<Tablou>{
    private String url = "jdbc:postgresql://localhost:5432/examen";
    private String username = "postgres";
    private String password = "admin";
    private static RepositoryDB instance = null;
    private RepositoryDB() {
    }

    public static RepositoryDB getInstance() {
        if(instance == null) {
            instance = new RepositoryDB();
        }
        return instance;
    }

    @Override
    public void create(Tablou entity) {
        String sql = "insert into tablou (id, titlu, pictor, tematica, celebritate) values (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getTitlu());
            ps.setString(3, entity.getPictor());
            ps.setString(4, entity.getTematica());
            ps.setDouble(5, entity.getCelebritate());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tablou> read() {
        List<Tablou> entities = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from tablou");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String titlu = resultSet.getString("titlu");
                String pictor = resultSet.getString("pictor");
                String tematica = resultSet.getString("tematica");
                Double celebritate = resultSet.getDouble("celebritate");
                Tablou entity = new Tablou(id, titlu, pictor, tematica, celebritate);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    @Override
    public Tablou read(int index) {
        return null;
    }

    @Override
    public void update(Tablou oldEntity, Tablou newEntity) {
        String sql = "UPDATE friendships SET first_user_id = (?), second_user_id = (?), status = (?) WHERE id = (?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1, newEntity.getFirstUserId());
//            ps.setInt(2, newEntity.getSecondUserId());
//            ps.setString(3, newEntity.getStatus());
//            ps.setInt(4,oldEntity.getId());

            ps.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Tablou entity) {
        String sql = "DELETE FROM friendships f WHERE f.id = (?)";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {
//            ps.setInt(1,entity.getId());
//            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
