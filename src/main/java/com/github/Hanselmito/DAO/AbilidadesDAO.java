package com.github.Hanselmito.DAO;

import com.github.Hanselmito.Conection.SQLConection;
import com.github.Hanselmito.Entity.Abilidades;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbilidadesDAO implements DAO<Abilidades>{
    private static final String INSERT = "INSERT INTO abilidades (imagen, nombre, nivel, descripcion, equip) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE abilidades SET imagen=?, nombre=?, nivel=?, descripcion=?, equip=? WHERE id=?";
    private static final String DELETE = "DELETE FROM abilidades WHERE id=?";
    private static final String FINDALL = "SELECT * FROM abilidades";
    private static final String FINDBYID = "SELECT * FROM abilidades WHERE id=?";


    private Connection conn;
    public AbilidadesDAO(){
        conn = SQLConection.getConnection();
    }

    @Override
    public Abilidades save(Abilidades entity) {
        if (entity == null) {
            System.out.println("La entidad es nula.");
            return null;
        }
        try (PreparedStatement pst = conn.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pst.setBytes(1, entity.getImagen());
            pst.setString(2, entity.getNombre());
            pst.setInt(3, entity.getNivel());
            pst.setString(4, entity.getDescripcion());
            pst.setString(5, entity.getEquip());
            int affectedRows = pst.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("La inserción de la entidad falló, no se afectaron filas.");
            }
            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("La inserción de la entidad falló, no se obtuvo el ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Abilidades update(Abilidades entity) {
        Abilidades result = entity;
        if (entity==null || entity.getId()==0) return result;
        try(PreparedStatement pst = SQLConection.getConnection().prepareStatement(UPDATE)){
            pst.setBytes(1, entity.getImagen());
            pst.setString(2, entity.getNombre());
            pst.setInt(3, entity.getNivel());
            pst.setString(4, entity.getDescripcion());
            pst.setString(5, entity.getEquip());
            pst.setInt(6, entity.getId());
            pst.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Abilidades delete(Abilidades entity) throws SQLException {
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
    public Abilidades findById(int key) {
        Abilidades result = new Abilidades();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYID)){
            pst.setString(1, String.valueOf(key));
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    result.setId(res.getInt("id"));
                    result.setImagen(res.getBytes("imagen"));
                    result.setNombre(res.getString("nombre"));
                    result.setNivel(res.getInt("nivel"));
                    result.setDescripcion(res.getString("descripcion"));
                    result.setEquip(res.getString("equip"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public List<Abilidades> findAll() {
        List<Abilidades> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)) {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Abilidades ab = new Abilidades();
                ab.setId(res.getInt("id"));
                ab.setImagen(res.getBytes("imagen"));
                ab.setNombre(res.getString("nombre"));
                ab.setNivel(res.getInt("nivel"));
                ab.setDescripcion(res.getString("descripcion"));
                ab.setEquip(res.getString("equip"));
                result.add(ab);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() throws IOException {

    }

    public static AbilidadesDAO build(){
        return new AbilidadesDAO();
    }

}
