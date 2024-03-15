package org.example.repository;

import org.example.domain.Excursie;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ExcursieDbRepo implements Repository {

    private String url = "jdbc:postgresql://localhost:5432/practic";
    private String username = "postgres";
    private String password = "postgres";

    private static ExcursieDbRepo instance = null;

    private ExcursieDbRepo() {
    }

    public static ExcursieDbRepo getInstance() {
        if(instance == null) {
            instance = new ExcursieDbRepo();
        }
        return instance;
    }

    @Override
    public List<Excursie> getAll() {
        List<Excursie> excursii = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from excursii");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String oras = resultSet.getString("oras");
                String atractie = resultSet.getString("atractie");
                String categorie = resultSet.getString("categorie");
                Double pret = resultSet.getDouble("pret");

                Excursie excursie = new Excursie(id,oras, atractie, categorie, pret);

                excursii.add(excursie);
            }
            return excursii;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return excursii;
    }


    @Override
    public Excursie getOne(Long id) { return null; }


    @Override
    public void verifyEntity(Excursie entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null");
        HashSet<Excursie> all = (HashSet<Excursie>) getAll();
        if (all.contains(entity)) {
            throw new IllegalArgumentException("the id must be unique");
        }
    }

    @Override
    public int size() {
        String sql = "SELECT COUNT(*) FROM excursii";

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
    public void save(Excursie entity) {
        String sql = "insert into excursii(id,oras, atractie, categorie, pret)values(?,?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getOras());
            ps.setString(3, entity.getAtractie());
            ps.setString(4, entity.getCategorie());
            ps.setDouble(5, entity.getPret());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Excursie delete(Long id) {
        return null;
    }

    @Override
    public Excursie update(Excursie oldEntity, Excursie newEntity) {
        return null;
    }
}

