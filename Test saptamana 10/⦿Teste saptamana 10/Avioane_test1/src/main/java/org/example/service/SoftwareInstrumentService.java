package org.example.service;

import org.example.domain.SoftwareInstrument;
import org.example.exceptions.DuplicateEntityException;
import org.example.repository.FileRepository;
import org.example.repository.IRepository;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SoftwareInstrumentService {

    IRepository<SoftwareInstrument> softwareInstrumentRepository;

    public SoftwareInstrumentService(IRepository<SoftwareInstrument> softwareInstrumentRepository) {
        this.softwareInstrumentRepository = softwareInstrumentRepository;
    }

    public void addSoftwareInstrument(String code, boolean maintenance, int versiuneSoftware) throws DuplicateEntityException {
        SoftwareInstrument softwareInstrument = new SoftwareInstrument(code, maintenance, versiuneSoftware);
        this.softwareInstrumentRepository.add(softwareInstrument);

//        if (this.softwareInstrumentRepository instanceof FileRepository) {
//            ((FileRepository<SoftwareInstrument>) this.softwareInstrumentRepository).saveToFile();
//        }
//        if (this.pacientRepository instanceof BinaryFileRepository<Pacient>) {
//            ((BinaryFileRepository<Pacient>) this.pacientRepository).saveToFile();
//        }
    }

    public void addSoftwareInstrumentMentenanta(String code, boolean maintenance, int versiuneSoftware) throws DuplicateEntityException {
        SoftwareInstrument softwareInstrument = new SoftwareInstrument(code, maintenance, versiuneSoftware);

        if (maintenance) { // Verificați dacă instrumentul are mentenanță
            this.softwareInstrumentRepository.add(softwareInstrument);

            if (this.softwareInstrumentRepository instanceof FileRepository) {
                //Collection<SoftwareInstrument> softwareInstruments = this.softwareInstrumentRepository.getAll();
                List<SoftwareInstrument> softwareList = new ArrayList<>( this.softwareInstrumentRepository.getAll());

                // Filtrare pentru instrumente cu mentenanță și sortare crescătoare după preț
                List<SoftwareInstrument> maintenanceSoftwareList = softwareList.stream()
                        .filter(SoftwareInstrument::isMaintenance)
                        .sorted(Comparator.comparingDouble(SoftwareInstrument::getPrice))
                        .collect(Collectors.toList());

                // Salvare în repository-ul de fișiere
                ((FileRepository<SoftwareInstrument>) this.softwareInstrumentRepository).overwriteAll(maintenanceSoftwareList);
            }
        }
    }


    public SoftwareInstrument findByCode(String code) {
        return softwareInstrumentRepository.findByCode(code);
    }

    public Collection<SoftwareInstrument> getAll() {
        return softwareInstrumentRepository.getAll();
    }


}