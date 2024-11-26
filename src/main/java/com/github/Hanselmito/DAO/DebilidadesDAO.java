package com.github.Hanselmito.DAO;

import com.github.Hanselmito.Conection.SQLConection;
import com.github.Hanselmito.Entity.Debilidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DebilidadesDAO implements DAO<Debilidades>{
    private final static String INSERT = "INSERT INTO debilidades (elementoFuego, elementoAgua, elementoRayo, elementoHielo, elementoDraco, efectividadFuego, efectividadAgua, efectividadRayo, efectividadHielo, efectividadDraco, idMonstruo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE debilidades SET elementoFuego=?, elementoAgua=?, elementoRayo=?, elementoHielo=?, elementoDraco=?, efectividadFuego=?, efectividadAgua=?, efectividadRayo=?, efectividadHielo=?, efectividadDraco=?, idMonstruo=? WHERE id=?";
    private final static String DELETE = "DELETE FROM debilidades WHERE idMonstruo=?";
    private final static String FINDALL = "SELECT * FROM debilidades";
    private final static String FINDBYID = "SELECT * FROM debilidades WHERE idMonstruo=?";

    private Connection conn;
    public DebilidadesDAO(){
        conn = SQLConection.getConnection();
    }

    @Override
    public Debilidades save(Debilidades entity) {
        Debilidades result = entity;
        if (entity == null || entity.getId_monstruo().getId() == 0) return result;
        Debilidades d = findById(entity.getId_monstruo().getId());
        if (d == null) {
            try (PreparedStatement pst = conn.prepareStatement(INSERT)) {
                pst.setString(1, entity.getElementoFuego().getPartOfDebilElementoFuego());
                pst.setString(2, entity.getElementoAgua().getPartOfDebilElementoAgua());
                pst.setString(3, entity.getElementoRayo().getPartOfDebilElementoRayo());
                pst.setString(4, entity.getElementoHielo().getPartOfDebilElementoHielo());
                pst.setString(5, entity.getElementoDraco().getPartOfDebilElementoDraco());
                pst.setInt(6, entity.getEfectividadFuego());
                pst.setInt(7, entity.getEfectividadAgua());
                pst.setInt(8, entity.getEfectividadRayo());
                pst.setInt(9, entity.getEfectividadHielo());
                pst.setInt(10, entity.getEfectividadDraco());
                pst.setInt(11, entity.getId_monstruo().getId());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Debilidades update(Debilidades entity) {
        Debilidades result = entity;
        if (entity==null || entity.getId_monstruo().getId()==0) return result;
        Debilidades d = findById(entity.getId_monstruo().getId());
        if(d!=null) {
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(UPDATE)) {
                pst.setString(1, entity.getElementoFuego().getPartOfDebilElementoFuego());
                pst.setString(2, entity.getElementoAgua().getPartOfDebilElementoAgua());
                pst.setString(3, entity.getElementoRayo().getPartOfDebilElementoRayo());
                pst.setString(4, entity.getElementoHielo().getPartOfDebilElementoHielo());
                pst.setString(5, entity.getElementoDraco().getPartOfDebilElementoDraco());
                pst.setInt(6, entity.getEfectividadFuego());
                pst.setInt(7, entity.getEfectividadAgua());
                pst.setInt(8, entity.getEfectividadRayo());
                pst.setInt(9, entity.getEfectividadHielo());
                pst.setInt(10, entity.getEfectividadDraco());
                pst.setInt(11, entity.getId_monstruo().getId());
                pst.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Debilidades delete(Debilidades entity){
        if(entity == null || entity.getId_monstruo().getId()==0) return entity;
        try (PreparedStatement pst = conn.prepareStatement(DELETE)){
            pst.setInt(1,entity.getId_monstruo().getId());
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Debilidades findById(int key) {
        Debilidades result = new Debilidades();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYID)){
            pst.setInt(1,key);
            ResultSet res = pst.executeQuery();
            if(res.next()){
                result.setElementoFuego(!res.getString("Fuego").equals("SinDebilidad"));
                result.setElementoAgua(!res.getString("Agua").equals("SinDebilidad"));
                result.setElementoRayo(!res.getString("Rayo").equals("SinDebilidad"));
                result.setElementoHielo(!res.getString("Hielo").equals("SinDebilidad"));
                result.setElementoDraco(!res.getString("Draco").equals("SinDebilidad"));
                result.setEfectividadFuego(res.getInt("efectividadFuego"));
                result.setEfectividadAgua(res.getInt("efectividadAgua"));
                result.setEfectividadRayo(res.getInt("efectividadRayo"));
                result.setEfectividadHielo(res.getInt("efectividadHielo"));
                result.setEfectividadDraco(res.getInt("efectividadDraco"));
                result.setId_monstruo(new MonstruosDAO().findById(res.getInt("idMonstruo")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Debilidades> findByAll() {
        List<Debilidades> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)) {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Debilidades f = new Debilidades();
                f.setElementoFuego(!res.getString("Fuego").equals("SinDebilidad"));
                f.setElementoAgua(!res.getString("Agua").equals("SinDebilidad"));
                f.setElementoRayo(!res.getString("Rayo").equals("SinDebilidad"));
                f.setElementoHielo(!res.getString("Hielo").equals("SinDebilidad"));
                f.setElementoDraco(!res.getString("Draco").equals("SinDebilidad"));
                f.setEfectividadFuego(res.getInt("efectividadFuego"));
                f.setEfectividadAgua(res.getInt("efectividadAgua"));
                f.setEfectividadRayo(res.getInt("efectividadRayo"));
                f.setEfectividadHielo(res.getInt("efectividadHielo"));
                f.setEfectividadDraco(res.getInt("efectividadDraco"));
                f.setId_monstruo(new MonstruosDAO().findById(res.getInt("idMonstruo")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close(){

    }
}
