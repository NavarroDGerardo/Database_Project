import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import Usuario.Usuario;
import java.util.Vector;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Usuario")

public class ConexionUsuario extends HttpServlet{
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
            String name=request.getParameter("Name");
            String contraseña=request.getParameter("Contraseña");

            Statement stat = con.createStatement();

            String sql="INSERT INTO Usuario(name, contraseña) VALUES (‘"+name+"‘,‘"+contraseña+"‘)";
            //String sql="INSERT INTO Proveedor(correo, diaDeEntrega, nombre, telefono) VALUES (‘1‘,‘1‘,‘1‘‘1‘)";
           
            stat.executeUpdate(sql);

            stat.close();
            con.close();

            RequestDispatcher disp=getServletContext().getRequestDispatcher("/sign_up.html");

            if(disp!=null){
                disp.forward(request,response);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}