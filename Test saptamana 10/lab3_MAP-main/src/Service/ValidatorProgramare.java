package Service;

import Domain.Programare;
import Repository.RepoAbstract;

public class ValidatorProgramare<T extends Programare> extends Validator<T>{
    @Override
    public boolean valid_id(RepoAbstract<T> rep, T ent) {
        try {
            for (Programare o : rep.getAll()) if (ent.getId() == o.getId()) return false;

            int zi_ent = Integer.parseInt(ent.getData().split("/")[0]);
            int luna_ent = Integer.parseInt(ent.getData().split("/")[1]);

            int ora_ent = Integer.parseInt(ent.getOra().split(":")[0]);
            int minute_ent = Integer.parseInt(ent.getOra().split(":")[1]);

            for (Programare o : rep.getAll()) {
                int zi_o = Integer.parseInt(o.getData().split("/")[0]);
                int luna_o = Integer.parseInt(o.getData().split("/")[1]);
                if (luna_o == luna_ent) if (zi_o == zi_ent) {
                    int ora_o = Integer.parseInt(o.getOra().split(":")[0]);
                    int minute_o = Integer.parseInt(o.getOra().split(":")[1]);
                    if (ora_o == ora_ent) if (minute_ent == minute_o) return false;
                }
            }
            return true;}   catch(NumberFormatException e)  {System.out.println("*** nu ati introdus datele corect. ***");return false;}
    }
}
