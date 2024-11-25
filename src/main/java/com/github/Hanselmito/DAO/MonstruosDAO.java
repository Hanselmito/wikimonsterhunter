package com.github.Hanselmito.DAO;

import com.github.Hanselmito.Conection.SQLConection;
import com.github.Hanselmito.Entity.Enums.Clase;
import com.github.Hanselmito.Entity.Enums.Debilidad;
import com.github.Hanselmito.Entity.Enums.Elementos;
import com.github.Hanselmito.Entity.Enums.Estados;
import com.github.Hanselmito.Entity.Monstruos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonstruosDAO implements DAO<Monstruos>{
    private final static String INSERT = "INSERT INTO monstruos (nombre, titulos, clase, elementos, estados, debilidad, habitats, tamano, parientes, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String FINDBYID = "SELECT * FROM monstruos WHERE id=?";

    private Connection conn;
    public MonstruosDAO(){
        conn = SQLConection.getConnection();
    }

    @Override
    public Monstruos save(Monstruos entity) {
        Monstruos result = entity;
        if (entity==null || entity.getId()==0) return result;
        Monstruos m = findById(entity.getId());
        if(m!=null){
            try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(INSERT)){
                pst.setInt(1, entity.getId());
                pst.setString(2, entity.getNombre());
                pst.setString(3, entity.getTitulos());
                pst.setString(4, entity.getClase().getPartOfClase());
                pst.setString(5, entity.getElementos().getPartOfElementos());
                pst.setString(6, entity.getEstados().getPartOfEstados());
                pst.setString(7, entity.getDebilidad().getPartOfDebilidad());
                pst.setString(8, entity.getHabitats());
                pst.setString(9, entity.getTamano());
                pst.setString(10, entity.getParientes());
                pst.setBytes(11, entity.getImagen());
                pst.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Monstruos update(Monstruos entity) {
        return null;
    }

    @Override
    public Monstruos delete(Monstruos entity) {
        return null;
    }

    @Override
    public Monstruos findById(int key) {
        Monstruos result = new Monstruos();
        try(PreparedStatement pst = conn.prepareStatement(FINDBYID)){
            pst.setString(1, String.valueOf(key));
            try(ResultSet res = pst.executeQuery()){
                if(res.next()){
                    result.setId(res.getInt("id"));
                    result.setNombre(res.getString("nombre"));
                    result.setTitulos(res.getString("titulos"));
                    result.setClase(Clase.valueOf(res.getString("clase")));
                    result.setElementos(Elementos.valueOf(res.getString("elementos")));
                    result.setEstados(Estados.valueOf(res.getString("estados")));
                    result.setDebilidad(Debilidad.valueOf(res.getString("debilidad")));
                    result.setHabitats(res.getString("habitats"));
                    result.setTamano(res.getString("tamano"));
                    result.setParientes(res.getString("parientes"));
                    result.setImagen(res.getBytes("imagen"));
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void close() {

    }
}
