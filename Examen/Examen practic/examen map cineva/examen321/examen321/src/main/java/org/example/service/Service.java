package org.example.service;

import org.example.domain.Album;
import org.example.repository.AlbumRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
public class Service {
    private AlbumRepository albumRepository;
    private Connection connection;

    List<Album> albums;

    public Service(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/examen", "postgres", "armadilo12");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        albums = new ArrayList<>(albumRepository.findAll());
    }

    public Album add(Album album){
      return this.albumRepository.save(album);

    }

    public Album delete(Long id)
    {
        return this.albumRepository.delete(id);
    }
    public Album update(Album album)
    {
        return this.albumRepository.update(album);
    }

    public Album findOneAlbum(Long id)
    {
        return albumRepository.findOne(id);
    }

    public List<Album> findAllAlbums(){
        return albumRepository.findAll();
    }
    public Album searchThriller(){
        Album finded = albums.stream().filter(b->b.getNume().equals("Thriller")).findFirst().orElse(null);
        return finded;
    }

    public List<Album> filterRock(){
        List<Album> albums2 =albums.stream().filter(b->b.getGen().equals("rock")).collect(Collectors.toList());
        return albums2;
    }
    public List<String> filterRock2(){
        return albums.stream()
                .filter(this::isRock)
                .map(b -> b.getNume())
                .collect(Collectors.toList());
    }

    private boolean isRock(Album album){
        return album.getGen().equals("rock");
    }
    public List<Album> filterRockAndPrice(){
        List<Album> albums2 =albums.stream().filter(b->b.getGen().equals("rock") && b.getPret()>40).collect(Collectors.toList());
        return albums2;
    }

    public List<Album> sortByArtistAndName(){
        return albums.stream().sorted(Comparator.comparing(Album::getArtist).thenComparing(Album::getNume)).collect(Collectors.toList());
    }

    public List<Album> sortByGenre(){
        return albums.stream().sorted(Comparator.comparing(Album::getGen).reversed()).collect(Collectors.toList());
    }
    public List<Album> sortByPrice(){
        return albums.stream().sorted(Comparator.comparing(Album::getPret)).collect(Collectors.toList());
    }



}
