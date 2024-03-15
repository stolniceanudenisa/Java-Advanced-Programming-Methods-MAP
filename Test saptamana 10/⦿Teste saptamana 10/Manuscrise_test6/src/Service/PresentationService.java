package Service;

import Domain.Document;
import Domain.Manuscript;
import Domain.Presentation;
import Repo.IRepository;
import exceptions.DuplicateEntityException;

import java.util.Comparator;
import java.util.List;

public class PresentationService {
    IRepository<Presentation> presentationRepository;

    public PresentationService(IRepository<Presentation> presentationRepository) {
        this.presentationRepository = presentationRepository;
    }

    public void addPresentation(String author, int nr_slides, String text) throws DuplicateEntityException {
        Presentation presentation = new Presentation(author,nr_slides, text);
        presentationRepository.add(presentation);
    }

    public Presentation findByAuthor(String author) {
        return presentationRepository.findByAuthor(author);
    }

    public Iterable<Presentation> getAll() {
        return presentationRepository.getAll();
    }

    public void isNeconform() {
      List<Presentation> presentations = (List<Presentation>) this.getAll();
        presentations.stream().filter(p1 -> !p1.isConformant())
                .sorted(Comparator.comparing(Document::getAuthor))
                .forEach(a -> System.out.println(a.toString()));
                // .forEach(System.out::println);

        System.out.println();
    }

    public Presentation[] saveToText() {
        List<Presentation> entitati = (List<Presentation>) presentationRepository.getAll();
        List<Presentation> entitatiFiltrateSiSortate = entitati.stream()
                .collect(java.util.stream.Collectors.toList());
        return entitatiFiltrateSiSortate.toArray(new Presentation[entitatiFiltrateSiSortate.size()]);
    }
}