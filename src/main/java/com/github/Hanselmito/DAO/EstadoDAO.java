package com.github.Hanselmito.DAO;

import com.github.Hanselmito.Conection.SQLConection;
import com.github.Hanselmito.Entity.Debilidades;
import com.github.Hanselmito.Entity.Enums.*;
import com.github.Hanselmito.Entity.Estado;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadoDAO implements DAO<Estado>{
    private final static String INSERT = "INSERT INTO estado (estadoVeneno, estadoSueno, estadoParalisis, estadoNitro, estadoAturdimiento, efectividadVeneno, efectividadSueno, efectividadParalisis, efectividadNitro, efectividadAturdimiento, idMonstruo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE estado SET estadoVeneno=?, estadoSueno=?, estadoParalisis=?, estadoNitro=?, estadoAturdimiento=?, efectividadVeneno=?, efectividadSueno=?, efectividadParalisis=?, efectividadNitro=?, efectividadAturdimiento=?, idMonstruo=? WHERE idMonstruo=?";
    private final static String DELETE = "DELETE FROM estado WHERE idMonstruo=?";
    private final static String FINDALL = "SELECT * FROM estado";
    private final static String FINDBYID = "SELECT * FROM estado WHERE idMonstruo=?";

    private Connection conn;
    public EstadoDAO(){
        conn = SQLConection.getConnection();
    }

    @Override
    public Estado save(Estado entity) {
        Estado result = entity;
        if (entity==null || entity.getId_monstruo().getId()==0) return result;
        Estado es = findById(entity.getId_monstruo().getId());
        if (es!=null) {
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(INSERT)) {
                pst.setString(1, entity.getEstadoVeneno().getPartOfAlteraEstado());
                pst.setString(2, entity.getEstadoSueno().getPartOfAlteraEstado());
                pst.setString(3, entity.getEstadoParalisis().getPartOfAlteraEstado());
                pst.setString(4, entity.getEstadoNitro().getPartOfAlteraEstado());
                pst.setString(5, entity.getEstadoAturdimiento().getPartOfAlteraEstado());
                pst.setInt(6, entity.getEfectividadVeneno());
                pst.setInt(7, entity.getEfectividadSueno());
                pst.setInt(8, entity.getEfectividadParalisis());
                pst.setInt(9, entity.getEfectividadNitro());
                pst.setInt(10, entity.getEfectividadAturdimiento());
                pst.setInt(11, entity.getId_monstruo().getId());
                pst.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Estado update(Estado entity) {
        Estado result = entity;
        if (entity==null || entity.getId_monstruo().getId()==0) return result;
        Estado es = findById(entity.getId_monstruo().getId());
        if(es!=null) {
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(UPDATE)) {
                pst.setString(1, entity.getEstadoVeneno().getPartOfAlteraEstado());
                pst.setString(2, entity.getEstadoSueno().getPartOfAlteraEstado());
                pst.setString(3, entity.getEstadoParalisis().getPartOfAlteraEstado());
                pst.setString(4, entity.getEstadoNitro().getPartOfAlteraEstado());
                pst.setString(5, entity.getEstadoAturdimiento().getPartOfAlteraEstado());
                pst.setInt(6, entity.getEfectividadVeneno());
                pst.setInt(7, entity.getEfectividadSueno());
                pst.setInt(8, entity.getEfectividadParalisis());
                pst.setInt(9, entity.getEfectividadNitro());
                pst.setInt(10, entity.getEfectividadAturdimiento());
                pst.setInt(11, entity.getId_monstruo().getId());
                pst.setInt(12, entity.getId_monstruo().getId());
                pst.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Estado delete(Estado entity) throws SQLException {
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
    public Estado findById(int key) {
        Estado result = new Estado();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYID)){
            pst.setInt(1,key);
            ResultSet res = pst.executeQuery();
            if(res.next()){
                result.setEstadoVeneno(EstadoVeneno.valueOf(res.getString("estadoVeneno")));
                result.setEstadoSueno(EstadoSueno.valueOf(res.getString("estadoSueno")));
                result.setEstadoParalisis(EstadoParalisis.valueOf(res.getString("estadoParalisis")));
                result.setEstadoNitro(EstadoNitro.valueOf(res.getString("estadoNitro")));
                result.setEstadoAturdimiento(EstadoAturdimiento.valueOf(res.getString("estadoAturdimiento")));
                result.setEfectividadVeneno(res.getInt("efectividadVeneno"));
                result.setEfectividadSueno(res.getInt("efectividadSueno"));
                result.setEfectividadParalisis(res.getInt("efectividadParalisis"));
                result.setEfectividadNitro(res.getInt("efectividadNitro"));
                result.setEfectividadAturdimiento(res.getInt("efectividadAturdimiento"));
                result.setId_monstruo(new MonstruosDAO().findById(res.getInt("idMonstruo")));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Estado> findByAll() {
        List<Estado> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)) {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Estado es = new Estado();
                es.setEstadoVeneno(EstadoVeneno.valueOf(res.getString("estadoVeneno")));
                es.setEstadoSueno(EstadoSueno.valueOf(res.getString("estadoSueno")));
                es.setEstadoParalisis(EstadoParalisis.valueOf(res.getString("estadoParalisis")));
                es.setEstadoNitro(EstadoNitro.valueOf(res.getString("estadoNitro")));
                es.setEstadoAturdimiento(EstadoAturdimiento.valueOf(res.getString("estadoAturdimiento")));
                es.setEfectividadVeneno(res.getInt("efectividadVeneno"));
                es.setEfectividadSueno(res.getInt("efectividadSueno"));
                es.setEfectividadParalisis(res.getInt("efectividadParalisis"));
                es.setEfectividadNitro(res.getInt("efectividadNitro"));
                es.setEfectividadAturdimiento(res.getInt("efectividadAturdimiento"));
                es.setId_monstruo(new MonstruosDAO().findById(res.getInt("idMonstruo")));
                result.add(es);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }
}
