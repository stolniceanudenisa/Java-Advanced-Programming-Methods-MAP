package org.example.repository;

import org.example.domain.Album;
import org.example.domain.validator.Validator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository implements Repository<Long, Album>{

    private Connection connection;
    private Validator<Album> validator;

    public AlbumRepository(Connection connection, Validator<Album> validator) {
        this.connection = connection;
        this.validator = validator;
    }


    @Override
    public Album findOne(Long id) {
        Album album = null;
        try{
            PreparedStatement st = connection.prepareStatement("SELECT * from albums where id=?");
            st.setLong(1,id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                album = new Album();
                album.setId(rs.getLong("id"));
                album.setNume(rs.getString("nume"));
                album.setArtist(rs.getString("artist"));
                album.setGen(rs.getString("gen"));
                album.setPret(rs.getDouble("pret"));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return album;
    }

    @Override
    public List<Album> findAll() {
        List<Album> albums = new ArrayList<>();
        try{
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery("select * from albums");
            while(rs.next()){
                Album album = new Album();
                album.setId(rs.getLong("id"));
                album.setNume(rs.getString("nume"));
                album.setArtist(rs.getString("artist"));
                album.setGen(rs.getString("gen"));
                album.setPret(rs.getDouble("pret"));
                albums.add(album);
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
        return albums;
    }

    @Override
    public Album save(Album entity) {
        Album album = entity;
        validator.validate(album);
        try{
            String sql = "INSERT INTO albums(nume, artist, gen, pret) VALUES(?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,album.getNume());
            st.setString(2,album.getArtist());
            st.setString(3,album.getGen());
            st.setDouble(4,album.getPret());
            st.executeUpdate();
            return entity;
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return album;
    }

    @Override
    public Album delete(Long id) {
        Album album = findOne(id);
        if (album!=null){
            try{
                String sql = "DELETE FROM albums where id=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setLong(1,id);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.getStackTrace();
            }
        }
        return album;
    }

    @Override
    public Album update(Album entity) {
        Album album = entity;
        validator.validate(album);
        try{
            String sql =" UPDATE albums SET nume=?, artist = ?, gen=?, pret=? where id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1,album.getNume());
            st.setString(2,album.getArtist());
            st.setString(3,album.getGen());
            st.setDouble(4,album.getPret());
            st.executeUpdate();
            return entity;
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return album;
    }
}
