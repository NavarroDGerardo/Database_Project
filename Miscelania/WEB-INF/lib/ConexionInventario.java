import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import Inventario.Inventario;
import java.util.Vector;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Inventario")

public class ConexionInventario extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try{
           String base=getServletContext().getInitParameter("base");
           String contraseña=getServletContext().getInitParameter("contraseña");
           String baseUsuario=getServletContext().getInitParameter("baseUsuario");

           Class.forName("com.mysql.jdbc.Driver");

            //Después de localhost va el nombre de la base

            String url = "jdbc:mysql://localhost/proyecto?useSSL=false&allowPublicKeyRetrieval=true";
             
            //Parametros son dirección de la base, usuario y contraseña de mysql
            Connection con = DriverManager.getConnection(url,"usuarioBase", "contraseña");
            String tipo_de_producto=request.getParameter("Tipo_de_producto");
            Integer cantidad_disponible=request.getParameter("Cantidad_disponible");

            Statement stat = con.createStatement();

            String sql="INSERT INTO Inventario(tipo_de_producto, cantidad_disponible) VALUES (‘"+tipo_de_producto+"‘,‘"+cantidad_disponible+"‘)";
            //String sql="INSERT INTO Proveedor(correo, diaDeEntrega, nombre, telefono) VALUES (‘1‘,‘1‘,‘1‘‘1‘)";
           
            stat.executeUpdate(sql);

            stat.close();
            con.close();

            RequestDispatcher disp=getServletContext().getRequestDispatcher("/options.html");

            if(disp!=null){
                disp.forward(request,response);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}