/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.daw.bean.implementation;

import com.google.gson.annotations.Expose;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import net.daw.bean.publicinterface.GenericBean;
import net.daw.dao.implementation.HotelDao;
import net.daw.dao.implementation.UsuarioDao;
import net.daw.helper.statics.EncodingUtilHelper;

/**
 *
 * @author Alvaro
 */
public class ReservaBean implements GenericBean {

    @Expose
    private Integer id = 0;
    @Expose
    private Date fecha_entrada;
    @Expose
    private Date fecha_salida;
    @Expose
    private Integer num_plazas = 0;
    @Expose
    private Integer precio_reserva = 0;

    @Expose(serialize = false)
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    private UsuarioBean obj_usuario = null;
    
    @Expose(serialize = false)
    private Integer id_hotel = 0;
    @Expose(deserialize = false)
    private HotelBean obj_hotel = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Integer getNum_plazas() {
        return num_plazas;
    }

    public void setNum_plazas(Integer num_plazas) {
        this.num_plazas = num_plazas;
    }

    public Integer getPrecio_reserva() {
        return precio_reserva;
    }

    public void setPrecio_reserva(Integer precio_reserva) {
        this.precio_reserva = precio_reserva;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public UsuarioBean getObj_usuario() {
        return obj_usuario;
    }

    public void setObj_usuario(UsuarioBean obj_usuario) {
        this.obj_usuario = obj_usuario;
    }

    public Integer getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(Integer id_hotel) {
        this.id_hotel = id_hotel;
    }

    public HotelBean getObj_hotel() {
        return obj_hotel;
    }

    public void setObj_hotel(HotelBean obj_hotel) {
        this.obj_hotel = obj_hotel;
    }
    
    public ReservaBean() {
    }

    public ReservaBean(Integer id) {
        this.id = id;
    }

    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "fecha_entrada,";
        strColumns += "fecha_salida,";
        strColumns += "num_plazas,";
        strColumns += "precio_reserva,";
        strColumns += "id_usuario,";
        strColumns += "id_hotel";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(fecha_entrada) + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(fecha_salida) + ",";
        strColumns += num_plazas + ",";
        strColumns += precio_reserva + ",";
        strColumns += id_usuario + ",";
        strColumns += id_hotel;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        //strPairs += "id=" + id + ",";
        strPairs += "fecha_entrada=" + EncodingUtilHelper.stringifyAndQuotate(fecha_entrada) + ",";
        strPairs += "fecha_salida=" + EncodingUtilHelper.stringifyAndQuotate(fecha_salida) + ",";
        strPairs += "num_plazas=" + num_plazas + ",";
        strPairs += "precio_reserva=" + precio_reserva + ",";
        strPairs += "id_usuario=" + id_usuario + ",";
        strPairs += "id_hotel=" + id_hotel;
        return strPairs;
    }

    @Override
    public GenericBean fill(ResultSet oResultSet, Connection pooledConnection, UsuarioBean oPusuarioBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setFecha_entrada(oResultSet.getTimestamp("fecha_entrada"));
        this.setFecha_salida(oResultSet.getTimestamp("fecha_salida"));
        this.setNum_plazas(oResultSet.getInt("num_plazas"));
        this.setPrecio_reserva(oResultSet.getInt("precio_reserva"));
        
        if (expand > 0) {
            UsuarioBean oUsuarioBean = new UsuarioBean();
            UsuarioDao ooUsuarioDao = new UsuarioDao(pooledConnection, oPusuarioBean_security, null);
            oUsuarioBean.setId(oResultSet.getInt("id_usuario"));
            oUsuarioBean = ooUsuarioDao.get(oUsuarioBean, expand - 1);
            this.setObj_usuario(oUsuarioBean);
        } else {
            this.setId_usuario(oResultSet.getInt("id_usuario"));
        }
        
        if (expand > 0) {
            HotelBean oHotelBean = new HotelBean();
            HotelDao ooHotelDao = new HotelDao(pooledConnection, oPusuarioBean_security, null);
            oHotelBean.setId(oResultSet.getInt("id_hotel"));
            oHotelBean = ooHotelDao.get(oHotelBean, expand - 1);
            this.setObj_hotel(oHotelBean);
        } else {
            this.setId_hotel(oResultSet.getInt("id_hotel"));
        }
        
        return this;
    }
}
