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
    private static final String INSERT = "INSERT INTO abilidades (nombre, nivel, descripcion, set) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE abilidades SET nombre=?, nivel=?, descripcion=?, set=? WHERE id=?";
    private static final String DELETE = "DELETE FROM abilidades WHERE id=?";
    private static final String FINDALL = "SELECT * FROM abilidades";
    private static final String FINDBYID = "SELECT * FROM abilidades WHERE id=?";


    private Connection conn;
    public AbilidadesDAO(){
        conn = SQLConection.getConnection();
    }

    @Override
    public Abilidades save(Abilidades entity) {
        Abilidades result = entity;
        if (entity==null || entity.getId()==0) return result;
        Abilidades ab = findById(entity.getId());
        if(ab!=null) {
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(INSERT)) {
                pst.setString(1, entity.getNombre());
                pst.setInt(2, entity.getNivel());
                pst.setString(3, entity.getDescripcion());
                pst.setString(4, entity.getSet());
                pst.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Abilidades update(Abilidades entity) {
        Abilidades result = entity;
        if (entity==null || entity.getId()==0) return result;
        try(PreparedStatement pst = SQLConection.getConnection().prepareStatement(UPDATE)){
            pst.setString(1, entity.getNombre());
            pst.setInt(2, entity.getNivel());
            pst.setString(3, entity.getDescripcion());
            pst.setString(4, entity.getSet());
            pst.setInt(5, entity.getId());
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
                    result.setNombre(res.getString("nombre"));
                    result.setNivel(res.getInt("nivel"));
                    result.setDescripcion(res.getString("descripcion"));
                    result.setSet(res.getString("set"));
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
                ab.setNombre(res.getString("nombre"));
                ab.setNivel(res.getInt("nivel"));
                ab.setDescripcion(res.getString("descripcion"));
                ab.setSet(res.getString("set"));
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
