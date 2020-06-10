/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.dao;

import com.emergentes.modelo.Producto;
import com.emergentes.utiles.ConexionBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author INTEL
 */
public class ProductoDAOimpl extends ConexionBD implements ProductoDAO{

    @Override
    public void insert(Producto producto) throws Exception {
        try {
            this.conectar();
            String sql = "insert into productos (descripcion,stock)values(?,?)";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, producto.getDescripcion());
            ps.setInt(2, producto.getStock());

            //ejecutar la consulta
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        }

    @Override
    public void uptade(Producto producto) throws Exception {
       try {
            this.conectar();
            String sql = "update productos set descripcion=?, stock=? where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1, producto.getDescripcion());
            ps.setInt(2, producto.getStock());
            ps.setInt(3, producto.getId());
            //ejecutar la consulta
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }  
    
    }

    @Override
    public void delete(int id) throws Exception {
      
        try {
            this.conectar();
            String sql = "delete from productos where id=?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            
            ps.setInt(1, id);
            //ejecutar la consulta
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }  
    
    }

    @Override
    public Producto getById(int id) throws Exception {
      Producto pro = new Producto();
        try {
            this.conectar();
            String sql = "select * from productos where id = ?";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                pro.setId(rs.getInt("id"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setStock(rs.getInt("stock"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return pro;  
    
    }

    @Override
    public List<Producto> getAll() throws Exception {
     List<Producto> lista = null;
        try {
            this.conectar();
            String sql = "select * from productos";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            lista = new ArrayList<Producto>();
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setId(rs.getInt("id"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setStock(rs.getInt("stock"));
                //adicionar a la colec cion
                lista.add(pro);
            }

        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    
    }
    
}
    