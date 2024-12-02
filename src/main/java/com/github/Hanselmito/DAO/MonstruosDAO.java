package com.github.Hanselmito.DAO;

import com.github.Hanselmito.Conection.SQLConection;
import com.github.Hanselmito.Entity.*;
import com.github.Hanselmito.Entity.Enums.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MonstruosDAO implements DAO<Monstruos>{
    private final static String INSERT = "INSERT INTO monstruos (nombre, titulos, clase, elementos, estados, debilidad, habitats, tamano, parientes, imagen) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE monstruos SET nombre=?, titulos=?, clase=?, elementos=?, estados=?, debilidad=?, habitats=?, tamano=?, parientes=?, imagen=? WHERE id=?";
    private final static String DELETE = "DELETE FROM monstruos WHERE id=?";
    private final static String FINDALL = "SELECT * FROM monstruos";
    private final static String FINDBYID = "SELECT * FROM monstruos WHERE id=?";
    private final static String FINDBYCLASE = "SELECT nombre FROM monstruos WHERE clase=?";
    private final static String FINDBYNAME = "SELECT nombre, titulos, clase, elementos, estados, debilidad, habitats, tamano, parientes, imagen FROM monstruos WHERE nombre=?";
    private final static String FINDBYNAMEJOINF = "SELECT f.imagen, f.puntosDebiles, f.corte, f.impacto, f.disparo, f.parteRompibles FROM monstruos m JOIN fisiologia f ON m.id = f.idMonstruo WHERE m.nombre=?";
    private final static String FINDBYNAMEJOINDE = "SELECT d.elementoFuego, d.elementoAgua, d.elementoRayo, d.elementoHielo, d.elementoDraco, d.efectividadFuego, d.efectividadAgua, d.efectividadRayo, d.efectividadHielo, d.efectividadDraco, e.estadoVeneno, e.estadoSueno, e.estadoParalisis, e.estadoNitro, e.estadoAturdimiento, e.efectividadVeneno, e.efectividadSueno, e.efectividadParalisis, e.efectividadNitro, e.efectividadAturdimiento FROM monstruos m JOIN debilidades d ON m.id = d.idMonstruo JOIN estado e ON m.id = e.idMonstruo WHERE m.nombre=?";
    private final static String FINDBYNAMEJOINM = "SELECT ma.imagen, ma.nombre, ma.dropRate, ma.mediante, ma.cantidad FROM monstruos m JOIN materiales ma ON m.id = ma.idMonstruo WHERE m.nombre=?";
    private final static String FINDBYARMASJOIN = "SELECT a.imagen, a.nombre FROM armas a JOIN materiales ma ON a.idMateriales = ma.id JOIN monstruos mo ON ma.idMonstruo = mo.id WHERE mo.nombre = ?";
    private final static String FINDBYEQUIPOJOIN = "SELECT e.imagen, e.nombre, a.nombre AS habilidad FROM equipo e JOIN otorga o ON e.id = o.idEquipo JOIN abilidades a ON o.idAbilidades = a.id JOIN materiales ma ON e.idMateriales = ma.id JOIN monstruos mo ON ma.idMonstruo = mo.id WHERE mo.nombre = ?";
    private final static String FINDALLNAME = "SELECT nombre FROM monstruos";

    private Connection conn;
    public MonstruosDAO(){
        conn = SQLConection.getConnection();
    }

    /*
    * Guarda un objeto Monstruos en la base de datos.
    * Si la operación es exitosa, se actualiza el ID del objeto con el valor generado por la base de datos.
    * */
    @Override
    public Monstruos save(Monstruos entity) {
        Monstruos result = entity;
        if (entity == null) return result;
        try (PreparedStatement pst = SQLConection.getConnection().prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS)) {
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

            try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
    * Actualiza un objeto Monstruos existente en la base de datos.
    *  Utiliza el ID del objeto para identificar el registro a actualizar.
    */
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

    /*
    * Elimina un objeto Monstruos de la base de datos utilizando su ID.
    *  Si el ID es 0 o el objeto es nulo, no se realiza ninguna operación.
    */
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

    /*
    * Recupera todos los objetos Monstruos de la base de datos y los devuelve en una lista.
    */
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

    /*
    * Recupera todos los nombres de Monstruos que pertenecen a una clase específica y los devuelve en una lista.
    */
    public List<Monstruos> findByClase(String key){
        List<Monstruos> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDBYCLASE)){
            pst.setString(1, key);
            ResultSet res = pst.executeQuery();
            while (res.next()){
                Monstruos m = new Monstruos();
                m.setNombre(res.getString("nombre"));
                result.add(m);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    /*
    * Recupera todos los detalles de los Monstruos que coinciden con un nombre específico y los devuelve en una lista.
    */
    public List<Monstruos> findByName(String key){
        List<Monstruos> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDBYNAME)){
            pst.setString(1, key);
            ResultSet res = pst.executeQuery();
            while (res.next()){
                Monstruos m = new Monstruos();
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

    /*
    * Recupera la fisiología de un Monstruos específico por su nombre y la devuelve en una lista de objetos Fisiologia.
    */
    public List<Fisiologia> findFisiologiaByMonstruoName(String nombre) {
        List<Fisiologia> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDBYNAMEJOINF)) {
            pst.setString(1, nombre);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Fisiologia f = new Fisiologia();
                f.setImagen(res.getBytes("imagen"));
                f.setPuntos_debiles(res.getString("puntosDebiles"));
                f.setCorte(res.getInt("corte"));
                f.setImpacto(res.getInt("impacto"));
                f.setDisparo(res.getInt("disparo"));
                f.setPartes_rompibles(res.getString("parteRompibles"));
                // Set other fields of Fisiologia as needed
                result.add(f);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
    * Recupera las debilidades y estados de un Monstruos específico por su nombre y los devuelve en una lista de arreglos de objetos.
    */
    public List<Object[]> findDebilidadesYEstadosByMonstruoName(String nombre) {
        List<Object[]> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDBYNAMEJOINDE)) {
            pst.setString(1, nombre);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Debilidades d = new Debilidades();
                d.setElementoFuego(DebilElementoFuego.valueOf(res.getString("elementoFuego")));
                d.setElementoAgua(DebilElementoAgua.valueOf(res.getString("elementoAgua")));
                d.setElementoRayo(DebilElementoRayo.valueOf(res.getString("elementoRayo")));
                d.setElementoHielo(DebilElementoHielo.valueOf(res.getString("elementoHielo")));
                d.setElementoDraco(DebilElementoDraco.valueOf(res.getString("elementoDraco")));
                d.setEfectividadFuego(res.getInt("efectividadFuego"));
                d.setEfectividadAgua(res.getInt("efectividadAgua"));
                d.setEfectividadRayo(res.getInt("efectividadRayo"));
                d.setEfectividadHielo(res.getInt("efectividadHielo"));
                d.setEfectividadDraco(res.getInt("efectividadDraco"));

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

                result.add(new Object[]{d, es});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
    * Recupera los materiales asociados a un Monstruos específico por su nombre y los devuelve en una lista de objetos Materiales.
    */
    public List<Materiales> findMaterialesByMonstruoName(String nombre) {
        List<Materiales> result = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDBYNAMEJOINM)) {
            pst.setString(1, nombre);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                Materiales m = new Materiales();
                m.setImagen(res.getBytes("imagen"));
                m.setNombre(res.getString("nombre"));
                m.setDropRate(res.getString("dropRate"));
                m.setMediante(res.getString("mediante"));
                m.setCantidad(res.getInt("cantidad"));
                // Set other fields of Fisiologia as needed
                result.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
    * Recupera las armas asociadas a un Monstruos específico por su nombre y las devuelve en una lista de objetos Armas.
    */
    public List<Armas> findArmasByMonstruoName(String monsterName) {
        List<Armas> armasList = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDBYARMASJOIN)) {
            pst.setString(1, monsterName);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Armas arma = new Armas();
                    arma.setImagen(rs.getBytes("imagen"));
                    arma.setNombre(rs.getString("nombre"));
                    armasList.add(arma);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return armasList;
    }

    /*
    * Recupera el equipo asociado a un Monstruos específico por su nombre y lo devuelve en una lista de objetos Equipo.
    */
    public List<Equipo> findEquipoByMonstruoName(String monsterName) {
        List<Equipo> equipoList = new ArrayList<>();
        try (PreparedStatement pst = conn.prepareStatement(FINDBYEQUIPOJOIN)) {
            pst.setString(1, monsterName);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Equipo equipo = new Equipo();
                    equipo.setImagen(rs.getBytes("imagen"));
                    equipo.setNombre(rs.getString("nombre"));

                    Abilidades habilidad = new Abilidades();
                    habilidad.setNombre(rs.getString("habilidad"));

                    equipo.setList_Abilidades(Arrays.asList(habilidad));
                    equipoList.add(equipo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return equipoList;
    }

    /*
    * Recupera un objeto Monstruos específico por su ID y lo devuelve.
     */
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

    /*
    * Recupera todos los nombres de los Monstruos de la base de datos y los devuelve en una lista.
    */
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

    @Override
    public void close() {

    }

    public static MonstruosDAO build(){
        return new MonstruosDAO();
    }
}
