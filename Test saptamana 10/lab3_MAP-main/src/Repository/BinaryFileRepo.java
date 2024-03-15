package Repository;

import Domain.Entity;

import java.io.*;

public class BinaryFileRepo<T extends Entity> extends MemoryRepo<T> {
    private final String fileName;

    public BinaryFileRepo(String fileName) {
        this.fileName = fileName;
        try {
            loadFile();
        }catch (IOException e){System.out.println("\033[31m"+"nu s-a putut deschide fisierul " + e.getMessage());}  catch (ClassNotFoundException | RepoException e){System.out.println();}
    }

    @Override
    public void add(T p) throws RepoException {
        super.add(p);
        try {
            saveFile();
        }catch (IOException e){throw new RepoException("\033[31m"+"Eroare la salvare in fisier "+ e.getMessage());}

    }

    @Override
    public void delete(int ID) throws RepoException {
        super.delete(ID);
        try {
            saveFile();
        }catch (IOException e){throw new RepoException("\033[31m"+"Eroare la salvare in fisier "+ e.getMessage());}
    }

    @Override
    public void update(T ent) throws RepoException {
        super.update(ent);
        try {
            saveFile();
        }catch (IOException e){throw new RepoException("\033[31m"+"Eroare la salvare in fisier "+ e.getMessage());}
    }

    private void loadFile() throws IOException, ClassNotFoundException, RepoException {

        T ent;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.available() >0) {
                ent = (T) ois.readObject();
                super.add(ent);
            }
            ois.close();
        }catch (EOFException ex){return;}

    }

    private void saveFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for(T o: elems) oos.writeObject(o);
        oos.close();
    }


}
