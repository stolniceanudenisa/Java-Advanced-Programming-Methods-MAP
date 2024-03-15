package Repository;

import Domain.Pacient;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class PatientsDbRepository extends MemoryRepository<Pacient> {
    private String JDBC_URL = "jdbc:sqlite:patients_db.sqlite";
    Connection connection;

    public PatientsDbRepository()
    {
        openConnection();
        createTable();
        loadDataInMemory();
        //initData();
        //initDataRandom();
    }

    private void openConnection()
    {
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl(JDBC_URL);

        try {
            if (connection == null || connection.isClosed())
            {
                connection = ds.getConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e. printStackTrace();
        }
    }

    private void createTable() {
        try (final Statement st = connection.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS patients(id int, name varchar(400),prenume varchar(400), age int);");

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void loadDataInMemory() {
        for (Pacient p : getAll())
        {
            entities.add(p);
            //super.add(p);
        }
    }

    private void initData() {
        List<Pacient> patients = new ArrayList<>();
        patients.add(new Pacient(1, "Ion","Pop", 23));
        patients.add(new Pacient(2, "Alexandra"," Ionescu", 25));
        patients.add(new Pacient(3, "Radu"," Oprea", 30));
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO patients VALUES (?,?,?,?)")) {
            for (Pacient p : patients)
            {
                statement.setInt(1, p.getId());
                statement.setString(2, p.getNume());
                statement.setString(3, p.getPrenume());
                statement.setInt(4, p.getVarsta());
                statement.executeUpdate();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Collection<Pacient> getAll()
    {
        List<Pacient> patients = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM patients")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                patients.add(new Pacient(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getInt(4)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return patients;
    }

    public void add(Pacient p) throws DuplicateEntityException {
        super.add(p);
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO patients VALUES (?,?,?,?)")) {
            statement.setInt(1, p.getId());
            statement.setString(2, p.getNume());
            statement.setString(3, p.getPrenume());
            statement.setInt(4, p.getVarsta());
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private String generateRandomName() {
        String[] names = {"John", "Emma", "Michael", "Sophia", "William", "Olivia", "James", "Ava", "Benjamin", "Isabella"};
        Random random = new Random();
        return names[random.nextInt(names.length)];
    }

    private String generateRandomSurname() {
        String[] surnames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
        Random random = new Random();
        return surnames[random.nextInt(surnames.length)];
    }

    private int generateRandomAge() {
        Random random = new Random();
        return random.nextInt(70) + 18; // Random age between 18 and 87
    }

    private void initDataRandom() {
        List<Pacient> patients = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            patients.add(new Pacient(i + 1, generateRandomName(), generateRandomSurname(), generateRandomAge()));
        }

        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO patients VALUES (?,?,?,?)")) {
            for (Pacient p : patients) {
                statement.setInt(1, p.getId());
                statement.setString(2, p.getNume());
                statement.setString(3, p.getPrenume());
                statement.setInt(4, p.getVarsta());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}