import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import Producto.Producto;
import java.util.Vector;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Producto")

public class ConexionProducto extends HttpServlet{
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
            Double precio=request.getParameter("Precio");
            String tipo=request.getParameter("Tipo");
            String nombre=request.getParameter("Nombre");
            String caducidad=request.getParameter("Caducidad");

            Statement stat = con.createStatement();

            String sql="INSERT INTO Producto(precio, tipo, nombre, caducidad) VALUES (‘"+precio+"‘,‘"+tipo+"‘,‘"+nombre+"‘,‘"+caducidad+"‘)";
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