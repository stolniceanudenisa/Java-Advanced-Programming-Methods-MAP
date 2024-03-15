package Service;

import Domain.HardwareInstrument;
import Repo.IRepository;
import exceptions.DuplicateEntityException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HardwareService {
    IRepository<HardwareInstrument> hardwareRepository;

    public HardwareService(IRepository<HardwareInstrument> hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    public void add(String code, boolean maintenance, String measurement_type) throws DuplicateEntityException {
        HardwareInstrument hardwareInstrument = new HardwareInstrument(code, maintenance, measurement_type);
        hardwareRepository.add(hardwareInstrument);
    }

    public HardwareInstrument findByCode(String code) {
        return hardwareRepository.findByCode(code);
    }

    public Iterable<HardwareInstrument> getAll() {
        return hardwareRepository.getAll();
    }

    public HardwareInstrument[] getHardwareInstrumentsCheaperThan(double pret) {
        List<HardwareInstrument> hardwareInstruments = (List<HardwareInstrument>) hardwareRepository.getAll();
        List<HardwareInstrument> hardwareInstrumentsCheaperThan = hardwareInstruments
                .stream()
                .filter(hardwareInstrument -> hardwareInstrument.getPrice() < pret)
                .sorted(Comparator.comparing(HardwareInstrument::getCode))
                .collect(Collectors.toList());

        return hardwareInstrumentsCheaperThan.toArray(new HardwareInstrument[hardwareInstrumentsCheaperThan.size()]);
    }

    public HardwareInstrument[] getHardwareMaintenance() {
        List<HardwareInstrument> hardwareInstruments = (List<HardwareInstrument>) hardwareRepository.getAll();
        List<HardwareInstrument> hardwareInstrumentsMaintenance = hardwareInstruments
                .stream()
                .filter(hardwareInstrument -> hardwareInstrument.isMaintenance())
                .sorted(Comparator.comparing(HardwareInstrument::getCode))
                .collect(Collectors.toList());

        return hardwareInstrumentsMaintenance.toArray(new HardwareInstrument[hardwareInstrumentsMaintenance.size()]);

    }
}