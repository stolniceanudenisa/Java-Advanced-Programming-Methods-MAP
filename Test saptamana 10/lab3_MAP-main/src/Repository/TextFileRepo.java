package Repository;

import Domain.Entity;
import Domain.IEntityFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextFileRepo<T extends Entity> extends MemoryRepo<T> {
    private final String fileName;
    private final IEntityFactory<T> EntityFactory;

    public TextFileRepo(String fileName, IEntityFactory<T> entityFactory){
        this.fileName = fileName;
        EntityFactory = entityFactory;

        try {
            LoadFile();
            SaveFile();
        } catch (RepoException e) {System.out.println();}
    }

    public void LoadFile() throws RepoException {
        File fisier = new File(fileName);
        try {
            Scanner read = new Scanner(fisier);

        while(read.hasNext()){
            String linie = read.nextLine();
            T Ent = EntityFactory.creareEntitate(linie);
            super.add(Ent);
        }
        read.close();
        }catch (FileNotFoundException e){System.out.println("nu s-a putut deschide fisierul.");}
    }

    @Override
    public void add(T ent) throws RepoException {
        super.add(ent);
        SaveFile();
    }

    @Override
    public void delete(int ID) throws RepoException {
        super.delete(ID);
        SaveFile();
    }

    @Override
    public void update(T ent) throws RepoException {
        super.update(ent);
        SaveFile();
    }

    public void SaveFile(){
        try {
            FileWriter writer = new FileWriter(fileName);
        for(T o : elems) {
            String linie = EntityFactory.scriereEntitate(o);
            writer.write(linie);
        }
            writer.close();
        } catch (IOException e) {System.out.println("nu s-a putut deschide fisierul.");}

    }
}
