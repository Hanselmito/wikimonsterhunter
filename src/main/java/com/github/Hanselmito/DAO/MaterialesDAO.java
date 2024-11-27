package com.github.Hanselmito.DAO;

import com.github.Hanselmito.Conection.SQLConection;
import com.github.Hanselmito.Entity.Materiales;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialesDAO implements DAO<Materiales>{
    private final static String INSERT = "INSERT INTO materiales (imagen, nombre, dropRate, mediante, cantidad, idMonstruo) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE materiales SET imagen=?, nombre=?, dropRate=?, mediante=?, cantidad=?, idMonstruo=? WHERE id=?";
    private final static String DELETE = "DELETE FROM materiales WHERE id=?";
    private final static String FINDALL = "SELECT * FROM materiales";
    private final static String FINDBYID = "SELECT * FROM materiales WHERE id=?";

    private Connection conn;
    public MaterialesDAO(){
        conn = SQLConection.getConnection();
    }

    @Override
    public Materiales save(Materiales entity) {
        Materiales result = entity;
        if (entity==null || entity.getId_monstruo().getId()==0) return result;
        Materiales m = findById(entity.getId_monstruo().getId());
        if(m!=null) {
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(INSERT)) {
                pst.setBytes(1, entity.getImagen());
                pst.setString(2, entity.getNombre());
                pst.setString(3, entity.getDropRate());
                pst.setString(4, entity.getMediante());
                pst.setInt(5, entity.getCantidad());
                pst.setInt(6, entity.getId_monstruo().getId());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Materiales update(Materiales entity) {
        Materiales result = entity;
        if (entity==null || entity.getId_monstruo().getId()==0) return result;
        Materiales m = findById(entity.getId_monstruo().getId());
        if(m!=null) {
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(UPDATE)) {
                pst.setBytes(1, entity.getImagen());
                pst.setString(2, entity.getNombre());
                pst.setString(3, entity.getDropRate());
                pst.setString(4, entity.getMediante());
                pst.setInt(5, entity.getCantidad());
                pst.setInt(6, entity.getId_monstruo().getId());
                pst.setInt(7, entity.getId());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Materiales delete(Materiales entity) throws SQLException {
        if(entity == null || entity.getId()==0) return entity;
        try (PreparedStatement pst = conn.prepareStatement(DELETE)){
            pst.setInt(1,entity.getId());
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Materiales findById(int key) {
        Materiales result = new Materiales();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYID)) {
            pst.setString(1, String.valueOf(key));
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result.setId(res.getInt("id"));
                    result.setImagen(res.getBytes("imagen"));
                    result.setNombre(res.getString("nombre"));
                    result.setDropRate(res.getString("dropRate"));
                    result.setMediante(res.getString("mediante"));
                    result.setCantidad(res.getInt("cantidad"));
                    result.setId_monstruo(new MonstruosDAO().findById(res.getInt("idMonstruo")));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Materiales> findAll(){
        List<Materiales> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)) {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Materiales m = new Materiales();
                m.setId(res.getInt("id"));
                m.setImagen(res.getBytes("imagen"));
                m.setNombre(res.getString("nombre"));
                m.setDropRate(res.getString("dropRate"));
                m.setMediante(res.getString("mediante"));
                m.setCantidad(res.getInt("cantidad"));
                m.setId_monstruo(new MonstruosDAO().findById(res.getInt("idMonstruo")));
                result.add(m);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }

    public static MaterialesDAO build(){
        return new MaterialesDAO();
    }
}
