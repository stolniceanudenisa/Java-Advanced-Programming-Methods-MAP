package Domain.validator.pacient;

import Domain.Pacient;

import java.util.ArrayList;
import java.util.List;

public class PacientValidator implements Validator<Pacient> {
    @Override
    public boolean validate(Pacient entity) throws ValidationException {

        List<String> errors = new ArrayList<>();
        if (entity.getNume().isEmpty())
            errors.add("Numele nu poate fi null!");

        if (entity.getPrenume().isEmpty())
            errors.add("Prenumele nu poate fi null!");

        if (entity.getVarsta() <= 0)
            errors.add("Varsta nu poate fi negativa!");

        if (entity.getVarsta() == 0)
            errors.add("Varsta nu poate fi nula!");

        if (!errors.isEmpty()) {
            throw new ValidationException(String.join("\n", errors));
        }

        return true;
    }
}