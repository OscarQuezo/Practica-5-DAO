/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;

import com.emergentes.dao.ProductoDAO;
import com.emergentes.dao.ProductoDAOimpl;
import com.emergentes.modelo.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author INTEL
 */
@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            ProductoDAO dao = new ProductoDAOimpl();
            int id; //para recibir el id
            Producto pro = new Producto(); 

            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            switch (action) {
                case "add":
                    //nuevo registro
                    request.setAttribute("producto", pro);
                    request.getRequestDispatcher("ABproducto.jsp").forward(request, response);
                    
                    break;

                case "edit":
                   
                    id = Integer.parseInt(request.getParameter("id"));
                    pro = dao.getById(id);
                    request.setAttribute("producto", pro);
                    request.getRequestDispatcher("ABproducto.jsp").forward(request, response);
                    
                    break;

                case "delete":
                    //eliminar registro
                    id = Integer.parseInt(request.getParameter("id"));
                    dao.delete(id);
                    response.sendRedirect(request.getContextPath()+"/Inicio");
                    break;

                default:
                    //listar los registros
                    List<Producto> lista = dao.getAll(); //en lista esta todos los datos
                    request.setAttribute("productos", lista);
                    request.getRequestDispatcher("listado.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }

        
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProductoDAO dao = new ProductoDAOimpl();
        
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
         int stock = Integer.parseInt(request.getParameter("stock"));
       // String stock = request.getParameter("stock");
       
        
        Producto pro = new Producto();
        pro.setId(id);
        pro.setDescripcion(descripcion);
        pro.setStock(stock);
        if (id == 0){
            // si ID es cero es neuvo registro
            try {
                dao.insert(pro);                
                response.sendRedirect("Inicio");//aqui es donde manda el control
            } catch (Exception e) {
            }
                    
            
        }else{
            // edicio n o actualizacion de un registro
            try {
                dao.uptade(pro);                
                response.sendRedirect("Inicio");//aqui es donde manda el control
            } catch (Exception e) {
            }
        }
        
        
      
    }

}
