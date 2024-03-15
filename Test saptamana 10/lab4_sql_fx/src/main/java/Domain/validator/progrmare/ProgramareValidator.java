package Domain.validator.progrmare;

import Domain.Programare;
import Domain.validator.pacient.ValidationException;
import Domain.validator.pacient.Validator;

import java.util.ArrayList;
import java.util.List;

public class ProgramareValidator implements Validator<Programare> {

    @Override
    public boolean validate(Programare entity) throws ValidationException
    {
        List<String> errors = new ArrayList<>();
        if (entity.getOra().isEmpty()) {
            errors.add("Ora programarii nu poate fi vidă");
        }


        if (entity.getScopulProgramarii().isEmpty()) {
            errors.add("Scopul programarii nu poate fi vid");
        }

        if (entity.getData().isEmpty()) {
            errors.add("Data programarii nu poate fi vidă");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join("\n", errors));
        }

        return true;
    }

}