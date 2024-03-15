package Service;


import Domain.Pacient;
import Repository.RepoAbstract;

public class ValidatorPacient<T extends Pacient>  extends Validator<T>{

    @Override
    public boolean valid_id(RepoAbstract<T> rep, T ent) {
        for (Pacient o : rep.getAll())if(ent.getId() == o.getId()) return false;
        return true;
    }
}
