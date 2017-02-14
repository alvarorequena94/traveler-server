/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*

http://localhost:8081/traveler-server/json?ob=usuario&op=login&user=alvaro&pass=alvaro

http://localhost:8081/traveler-server/json?ob=usuario&op=logout

http://localhost:8081/traveler-server/json?ob=hotel&op=getall

http://localhost:8081/traveler-server/json?ob=provincia&op=set&json={"id":0,"nombre":"provPrueba"}

*/

package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.ProvinciaDao;
import net.daw.helper.statics.EncodingUtilHelper;

/**
 *
 * @author Alvaro
 */
public class HotelBean implements GenericBean {

    @Expose
    private Integer id = 0;
    @Expose
    private String nombre;
    @Expose
    private String descripcion;
    @Expose
    private Integer estrellas = 0;
    @Expose
    private Integer hab_individuales = 0;
    @Expose
    private Integer hab_dobles = 0;
    @Expose
    private Integer precio_noche = 0;
    @Expose
    private String imagen;


    @Expose(serialize = false)
    private Integer id_provincia = 0;
    @Expose(deserialize = false)
    private ProvinciaBean obj_provincia = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    public Integer getHab_individuales() {
        return hab_individuales;
    }

    public void setHab_individuales(Integer hab_individuales) {
        this.hab_individuales = hab_individuales;
    }

    public Integer getHab_dobles() {
        return hab_dobles;
    }

    public void setHab_dobles(Integer hab_dobles) {
        this.hab_dobles = hab_dobles;
    }

    public Integer getPrecio_noche() {
        return precio_noche;
    }

    public void setPrecio_noche(Integer precio_noche) {
        this.precio_noche = precio_noche;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(Integer id_provincia) {
        this.id_provincia = id_provincia;
    }

    public ProvinciaBean getObj_provincia() {
        return obj_provincia;
    }

    public void setObj_provincia(ProvinciaBean obj_provincia) {
        this.obj_provincia = obj_provincia;
    }

    
    public HotelBean() {
    }

    public HotelBean(Integer id) {
        this.id = id;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "nombre,";
        strColumns += "descripcion,";
        strColumns += "estrellas,";
        strColumns += "hab_individuales,";
        strColumns += "hab_dobles,";
        strColumns += "precio_noche,";
        strColumns += "imagen,";
        strColumns += "id_provincia";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(nombre) + ",";
        strColumns += EncodingUtilHelper.quotate(descripcion) + ",";
        strColumns += estrellas + ",";
        strColumns += hab_individuales + ",";
        strColumns += hab_dobles + ",";
        strColumns += precio_noche + ",";
        strColumns += EncodingUtilHelper.quotate(imagen) + ",";
        strColumns += id_provincia;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        //strPairs += "id=" + id + ",";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre) + ",";
        strPairs += "descripcion=" + EncodingUtilHelper.quotate(descripcion) + ",";
        strPairs += "estrellas" + estrellas + ",";
        strPairs += "hab_individuales" + hab_individuales + ",";
        strPairs += "hab_dobles" + hab_dobles + ",";
        strPairs += "precio_noche" + precio_noche + ",";
        strPairs += "id_provincia=" + id_provincia;
        strPairs += "imagen=" + EncodingUtilHelper.quotate(imagen) + ",";
        return strPairs;
    }

    @Override
    public GenericBean fill(ResultSet oResultSet, Connection pooledConnection, UsuarioBean oPusuarioBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setDescripcion(oResultSet.getString("descripcion"));
        this.setEstrellas(oResultSet.getInt("estrellas"));
        this.setHab_individuales(oResultSet.getInt("hab_individuales"));
        this.setHab_dobles(oResultSet.getInt("hab_dobles"));
        this.setPrecio_noche(oResultSet.getInt("precio_noche"));
        this.setImagen(oResultSet.getString("imagen"));
        
        if (expand > 0) {
            ProvinciaBean oProvinciaBean = new ProvinciaBean();
            ProvinciaDao oProvinciaDao = new ProvinciaDao(pooledConnection, oPusuarioBean_security, null);
            oProvinciaBean.setId(oResultSet.getInt("id_provincia"));
            oProvinciaBean = oProvinciaDao.get(oProvinciaBean, expand - 1);
            this.setObj_provincia(oProvinciaBean);
        } else {
            this.setId_provincia(oResultSet.getInt("id_provincia"));
        }
        
        return this;
    }
}
