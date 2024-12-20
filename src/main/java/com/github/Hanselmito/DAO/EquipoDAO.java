package com.github.Hanselmito.DAO;

import com.github.Hanselmito.Conection.SQLConection;
import com.github.Hanselmito.Entity.Enums.Tipo;
import com.github.Hanselmito.Entity.Equipo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO implements DAO<Equipo> {
    // Consultas SQL para las operaciones CRUD
    private final static String INSERT = "INSERT INTO equipo (nombre, tipo, habilidades, imagen, materiales, idMateriales) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE equipo SET nombre=?, tipo=?, habilidades=?, imagen=?, materiales=?, idMateriales=? WHERE id=?";
    private final static String DELETE = "DELETE FROM equipo WHERE id=?";
    private final static String FINDALL = "SELECT * FROM equipo";
    private final static String FINDBYID = "SELECT * FROM equipo WHERE id=?";

    private Connection conn;

    // Constructor que inicializa la conexión a la base de datos
    public EquipoDAO() {
        conn = SQLConection.getConnection();
    }

    // Método para guardar un nuevo equipo en la base de datos
    @Override
    public Equipo save(Equipo entity) {
        Equipo result = entity;
        if (entity == null || entity.getId_materiales().getId() == 0) return result;
        Equipo eq = findById(entity.getId_materiales().getId());
        if (eq != null) {
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(INSERT)) {
                pst.setString(1, entity.getNombre());
                pst.setString(2, entity.getTipo().getPartOfTipo());
                pst.setString(3, entity.getHabilidades());
                pst.setBytes(4, entity.getImagen());
                pst.setString(5, entity.getMateriales());
                pst.setInt(6, entity.getId_materiales().getId());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Método para actualizar un equipo existente en la base de datos
    @Override
    public Equipo update(Equipo entity) {
        Equipo result = entity;
        if (entity == null || entity.getId_materiales().getId() == 0) return result;
        Equipo eq = findById(entity.getId_materiales().getId());
        if (eq != null) {
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(UPDATE)) {
                pst.setString(1, entity.getNombre());
                pst.setString(2, entity.getTipo().getPartOfTipo());
                pst.setString(3, entity.getHabilidades());
                pst.setBytes(4, entity.getImagen());
                pst.setString(5, entity.getMateriales());
                pst.setInt(6, entity.getId_materiales().getId());
                pst.setInt(7, entity.getId());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Método para eliminar un equipo de la base de datos
    @Override
    public Equipo delete(Equipo entity) throws SQLException {
        if (entity == null || entity.getId() == 0) return entity;
        try (PreparedStatement pst = conn.prepareStatement(DELETE)) {
            pst.setInt(1, entity.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    // Método para encontrar un equipo por su ID
    @Override
    public Equipo findById(int key) {
        Equipo result = new Equipo();
        try (PreparedStatement pst = conn.prepareStatement(FINDBYID)) {
            pst.setString(1, String.valueOf(key));
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result.setId(res.getInt("id"));
                    result.setNombre(res.getString("nombre"));
                    result.setTipo(Tipo.valueOf(res.getString("tipo")));
                    result.setHabilidades(res.getString("habilidades"));
                    result.setImagen(res.getBytes("imagen"));
                    result.setMateriales(res.getString("materiales"));
                    result.setId_materiales(new MaterialesDAO().findById(res.getInt("idMateriales")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Método para encontrar todos los equipos
    public List<Equipo> findAll() {
        List<Equipo> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)) {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Equipo eq = new Equipo();
                eq.setId(res.getInt("id"));
                eq.setNombre(res.getString("nombre"));
                eq.setTipo(Tipo.valueOf(res.getString("tipo")));
                eq.setHabilidades(res.getString("habilidades"));
                eq.setImagen(res.getBytes("imagen"));
                eq.setMateriales(res.getString("materiales"));
                eq.setId_materiales(new MaterialesDAO().findById(res.getInt("idMateriales")));
                result.add(eq);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Método para cerrar la conexión (actualmente vacío)
    @Override
    public void close() throws IOException {
    }

    // Método estático para crear una nueva instancia de EquipoDAO
    public static EquipoDAO build() {
        return new EquipoDAO();
    }
}