package org.example.domain.validator;


import org.example.domain.Album;

public class AlbumValidator implements Validator<Album> {
    @Override
    public void validate(Album entity) throws ValidationException {
        if(entity.getNume() == null)
            throw new ValidationException("Titlul nu poate fi null");
        if(entity.getArtist() == null)
            throw new ValidationException("Autorul nu poate fi null");
        if(entity.getGen() == null)
            throw new ValidationException("Genul nu poate fi null");
        if(entity.getPret() <=0)
            throw new ValidationException("Pretul nu poate fi null sau negativ");
    }
}
