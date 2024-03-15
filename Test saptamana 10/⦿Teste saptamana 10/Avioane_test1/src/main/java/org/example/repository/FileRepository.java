package org.example.repository;


import org.example.domain.FlightInstrument;
import org.example.domain.IEntityFactory;
import org.example.exceptions.DuplicateEntityException;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class FileRepository<T extends FlightInstrument> extends MemoryRepository<T> {
    private String fileName;
    private IEntityFactory<T> entityFactory;


    public FileRepository(String fileName, IEntityFactory<T> entityFactory) throws FileNotFoundException, DuplicateEntityException {
        this.fileName = fileName;
        this.entityFactory = entityFactory;
        readFromFile();
    }

    public void readFromFile() throws FileNotFoundException, DuplicateEntityException {
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            T entity = entityFactory.createEntity(line);
            add(entity);
        }
    }

    public void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (T entity : entities) {
                String entityString = entityFactory.createString(entity);
                writer.write(entityString + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void overwriteAll(List<T> maintenanceSoftwareList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (T entity : maintenanceSoftwareList) {
                String entityString = entityFactory.createString(entity);
                writer.write(entityString + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}