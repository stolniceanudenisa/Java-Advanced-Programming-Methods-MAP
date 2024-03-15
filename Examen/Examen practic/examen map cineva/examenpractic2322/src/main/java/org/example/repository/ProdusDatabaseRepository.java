package org.example.repository;

import org.example.domain.*;

import java.sql.*;
import java.util.*;

public class ProdusDatabaseRepository implements Repository<Produs>{
    private static ProdusDatabaseRepository instance = null;
    private ProdusDatabaseRepository() {
    }
    public static ProdusDatabaseRepository getInstance() {
        if(instance == null) {
            instance = new ProdusDatabaseRepository();
        }
        return instance;
    }
    private final String url = "jdbc:postgresql://localhost:5432/lastmodel";
    private final String username = "postgres";
    private final String password = "15dovlecel";

    @Override
    public Produs getOne(Long id) {
        String sql = "SELECT * from produse WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            //long id1 = resultSet.getLong("id");
            String nume = resultSet.getString("nume");
            String categorie = resultSet.getString("categorie");
            String descriere = resultSet.getString("descriere");
            Double pret = resultSet.getDouble("pret");

            //resultSet.next();

            return new Produs(nume, categorie, descriere, pret);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Produs> getAll() {
        Set<Produs> produse = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from produse");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                //long id1 = resultSet.getLong("id");
                String nume = resultSet.getString("nume");
                String categorie = resultSet.getString("categorie");
                String descriere = resultSet.getString("descriere");
                Double pret = resultSet.getDouble("pret");

                Produs p = new Produs(nume,categorie,descriere,pret);
                produse.add(p);

            }
            return produse;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produse;
    }

    public ArrayList<Produs> getAll2() {
        ArrayList<Produs> carti = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from produse");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                long id1 = resultSet.getLong("id");
                String nume = resultSet.getString("nume");
                String categorie = resultSet.getString("categorie");
                String descriere = resultSet.getString("descriere");
                Double pret = resultSet.getDouble("pret");

                Produs p = new Produs(nume,categorie,descriere,pret);
                p.setId(id1);
                carti.add(p);
            }
            return carti;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carti;
    }

    @Override
    public void verifyEntity(Produs entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null");
        HashSet<Produs> all = (HashSet<Produs>) getAll();
        if (all.contains(entity)) {
            throw new IllegalArgumentException("the id must be unique");
        }
    }

    @Override
    public void save(Produs entity) {
        String sql = "insert into produse(nume,categorie,descriere,pret)values(?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, entity.getNume());
            ps.setString(2, entity.getCategorie());
            ps.setString(3, entity.getDescriere());
            ps.setDouble(4, entity.getPret());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        String sql = "SELECT COUNT(*) FROM produse";

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
    public Produs delete(Long id) {
        return null;
    }

    @Override
    public Produs update(Produs entity) {
        return null;
    }
}
