package Service;

import Domain.Block;
import Domain.House;
import Repo.IRepository;
import exceptions.DuplicateEntityException;

import java.util.Comparator;
import java.util.List;

public class HouseService {
    IRepository<House> houseRepository;

    public HouseService(IRepository<House> houseRepository) {
        this.houseRepository = houseRepository;
    }

    public void add(Integer constructionYear, boolean isHistorical) throws DuplicateEntityException {
        House house = new House(constructionYear, isHistorical);
        house.setId(houseRepository.getAll().size() + 1);
        houseRepository.add(house);
    }

    public House findByConstructionYear(Integer constructionYear) {
        return houseRepository.findByConstructionYear(constructionYear);
    }

    public Iterable<House> getAll() {
        return houseRepository.getAll();
    }

    public House[] afisCaseRestaurate() {
        //o casa trebuie restaurata daca are peste 100 de ani si e istorica
        List<House> casele = (List<House>) houseRepository.getAll();
        List<House> casele_final =  casele
                .stream()
                .filter(casa -> casa.getConstructionYear() > 100 && casa.isHistorical())
                .sorted(Comparator.comparing(House::getConstructionYear))
                .collect(java.util.stream.Collectors.toList());

        return casele_final.toArray(new House[casele_final.size()]);
    }


    public House[] saveToTextDemolate() {
        // o casa poate fi demolata daca nu e istorica
        List<House> casele = (List<House>) houseRepository.getAll();
        List<House> casele_final =  casele
                .stream()
                .filter(casa -> !casa.isHistorical())
                .sorted(Comparator.comparing(House::getConstructionYear))
                .collect(java.util.stream.Collectors.toList());
        return casele_final.toArray(new House[casele_final.size()]);
    }

    public House[] getHouses() {
        List<House> casele = (List<House>) houseRepository.getAll();
        List<House> casele_final =  casele
                .stream()
                .collect(java.util.stream.Collectors.toList());
        return casele_final.toArray(new House[casele_final.size()]);
    }

    public void remove(Integer id) {
        houseRepository.remove(id);
    }
}