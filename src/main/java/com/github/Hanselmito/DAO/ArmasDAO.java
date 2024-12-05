package com.github.Hanselmito.DAO;

import com.github.Hanselmito.Conection.SQLConection;
import com.github.Hanselmito.Entity.Armas;
import com.github.Hanselmito.Entity.Enums.Atributo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArmasDAO implements DAO<Armas> {
    // Consultas SQL para las operaciones CRUD
    private final static String INSERT = "INSERT INTO armas (imagen, nombre, ataque, atributo, afilado, afinidad, defensa, ranuras, materiales, idMateriales) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE armas SET imagen=?, nombre=?, ataque=?, atributo=?, afilado=?, afinidad=?, defensa=?, ranuras=?, materiales=?, idMateriales=? WHERE id=?";
    private final static String DELETE = "DELETE FROM armas WHERE id=?";
    private final static String FINDALL = "SELECT * FROM armas";
    private final static String FINDBYID = "SELECT * FROM armas WHERE id=?";
    private final static String FINDALLNAME = "SELECT nombre FROM armas";
    private final static String FINDALLWITHOUTNAMEANDIMAGE = "SELECT ataque, atributo, afilado, afinidad, defensa, ranuras, materiales FROM armas where nombre=?";

    private Connection conn;

    // Constructor que inicializa la conexión a la base de datos
    public ArmasDAO() {
        conn = SQLConection.getConnection();
    }

    // Método para guardar una nueva arma en la base de datos
    @Override
    public Armas save(Armas entity) {
        Armas result = entity;
        if (entity == null || entity.getId_materiales().getId() == 0) return result;
        Armas a = findById(entity.getId_materiales().getId());
        if (a != null) {
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(INSERT)) {
                pst.setBytes(1, entity.getImagen());
                pst.setString(2, entity.getNombre());
                pst.setInt(3, entity.getAtaque());
                pst.setString(4, entity.getAtributo().getPartOfAtributo());
                pst.setString(5, entity.getAfilado());
                pst.setString(6, entity.getAfinidad());
                pst.setInt(7, entity.getDefensa());
                pst.setInt(8, entity.getRanuras());
                pst.setString(9, entity.getMateriales());
                pst.setInt(10, entity.getId_materiales().getId());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Método para actualizar una arma existente en la base de datos
    @Override
    public Armas update(Armas entity) {
        Armas result = entity;
        if (entity == null || entity.getId_materiales().getId() == 0) return result;
        Armas a = findById(entity.getId_materiales().getId());
        if (a != null) {
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(UPDATE)) {
                pst.setBytes(1, entity.getImagen());
                pst.setString(2, entity.getNombre());
                pst.setInt(3, entity.getAtaque());
                pst.setString(4, entity.getAtributo().getPartOfAtributo());
                pst.setString(5, entity.getAfilado());
                pst.setString(6, entity.getAfinidad());
                pst.setInt(7, entity.getDefensa());
                pst.setInt(8, entity.getRanuras());
                pst.setString(9, entity.getMateriales());
                pst.setInt(10, entity.getId_materiales().getId());
                pst.setInt(11, entity.getId());
                pst.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // Método para eliminar una arma de la base de datos
    @Override
    public Armas delete(Armas entity) throws SQLException {
        if (entity == null || entity.getId() == 0) return entity;
        try (PreparedStatement pst = conn.prepareStatement(DELETE)) {
            pst.setInt(1, entity.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    // Método para encontrar una arma por su ID
    @Override
    public Armas findById(int key) {
        Armas result = new Armas();
        try (PreparedStatement pst = conn.prepareStatement(FINDBYID)) {
            pst.setString(1, String.valueOf(key));
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    result.setId(res.getInt("id"));
                    result.setImagen(res.getBytes("imagen"));
                    result.setNombre(res.getString("nombre"));
                    result.setAtaque(res.getInt("ataque"));
                    result.setAtributo(Atributo.valueOf(res.getString("atributo")));
                    result.setAfilado(res.getString("afilado"));
                    result.setAfinidad(res.getString("afinidad"));
                    result.setDefensa(res.getInt("defensa"));
                    result.setRanuras(res.getInt("ranuras"));
                    result.setMateriales(res.getString("materiales"));
                    result.setId_materiales(new MaterialesDAO().findById(res.getInt("idMateriales")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // Método para encontrar todas las armas
    public List<Armas> findAll() {
        List<Armas> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)) {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Armas a = new Armas();
                a.setId(res.getInt("id"));
                a.setImagen(res.getBytes("imagen"));
                a.setNombre(res.getString("nombre"));
                a.setAtaque(res.getInt("ataque"));
                a.setAtributo(Atributo.valueOf(res.getString("atributo")));
                a.setAfilado(res.getString("afilado"));
                a.setAfinidad(res.getString("afinidad"));
                a.setDefensa(res.getInt("defensa"));
                a.setRanuras(res.getInt("ranuras"));
                a.setMateriales(res.getString("materiales"));
                a.setId_materiales(new MaterialesDAO().findById(res.getInt("idMateriales")));
                result.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<String> findAllNames() {
        List<String> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALLNAME)) {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                result.add(res.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    // ArmasDAO.java
    public List<Armas> findAllWithoutNameAndImage(String nombre) {
        List<Armas> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALLWITHOUTNAMEANDIMAGE)) {
            pst.setString(1, nombre);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Armas a = new Armas();
                a.setAtaque(res.getInt("ataque"));
                a.setAtributo(Atributo.valueOf(res.getString("atributo")));
                a.setAfilado(res.getString("afilado"));
                a.setAfinidad(res.getString("afinidad"));
                a.setDefensa(res.getInt("defensa"));
                a.setRanuras(res.getInt("ranuras"));
                a.setMateriales(res.getString("materiales"));
                result.add(a);
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
}