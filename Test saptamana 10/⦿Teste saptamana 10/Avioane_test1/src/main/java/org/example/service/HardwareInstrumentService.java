package org.example.service;

import org.example.domain.HardwareInstrument;
import org.example.exceptions.DuplicateEntityException;
import org.example.repository.FileRepository;
import org.example.repository.IRepository;

import java.util.Collection;

public class HardwareInstrumentService {
    IRepository<HardwareInstrument> hardwareInstrumentRepository;

    public HardwareInstrumentService(IRepository<HardwareInstrument> hardwareInstrumentRepository) {
        this.hardwareInstrumentRepository = hardwareInstrumentRepository;
    }

    public void addHardwareInstrument(String code, boolean maintenance, String tip_de_masuratoare) throws DuplicateEntityException {
        HardwareInstrument hardwareInstrument = new HardwareInstrument(code, maintenance, tip_de_masuratoare);
        this.hardwareInstrumentRepository.add(hardwareInstrument);

        if (this.hardwareInstrumentRepository instanceof FileRepository) {
            ((FileRepository<HardwareInstrument>) this.hardwareInstrumentRepository).saveToFile();
        }
        //        if (this.pacientRepository instanceof BinaryFileRepository<Pacient>) {
//            ((BinaryFileRepository<Pacient>) this.pacientRepository).saveToFile();
//        }

    }



    public HardwareInstrument findByCode(String code) {
        return hardwareInstrumentRepository.findByCode(code);
    }

    public Collection<HardwareInstrument> getAll() {
        return hardwareInstrumentRepository.getAll();
    }

}