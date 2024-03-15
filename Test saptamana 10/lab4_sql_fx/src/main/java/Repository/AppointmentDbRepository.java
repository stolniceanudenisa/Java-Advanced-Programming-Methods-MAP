package Repository;

import Domain.Pacient;
import Domain.Programare;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class AppointmentDbRepository extends MemoryRepository<Programare> {
    private String JDBC_URL = "jdbc:sqlite:appointments_db.sqlite";

    Connection connection;
    public AppointmentDbRepository(){
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
            st.execute("CREATE TABLE IF NOT EXISTS appointments(programare_id int, pacient_id int, data varchar(50),ora varchar(30), scopul_programarii varchar(100));");
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }


    private void loadDataInMemory() {
        for (Programare p : getAll())
        {
            entities.add(p);
            //super.add(p);
        }
    }
    private void initData() {
        List<Programare> programari = new ArrayList<>();

        Pacient pacient1 = new Pacient(1, "Ion", "Pop", 23);
        Pacient pacient2 = new Pacient(2, "Alexandra", "Ionescu", 25);
        Pacient pacient3 = new Pacient(3, "Radu", "Oprea", 30);

        programari.add(new Programare(1, pacient1, "2023-12-31", "10:00", "Checkup"));
        programari.add(new Programare(2, pacient2, "2023-12-31", "11:00", "Follow-up"));
        programari.add(new Programare(3, pacient3, "2023-12-31", "12:00", "Consultation"));



        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO appointments (programare_id, pacient_id, data, ora, scopul_programarii) VALUES (?, ?, ?, ?, ?)")) {
            for (Programare p : programari) {
                statement.setInt(1, p.getId());
                statement.setInt(2, p.getPacient().getId());
                statement.setString(3, p.getData());
                statement.setString(4, p.getOra());
                statement.setString(5, p.getScopulProgramarii());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Collection<Programare> getAll()
    {
        List<Programare> programari = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM appointments")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next())
            {
                int pacientId = rs.getInt("pacient_id"); // Get the pacient_id from the ResultSet

                Pacient pacient = new Pacient(pacientId, "Ion", "Pop", 23);

                programari.add(new Programare(rs.getInt(1), pacient, rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return programari;
    }
//
//    public Collection<Programare> getAll() {
//        List<Programare> programari = new ArrayList<>();
//        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM appointments")) {
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                int pacientId = rs.getInt("pacient_id"); // Get the pacient_id from the ResultSet
//                // Fetch the Pacient object associated with pacientId from your repository or database
//
//                // For demonstration purposes, let's assume you have a method getPacientById in PacientDbRepository
//                // Replace this with your appropriate way of fetching Pacient objects by their ID
//                Pacient pacient = getId(pacientId);
//
//                programari.add(new Programare(
//                        rs.getInt(1), // Assuming rs.getInt(1) is the ID of Programare
//                        pacient, // Add the fetched Pacient object here
//                        rs.getString(2), // Assuming rs.getString(2) is the data
//                        rs.getString(3), // Assuming rs.getString(3) is the ora
//                        rs.getString(4) // Assuming rs.getString(4) is the scopulProgramarii
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return programari;
//    }


    public void add(Programare p) throws DuplicateEntityException {
        super.add(p);
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO appointments VALUES (?, ?, ?, ?, ?)")) {
            statement.setInt(1, p.getId());
            statement.setInt(2, p.getPacient().getId());
            statement.setString(3, p.getData());
            statement.setString(4, p.getOra());
            statement.setString(5, p.getScopulProgramarii());
            statement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private LocalDate generateRandomDate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(2022, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2023, 12, 31).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        return LocalDate.ofEpochDay(randomDay);
    }

    private String generateRandomTime() {
        Random random = new Random();
        int hour = random.nextInt(24);
        int minute = random.nextInt(60);

        return String.format("%02d:%02d", hour, minute);
    }

    private String[] generateRandomScopulProgramarii() {
        String[] scopuri = {"Checkup", "Follow-up", "Consultation", "Treatment", "Therapy", "Examination"};
        Random random = new Random();
        return new String[]{scopuri[random.nextInt(scopuri.length)]};
    }

    private void initDataRandom() {
        List<Programare> programari = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Pacient pacient = new Pacient(i + 1, "Name", "Surname", 25); // Replace with actual random patient details

            LocalDate randomDate = generateRandomDate();
            String randomTime = generateRandomTime();
            String randomScop = Arrays.toString(generateRandomScopulProgramarii());

            programari.add(new Programare(i + 1, pacient, randomDate.toString(), randomTime, randomScop));
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO appointments (programare_id, pacient_id, data, ora, scopul_programarii) VALUES (?, ?, ?, ?, ?)")) {
            for (Programare p : programari) {
                statement.setInt(1, p.getId());
                statement.setInt(2, p.getPacient().getId());
                statement.setString(3, p.getData());
                statement.setString(4, p.getOra());
                statement.setString(5, p.getScopulProgramarii());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}