package Service;

import Domain.HardwareInstrument;
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

    public SoftwareInstrument[] getSoftwareMaintenance() {
        List<SoftwareInstrument> softwareInstruments = (List<SoftwareInstrument>) softwareRepository.getAll();
        List<SoftwareInstrument> softwareInstrumentsMaintenance = softwareInstruments
                .stream()
                .filter(softwareInstrument -> softwareInstrument.isMaintenance())
                .sorted(Comparator.comparing(SoftwareInstrument::getCode))
                .collect(Collectors.toList());

        return softwareInstrumentsMaintenance.toArray(new SoftwareInstrument[softwareInstrumentsMaintenance.size()]);
    }
}