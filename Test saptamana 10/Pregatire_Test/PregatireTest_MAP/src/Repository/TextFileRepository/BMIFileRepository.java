package Repository.TextFileRepository;

import Domain.BMI;
import Domain.HealthData;
import Repository.Repository;

import java.io.*;

public class BMIFileRepository<T extends HealthData> extends Repository<Domain.BMI> {
    private String fileName;

    public BMIFileRepository(String fileName) {
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
    public void addEntity(BMI o) {
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
            for (BMI bmi : entitati) {
                String linie = convertEntityInLine(bmi);

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
                BMI entitate = convertReadLineToEntity(linie);

                //Adaugam informatiile din fisier, in lista noastra de elemente
                entitati.add(entitate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String convertEntityInLine(BMI bmi) {
//        System.out.println("ConvEntityInLine");

        // Input in the format that we have got
        String data = bmi.getDate();
        float value = bmi.getValue();

        String result = data + " " + value;
        return result; // Trebuie implementat conform logicii entității specifice
    }

    private BMI convertReadLineToEntity(String linie) {

        String[] parts = linie.split(" "); // Presupunem că datele din fișier sunt separate prin virgulă

        BMI bmi;
        String data = parts[0]; // ID-ul este primul element
        float value = Float.parseFloat(parts[1]);
        bmi = new BMI(data, value);

        return bmi;
    }
}
