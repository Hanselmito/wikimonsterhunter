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
import java.util.ArrayList;
import java.util.List;

public class MonstruosDAO implements DAO<Monstruos>{
    private final static String INSERT = "INSERT INTO monstruos (nombre, titulos, clase, elementos, estados, debilidad, habitats, tamano, parientes, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE monstruos SET nombre=?, titulos=?, clase=?, elementos=?, estados=?, debilidad=?, habitats=?, tamano=?, parientes=?, imagen=? WHERE id=?";
    private final static String DELETE = "DELETE FROM monstruos WHERE id=?";
    private final static String FINDALL = "SELECT * FROM monstruos";
    private final static String FINDBYID = "SELECT * FROM monstruos WHERE id=?";
    private final static String FINDBYCLASE = "SELECT * FROM monstruos WHERE clase=?";

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
                pst.setString(1, entity.getNombre());
                pst.setString(2, entity.getTitulos());
                pst.setString(3, entity.getClase().getPartOfClase());
                pst.setString(4, entity.getElementos().getPartOfElementos());
                pst.setString(5, entity.getEstados().getPartOfEstados());
                pst.setString(6, entity.getDebilidad().getPartOfDebilidad());
                pst.setString(7, entity.getHabitats());
                pst.setString(8, entity.getTamano());
                pst.setString(9, entity.getParientes());
                pst.setBytes(10, entity.getImagen());
                pst.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Monstruos update(Monstruos entity) {
        Monstruos result = entity;
        if (entity==null || entity.getId()==0) return result;
        try(PreparedStatement pst = SQLConection.getConnection().prepareStatement(UPDATE)){
            pst.setString(1, entity.getNombre());
            pst.setString(2, entity.getTitulos());
            pst.setString(3, entity.getClase().getPartOfClase());
            pst.setString(4, entity.getElementos().getPartOfElementos());
            pst.setString(5, entity.getEstados().getPartOfEstados());
            pst.setString(6, entity.getDebilidad().getPartOfDebilidad());
            pst.setString(7, entity.getHabitats());
            pst.setString(8, entity.getTamano());
            pst.setString(9, entity.getParientes());
            pst.setBytes(10, entity.getImagen());
            pst.setInt(11,entity.getId());
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Monstruos delete(Monstruos entity) {
        if(entity == null || entity.getId()==0) return entity;
        try (PreparedStatement pst = conn.prepareStatement(DELETE)){
            pst.setInt(1,entity.getId());
            pst.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public List<Monstruos> findAll(){
        List<Monstruos> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDALL)) {
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Monstruos m = new Monstruos();
                m.setId(res.getInt("id"));
                m.setNombre(res.getString("nombre"));
                m.setTitulos(res.getString("titulos"));
                m.setClase(Clase.valueOf(res.getString("clase")));
                m.setElementos(Elementos.valueOf(res.getString("elementos")));
                m.setEstados(Estados.valueOf(res.getString("estados")));
                m.setDebilidad(Debilidad.valueOf(res.getString("debilidad")));
                m.setHabitats(res.getString("habitats"));
                m.setTamano(res.getString("tamano"));
                m.setParientes(res.getString("parientes"));
                m.setImagen(res.getBytes("imagen"));
                result.add(m);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Monstruos> findByClase(String key){
        List<Monstruos> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDBYCLASE)){
            pst.setString(1, key);
            ResultSet res = pst.executeQuery();
            while (res.next()){
                Monstruos m = new Monstruos();
                m.setId(res.getInt("id"));
                m.setNombre(res.getString("nombre"));
                m.setTitulos(res.getString("titulos"));
                m.setClase(Clase.valueOf(res.getString("clase")));
                m.setElementos(Elementos.valueOf(res.getString("elementos")));
                m.setEstados(Estados.valueOf(res.getString("estados")));
                m.setDebilidad(Debilidad.valueOf(res.getString("debilidad")));
                m.setHabitats(res.getString("habitats"));
                m.setTamano(res.getString("tamano"));
                m.setParientes(res.getString("parientes"));
                m.setImagen(res.getBytes("imagen"));
                result.add(m);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
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
