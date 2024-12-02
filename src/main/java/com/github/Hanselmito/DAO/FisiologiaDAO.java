package com.github.Hanselmito.DAO;

import com.github.Hanselmito.Conection.SQLConection;
import com.github.Hanselmito.Entity.Fisiologia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FisiologiaDAO implements DAO<Fisiologia> {
    // Consultas SQL para las operaciones CRUD
    private final static String INSERT = "INSERT INTO fisiologia (imagen, puntosDebiles, corte, impacto, disparo, parteRompibles, idMonstruo) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE fisiologia SET imagen=?, puntosDebiles=?, corte=?, impacto=?, disparo=?, parteRompibles=?, idMonstruo=? WHERE idMonstruo=?";
    private final static String DELETE = "DELETE FROM fisiologia WHERE idMonstruo=?";
    private final static String FINDALL = "SELECT * FROM fisiologia";
    private final static String FINDBYID = "SELECT * FROM fisiologia WHERE idMonstruo=?";

    private Connection conn;

    // Constructor que inicializa la conexión a la base de datos
    public FisiologiaDAO() {
        conn = SQLConection.getConnection();
    }

    // Método para guardar una nueva fisiología en la base de datos
    @Override
    public Fisiologia save(Fisiologia entity) {
        Fisiologia result = entity;
        if (entity == null || entity.getId_monstruo().getId() == 0) return result;
        Fisiologia f = findById(entity.getId_monstruo().getId());
        if (f != null) {
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(INSERT)) {
                pst.setBytes(1, entity.getImagen());
                pst.setString(2, entity.getPuntos_debiles());
                pst.setInt(3, entity.getCorte());
                pst.setInt(4, entity.getImpacto());
                pst.setInt(5, entity.getDisparo());
                pst.setString(6, entity.getPartes_rompibles());
                pst.setInt(7, entity.getId_monstruo().getId());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Método para actualizar una fisiología existente en la base de datos
    @Override
    public Fisiologia update(Fisiologia entity) {
        Fisiologia result = entity;
        if (entity == null || entity.getId_monstruo().getId() == 0) return result;
        Fisiologia f = findById(entity.getId_monstruo().getId());
        if (f != null) {
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(UPDATE)) {
                pst.setBytes(1, entity.getImagen());
                pst.setString(2, entity.getPuntos_debiles());
                pst.setInt(3, entity.getCorte());
                pst.setInt(4, entity.getImpacto());
                pst.setInt(5, entity.getDisparo());
                pst.setString(6, entity.getPartes_rompibles());
                pst.setInt(7, entity.getId_monstruo().getId());
                pst.setInt(8, entity.getId_monstruo().getId());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Método para eliminar una fisiología de la base de datos
    @Override
    public Fisiologia delete(Fisiologia entity) throws SQLException {
        if (entity == null || entity.getId_monstruo().getId() == 0) return entity;
        try (PreparedStatement pst = conn.prepareStatement(DELETE)) {
            pst.setInt(1, entity.getId_monstruo().getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    // Método para encontrar una fisiología por su ID
    @Override
    public Fisiologia findById(int key) {
        Fisiologia result = new Fisiologia();
        try (PreparedStatement pst = conn.prepareStatement(FINDBYID)) {
            pst.setInt(1, key);
            ResultSet res = pst.executeQuery();
            if (res.next()) {
                result.setImagen(res.getBytes("imagen"));
                result.setPuntos_debiles(res.getString("puntosDebiles"));
                result.setCorte(res.getInt("corte"));
                result.setImpacto(res.getInt("impacto"));
                result.setDisparo(res.getInt("disparo"));
                result.setPartes_rompibles(res.getString("parteRompibles"));
                result.setId_monstruo(MonstruosDAO.build().findById(res.getInt("idMonstruo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Método para encontrar todas las fisiologías
    public List<Fisiologia> findByAll() {
        List<Fisiologia> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)) {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Fisiologia f = new Fisiologia();
                f.setImagen(res.getBytes("imagen"));
                f.setPuntos_debiles(res.getString("puntosDebiles"));
                f.setCorte(res.getInt("corte"));
                f.setImpacto(res.getInt("impacto"));
                f.setDisparo(res.getInt("disparo"));
                f.setPartes_rompibles(res.getString("parteRompibles"));
                f.setId_monstruo(MonstruosDAO.build().findById(res.getInt("idMonstruo")));
                result.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Método para cerrar la conexión (actualmente vacío)
    @Override
    public void close() {
    }
}