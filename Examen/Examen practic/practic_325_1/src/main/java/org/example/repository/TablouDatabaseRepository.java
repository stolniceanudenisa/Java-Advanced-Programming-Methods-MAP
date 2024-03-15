package org.example.repository;

import org.example.domain.Tablou;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TablouDatabaseRepository implements Repository<Tablou> {

    private String url = "jdbc:postgresql://localhost:5432/practic325";
    private String username = "postgres";
    private String password = "postgres";


    private static TablouDatabaseRepository instance = null;
    private TablouDatabaseRepository() {
    }

    public static TablouDatabaseRepository getInstance() {
        if(instance == null) {
            instance = new TablouDatabaseRepository();
        }
        return instance;
    }


    @Override
    public List<Tablou> getAll() {
        List<Tablou> tablouri = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement("SELECT * from tablouri");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String titlu = resultSet.getString("titlu");
                String pictor = resultSet.getString("pictor");
                String tematica = resultSet.getString("tematica");
                Double celebritate = resultSet.getDouble("celebritate");

                Tablou tablou = new Tablou(id,titlu,pictor,tematica,celebritate);

                tablouri.add(tablou);
            }
            return tablouri;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tablouri;
    }

    @Override
    public Tablou getOne(Long id) { return null; }


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
        String sql = "insert into tablouri(id,titlu,pictor,tematica,celebritate)values(?,?,?,?,?)";

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
    public Tablou update(Tablou oldEntity, Tablou newEntity) {
        return null;
    }
}
