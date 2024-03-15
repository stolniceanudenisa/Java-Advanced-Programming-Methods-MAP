package Service;

import Domain.SoftwareInstrument;
import Repo.IRepository;
import exceptions.DuplicateEntityException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SoftwareService {
    IRepository<SoftwareInstrument> softwareRepository;

    public SoftwareService(IRepository<SoftwareInstrument> softwareRepository) {
        this.softwareRepository = softwareRepository;
    }

    public void add(String code, boolean maintenance, int version) throws DuplicateEntityException {
        SoftwareInstrument softwareInstrument = new SoftwareInstrument(code, maintenance, version);
        softwareRepository.add(softwareInstrument);
    }

    public SoftwareInstrument findByCode(String code) {
        return softwareRepository.findByCode(code);
    }

    public Iterable<SoftwareInstrument> getAll() {
        return softwareRepository.getAll();
    }

    public SoftwareInstrument[] getSoftwareInstrumentsCheaperThan(double pret) {
        List<SoftwareInstrument> softwareInstruments = (List<SoftwareInstrument>) softwareRepository.getAll();
        List<SoftwareInstrument> softwareInstrumentsCheaperThan = softwareInstruments
                .stream()
                .filter(softwareInstrument -> softwareInstrument.getPrice() < pret)
                .sorted(Comparator.comparing(SoftwareInstrument::getCode))
                .collect(Collectors.toList());

        return softwareInstrumentsCheaperThan.toArray(new SoftwareInstrument[softwareInstrumentsCheaperThan.size()]);
    }

    public List<SoftwareInstrument> getAllSoftware() throws DuplicateEntityException {
        softwareRepository.add(new SoftwareInstrument("226", true, 8));
        softwareRepository.add(new SoftwareInstrument("220", false, 5));
        softwareRepository.add(new SoftwareInstrument("223", true, 13));
        softwareRepository.add(new SoftwareInstrument("222", false, 54));

        return (List<SoftwareInstrument>) softwareRepository.getAll();
    }

    public void remove(String code) {
        softwareRepository.remove(code);
    }

    public SoftwareInstrument[] getAllSoftware2() {
        List<SoftwareInstrument> softwareInstruments = (List<SoftwareInstrument>) softwareRepository.getAll();
        List<SoftwareInstrument> softwareInstrumentsMaintenance = softwareInstruments
                .stream()
                .sorted(Comparator.comparing(SoftwareInstrument::getPrice))
                .collect(Collectors.toList());

        return softwareInstrumentsMaintenance.toArray(new SoftwareInstrument[softwareInstrumentsMaintenance.size()]);
    }
}