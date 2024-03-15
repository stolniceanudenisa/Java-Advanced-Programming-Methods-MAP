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

    public List<HardwareInstrument> getAllHardware() throws DuplicateEntityException {

        hardwareRepository.add((new HardwareInstrument("116", true, "altitudine")));
        hardwareRepository.add((new HardwareInstrument("112", false, "directie")));
        hardwareRepository.add((new HardwareInstrument("115", true, "stare_motor")));
        hardwareRepository.add((new HardwareInstrument("113", false, "directie")));

        return (List<HardwareInstrument>) hardwareRepository.getAll();
    }

    public HardwareInstrument[] getHardwareInstrumentsCheaperThan(double pret) {
        List<HardwareInstrument> hardwareInstruments = (List<HardwareInstrument>) hardwareRepository.getAll();
        List<HardwareInstrument> hardwareInstrumentsCheaperThan = hardwareInstruments
                .stream()
                .filter(hardwareInstrument -> hardwareInstrument.getPrice() < pret)
                .sorted(Comparator.comparing(HardwareInstrument::getCode)) //ord cresc dupa cod
                .collect(Collectors.toList());

        return hardwareInstrumentsCheaperThan.toArray(new HardwareInstrument[hardwareInstrumentsCheaperThan.size()]);
    }

    public void remove(String code) {
        hardwareRepository.remove(code);
    }

}