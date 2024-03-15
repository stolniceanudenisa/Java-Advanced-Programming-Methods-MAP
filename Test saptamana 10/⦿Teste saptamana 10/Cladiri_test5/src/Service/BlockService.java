package Service;

import Domain.Block;
import Repo.IRepository;
import exceptions.DuplicateEntityException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BlockService {
    IRepository<Block> blockRepository;

    public BlockService(IRepository<Block> blockRepository) {
        this.blockRepository = blockRepository;
    }

    public void add(Integer constructionYear, Integer totalApartments, Integer occupiedApartments) throws DuplicateEntityException, DuplicateEntityException {
        Block block = new Block(constructionYear, totalApartments, occupiedApartments);
        block.setId(blockRepository.getAll().size() + 1);
        blockRepository.add(block);
    }

    public Block findByConstructionYear(Integer constructionYear) {
        return blockRepository.findByConstructionYear(constructionYear);
    }

    public Iterable<Block> getAll() {
        return blockRepository.getAll();
    }

    public Block[] afisBlocuriRestaurate() {
        //un bloc trebuie restaurat daca are peste 40 de ani si daca procentul apartamentelor ocupate este mai mare de 80%
        List<Block> blocuri = (List<Block>) blockRepository.getAll();
        List<Block> blocuri_final =  blocuri
                .stream()
                .filter(bloc -> bloc.getConstructionYear() > 40 &&  calculateOccupancyPercentage(bloc) > 80)
                 //filter crescator dupa anul constructiei
                //.sorted((bloc1, bloc2) -> bloc1.getConstructionYear().compareTo(bloc2.getConstructionYear()))
                .sorted(Comparator.comparing(Block::getConstructionYear))
                .collect(Collectors.toList());

        return blocuri_final.toArray(new Block[blocuri_final.size()]);
    }

    private double calculateOccupancyPercentage(Block bloc) {
        return ((double) bloc.getOccupiedApartments() / bloc.getTotalApartments()) * 100;
    }

    public Block[] saveToTextDemolate() {
        //un block poate fi demolat daca procentul de apartamente ocupate e < 5%
        List<Block> blocuri = (List<Block>) blockRepository.getAll();
        List<Block> blocuri_final =  blocuri
                .stream()
                .filter(bloc -> calculateOccupancyPercentage(bloc) < 5)
                .sorted(Comparator.comparing(Block::getConstructionYear))
                .collect(Collectors.toList());
        return blocuri_final.toArray(new Block[blocuri_final.size()]);
    }

    public Block[] getBlocks() {
        List<Block> blocuri = (List<Block>) blockRepository.getAll();
        List<Block> blocuri_final =  blocuri
                .stream()
                .collect(Collectors.toList());
        return blocuri_final.toArray(new Block[blocuri_final.size()]);
    }

    public void remove(Integer id) {
        blockRepository.remove(id);
    }

}