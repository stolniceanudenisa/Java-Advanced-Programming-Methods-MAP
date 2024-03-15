import Domain.*;
import Repository.BinaryFileRepo;
import Repository.MemoryRepo;
import Repository.RepoAbstract;
import Repository.TextFileRepo;
import Service.Service;
import Service.Validator;
import Service.ValidatorPacient;
import Service.ValidatorProgramare;
import UI.Consola;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;



public class Main {

    public static void main(String[] args) {

        Properties properties = new Properties();
        InputStream read_prop;
        try{

            read_prop = new FileInputStream("settings.properties");
            properties.load(read_prop);

            RepoAbstract<Pacient> repPacient = null;
            RepoAbstract<Programare> repProgramare = null;

            if(Objects.equals(properties.getProperty("Repository_Pacient"), "Text")){
                IEntityFactory<Pacient> PacientFactory = new PacientFactory();
                repPacient = new TextFileRepo<>(properties.getProperty("Pacienti"),PacientFactory);
            }
            else if (Objects.equals(properties.getProperty("Repository_Pacient"),"Binary")) {
                repPacient = new BinaryFileRepo<>(properties.getProperty("Pacienti"));

            } else if (Objects.equals(properties.getProperty("Repository_Pacient"), "Memory")) {
                repPacient = new MemoryRepo<>();
            }


            if(Objects.equals(properties.getProperty("Repository_Progr"), "Text")){
                IEntityFactory<Programare> ProgramareFactory = new ProgramareFactory();
                repProgramare = new TextFileRepo<>(properties.getProperty("Programari"), ProgramareFactory);
            }
            else if (Objects.equals(properties.getProperty("Repository_Progr"),"Binary")) {
                repProgramare= new BinaryFileRepo<>(properties.getProperty("Programari"));

            } else if (Objects.equals(properties.getProperty("Repository_Progr"), "Memory")) {
                repProgramare = new MemoryRepo<>();
            }


            Validator<Pacient> valPacient = new ValidatorPacient<>();
            Validator<Programare> valPRogramare = new ValidatorProgramare<>();

            Service<Pacient> ser1 = new Service<Pacient>(repPacient,valPacient);
            Service<Programare> ser2 = new Service<Programare>(repProgramare,valPRogramare);
            Consola cons = new Consola(ser1, ser2);

            cons.meniu();


        } catch (IOException e){
            System.out.println("greseala " + e.getMessage());

        }
    }
}