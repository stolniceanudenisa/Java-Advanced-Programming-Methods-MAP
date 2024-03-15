package com.example.guiex1.domain;

public class UtilizatorValidator implements Validator<Utilizator> {
    @Override
    public void validate(Utilizator entity) throws ValidationException {
        //TODO: implement method validate
        if(entity.getFirst_name() == null || entity.getLast_name() == null)
            throw new ValidationException("Names cannot be null");
    }
}
