package Service;

import Domain.Manuscript;
import Repo.IRepository;
import exceptions.DuplicateEntityException;

import java.util.List;
import java.util.stream.Collectors;

public class ManuscriptService {
    IRepository<Manuscript> manuscriptRepository;

    public ManuscriptService(IRepository<Manuscript> manuscriptRepository) {
        this.manuscriptRepository = manuscriptRepository;
    }

    public void addManuscript(String author, int nr_words, int nr_pages) throws DuplicateEntityException {
        Manuscript manuscript = new Manuscript(author,nr_words, nr_pages);
        manuscriptRepository.add(manuscript);
    }

    public Manuscript findByAuthor(String author) {
        return manuscriptRepository.findByAuthor(author);
    }

    public Iterable<Manuscript> getAll() {
        return manuscriptRepository.getAll();
    }

    public void isNeconform() {
        List<Manuscript> manuscripts = (List<Manuscript>) this.getAll();
        manuscripts.stream().filter(p1 -> !p1.isConformant())
                .sorted((p1, p2) -> (p1.getAuthor().compareTo(p2.getAuthor())))
                .forEach(a -> System.out.println(a.toString()));
                // .forEach(System.out::println);

        System.out.println();
    }

    public Manuscript[] saveToText() {
        List<Manuscript> entitati = (List<Manuscript>) manuscriptRepository.getAll();
        List<Manuscript> entitatiFiltrateSiSortate = entitati.stream()
                .collect(Collectors.toList());
        return entitatiFiltrateSiSortate.toArray(new Manuscript[entitatiFiltrateSiSortate.size()]);
    }


}