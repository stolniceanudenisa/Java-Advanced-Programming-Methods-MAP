package org.example.repository;

import org.example.domain.*;

import java.sql.*;
import java.util.*;

public class TablouDatabaseRepository implements Repository<Tablou> {
    private static TablouDatabaseRepository instance = null;
    private TablouDatabaseRepository() {
    }
    public static TablouDatabaseRepository getInstance() {
        if(instance == null) {
            instance = new TablouDatabaseRepository();
        }
        return instance;
    }
    private final String url = "jdbc:postgresql://localhost:5432/examen";
    private final String username = "postgres";
    private final String password = "15dovlecel";

    @Override
    public Tablou getOne(Long id) {
        String sql = "SELECT * from tablouri WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            //long id1 = resultSet.getLong("id");
            String titlu = resultSet.getString("titlu");
            String pictor = resultSet.getString("pictor");
            String tematica = resultSet.getString("tematica");
            Double celebritate = resultSet.getDouble("celebritate");

            //resultSet.next();

            return new Tablou(titlu, pictor, tematica, celebritate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Iterable<Tablou> getAll() {
        Set<Tablou> tablouSet = new HashSet<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from tablouri");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                //long id1 = resultSet.getLong("id");
                String titlu = resultSet.getString("titlu");
                String pictor = resultSet.getString("pictor");
                String tematica = resultSet.getString("tematica");
                Double celebritate = resultSet.getDouble("celebritate");

                Tablou tablou = new Tablou(titlu,pictor,tematica,celebritate);
                tablouSet.add(tablou);

            }
            return tablouSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablouSet;
    }

    public ArrayList<Tablou> getAll2() {
        ArrayList<Tablou> tablouri = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from tablouri");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                long id1 = resultSet.getLong("id");
                String titlu = resultSet.getString("titlu");
                String pictor = resultSet.getString("pictor");
                String tematica = resultSet.getString("tematica");
                Double celebritate = resultSet.getDouble("celebritate");

                Tablou tablou = new Tablou(titlu,pictor,tematica,celebritate);
                tablou.setId(id1);
                tablouri.add(tablou);
            }
            return tablouri;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablouri;
    }

    @Override
    public void verifyEntity(Tablou entity) {
        if (entity == null)
            throw new IllegalArgumentException("entity must be not null");
        HashSet<Tablou> all = (HashSet<Tablou>) getAll();
        if (all.contains(entity)) {
            throw new IllegalArgumentException("the id must be unique");
        }
    }

    @Override
    public void save(Tablou entity) {
        String sql = "insert into tablouri(titlu,pictor,tematica,celebritate)values(?,?,?,?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, entity.getTitlu());
            ps.setString(2, entity.getPictor());
            ps.setString(3, entity.getTematica());
            ps.setDouble(4, entity.getCelebritate());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        String sql = "SELECT COUNT(*) FROM tablouri";

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
    public Tablou delete(Long id) {
        return null;
    }

    @Override
    public Tablou update(Tablou entity) {
        return null;
    }
}
