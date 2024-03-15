package Repository.TextFileRepository;

import Domain.BP;
import Domain.HealthData;
import Repository.Repository;

import java.io.*;

public class BPFileRepository<T extends HealthData> extends Repository<Domain.BP> {
    private String fileName;

    public BPFileRepository(String fileName) {
        this.fileName = fileName;
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            // Citim din fisierul Text, si incarcam Lista noastra cu elemente
        } catch (IOException e) {
            e.printStackTrace();
        }
        readFromFile();
    }

    @Override
    public void addEntity(BP o) {
        // Apeleaza method add din MemoryRepository
        super.addEntity(o);

        // saveFile se executa doar daca super.add() nu a aruncat exceptie
        try {
            writeInFile();
        } catch (IOException e) {
            throw new RuntimeException("Error saving file " + e.getMessage());
        }
    }

    private void writeInFile() throws IOException {
        // FIXME try with resources
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (BP bp : entitati) {
                String linie = convertEntityInLine(bp);

                bw.write(linie);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String linie;
            while ((linie = br.readLine()) != null) {
                BP entitate = convertReadLineToEntity(linie);

                //Adaugam informatiile din fisier, in lista noastra de elemente
                entitati.add(entitate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertEntityInLine(BP bp) {
//        System.out.println("ConvEntityInLine");

        // Input in the format that we have got
        String data = bp.getDate();
        int systolicValue = bp.getSystolicValue();
        int diastolicValue = bp.getDiastolicValue();

        String result = data + " " + systolicValue + " " + diastolicValue;
        return result; // Trebuie implementat conform logicii entității specifice
    }

    private BP convertReadLineToEntity(String linie) {

        String[] parts = linie.split(" "); // Presupunem că datele din fișier sunt separate prin virgulă

        BP bp;
        String data = parts[0]; // ID-ul este primul element
        int systolicValue = Integer.parseInt(parts[1]);
        int diastolicValue = Integer.parseInt(parts[2]);
        bp = new BP(data, systolicValue, diastolicValue);

        return bp;
    }
}
