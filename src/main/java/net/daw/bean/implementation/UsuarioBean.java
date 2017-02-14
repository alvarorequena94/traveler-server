/*
 * Copyright (c) 2016 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 *
 * sisane-server: Helps you to develop easily AJAX web applications
 *                   by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/sisane-server
 *
 * sisane-server is distributed under the MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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

public class UsuarioBean implements GenericBean {

    @Expose
    private Integer id = 0;
    @Expose
    private String nombre;
    @Expose
    private String primerapellido;
    @Expose
    private String segundoapellido;
    @Expose
    private String email;
    @Expose
    private String login;
    @Expose(serialize = false)
    private String password;
    @Expose
    private String telefono;
    @Expose
    private Date fecha_registro;
    
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

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getSegundoapellido() {
        return segundoapellido;
    }

    public void setSegundoapellido(String segundoapellido) {
        this.segundoapellido = segundoapellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
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
    
    public UsuarioBean() {
    }

    public UsuarioBean(Integer id) {
        this.id = id;
    }
    
    @Override
    public String getColumns() {
        String strColumns = "";
        strColumns += "id,";
        strColumns += "nombre,";
        strColumns += "primerapellido,";
        strColumns += "segundoapellido,";
        strColumns += "email,";
        strColumns += "login,";
        strColumns += "password,";
        strColumns += "telefono,";
        strColumns += "fecha_registro,";
        strColumns += "id_provincia";
        return strColumns;
    }

    @Override
    public String getValues() {
        String strColumns = "";
        strColumns += id + ",";
        strColumns += EncodingUtilHelper.quotate(nombre) + ",";
        strColumns += EncodingUtilHelper.quotate(primerapellido) + ",";
        strColumns += EncodingUtilHelper.quotate(segundoapellido) + ",";
        strColumns += EncodingUtilHelper.quotate(email) + ",";
        strColumns += EncodingUtilHelper.quotate(login) + ",";
        strColumns += EncodingUtilHelper.quotate(password) + ",";
        strColumns += EncodingUtilHelper.quotate(telefono) + ",";
        strColumns += EncodingUtilHelper.stringifyAndQuotate(fecha_registro) + ",";
        strColumns += id_provincia;
        return strColumns;
    }

    @Override
    public String toPairs() {
        String strPairs = "";
        strPairs += "nombre=" + EncodingUtilHelper.quotate(nombre) + ",";
        strPairs += "primerapellido=" + EncodingUtilHelper.quotate(primerapellido) + ",";
        strPairs += "segundoapellido=" + EncodingUtilHelper.quotate(segundoapellido) + ",";
        strPairs += "email=" + EncodingUtilHelper.quotate(email) + ",";
        strPairs += "login=" + EncodingUtilHelper.quotate(login) + ",";
        strPairs += "password=" + EncodingUtilHelper.quotate(password) + ",";
        strPairs += "telefono=" + EncodingUtilHelper.quotate(telefono) + ",";
        strPairs += "fecha_registro=" + EncodingUtilHelper.stringifyAndQuotate(fecha_registro) + ",";
        strPairs += "id_provincia=" + id_provincia;
        return strPairs;
    }

    @Override
    public UsuarioBean fill(ResultSet oResultSet, Connection pooledConnection, UsuarioBean oPusuarioBean_security, Integer expand) throws SQLException, Exception {
        this.setId(oResultSet.getInt("id"));
        this.setNombre(oResultSet.getString("nombre"));
        this.setPrimerapellido(oResultSet.getString("primerapellido"));
        this.setSegundoapellido(oResultSet.getString("segundoapellido"));
        this.setEmail(oResultSet.getString("email"));
        this.setLogin(oResultSet.getString("login"));
        this.setPassword(oResultSet.getString("password"));
        this.setTelefono(oResultSet.getString("telefono"));
        this.setFecha_registro(oResultSet.getTimestamp("fecha_registro"));

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
