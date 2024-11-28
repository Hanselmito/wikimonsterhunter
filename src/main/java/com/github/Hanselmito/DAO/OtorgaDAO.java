package com.github.Hanselmito.DAO;

import com.github.Hanselmito.Conection.SQLConection;
import com.github.Hanselmito.Entity.Otorga;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OtorgaDAO implements DAO<Otorga>{
    private final static String INSERT = "INSERT INTO otorga (idEquipo, idAbilidades) VALUES (?, ?)";
    private final static String UPDATE = "UPDATE otorga SET idEquipo=?, idAbilidades=? WHERE idEquipo=? AND idAbilidades=?";
    private final static String DELETE = "DELETE FROM otorga WHERE idEquipo=? AND idAbilidades=?";
    private final static String FINDBYID = "SELECT * FROM otorga WHERE idEquipo=? AND idAbilidades=?";

    private Connection conn;
    public OtorgaDAO() {
        conn = SQLConection.getConnection();
    }

    @Override
    public Otorga save(Otorga entity) {
        Otorga result = entity;
        if (entity == null || entity.getId_Equipo().getId() == 0 || entity.getId_Abilidades().getId() == 0) return result;
        Otorga ot = findById(entity.getId_Equipo().getId());
        Otorga ot2 = findById(entity.getId_Abilidades().getId());
        if (ot == null || ot2 == null){
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(INSERT)) {
                pst.setInt(1, entity.getId_Equipo().getId());
                pst.setInt(2, entity.getId_Abilidades().getId());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Otorga update(Otorga entity) {
        Otorga result = entity;
        if (entity == null || entity.getId_Equipo().getId() == 0 || entity.getId_Abilidades().getId() == 0) return result;
        try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(UPDATE)) {
            pst.setInt(1, entity.getId_Equipo().getId());
            pst.setInt(2, entity.getId_Abilidades().getId());
            pst.setInt(3, entity.getId_Equipo().getId());
            pst.setInt(4, entity.getId_Abilidades().getId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Otorga delete(Otorga entity) throws SQLException {
        if (entity == null || entity.getId_Equipo().getId() == 0 || entity.getId_Abilidades().getId() == 0) return entity;
        try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(DELETE)) {
            pst.setInt(1, entity.getId_Equipo().getId());
            pst.setInt(2, entity.getId_Abilidades().getId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Otorga findById(int key) {
        Otorga result = new Otorga();
        try (PreparedStatement pst = conn.prepareStatement(FINDBYID)) {
            pst.setInt(1, key);
            pst.setInt(2, key);
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    result.setId_Equipo(new EquipoDAO().findById(res.getInt("idEquipo")));
                    result.setId_Abilidades(new AbilidadesDAO().findById(res.getInt("idAbilidades")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }
}
