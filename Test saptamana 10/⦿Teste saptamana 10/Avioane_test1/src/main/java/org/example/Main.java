package org.example;

import org.example.domain.HardwareFactory;
import org.example.domain.HardwareInstrument;
import org.example.domain.SoftwareFactory;
import org.example.domain.SoftwareInstrument;
import org.example.exceptions.DuplicateEntityException;
import org.example.repository.FileRepository;
import org.example.repository.IRepository;
import org.example.service.HardwareInstrumentService;
import org.example.service.SoftwareInstrumentService;
import org.example.ui.Ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

       // System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("settings.properties")) {
            properties.load(input);

            String repositoryType = properties.getProperty("Repository");
            String softwareFile = properties.getProperty("Software");
            String hardwareFile = properties.getProperty("Hardware");

            IRepository<SoftwareInstrument> softwareInstrumentRepository;
            IRepository<HardwareInstrument> hardwareInstrumentsRepository;



                softwareInstrumentRepository = new FileRepository<>(softwareFile, new SoftwareFactory());
                //IRepository<SoftwareInstrument> pacientRepository = new FileRepository<>(softwareFile, new SoftwareFactory());
                hardwareInstrumentsRepository = new FileRepository<>(hardwareFile, new HardwareFactory());


            SoftwareInstrumentService softwareInstrumentService = new SoftwareInstrumentService(softwareInstrumentRepository);
            HardwareInstrumentService hardwareInstrumentService = new HardwareInstrumentService(hardwareInstrumentsRepository);
                Ui menu = new Ui(softwareInstrumentService, hardwareInstrumentService);
                menu.run();
            } catch(IOException e){
                System.err.println("Error: " + e.getMessage());
            } catch(DuplicateEntityException e){
                throw new RuntimeException(e);
            } catch(RuntimeException e){
                System.err.println("Error: " + e.getMessage());
            }


    }
}