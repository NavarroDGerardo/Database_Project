import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import Cliente.Cliente;
import java.util.Vector;

import javax.servlet.annotation.WebServlet;

@WebServlet("/Cliente")

public class ConexionCliente extends HttpServlet{
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try{
           String base=getServletContext().getInitParameter("base");
           String contraseña=getServletContext().getInitParameter("contraseña");
           String baseUsuario=getServletContext().getInitParameter("baseUsuario");

           Class.forName("com.mysql.jdbc.Driver");

            

            String url = "jdbc:mysql://localhost/proyecto?useSSL=false&allowPublicKeyRetrieval=true";
             
            
            Connection con = DriverManager.getConnection(url,"usuarioBase", "contraseña");
            String nombre=request.getParameter("Nombre");
            String diaDeVisita=request.getParameter("DiaDeVisita");

            Statement stat = con.createStatement();

            String sql="INSERT INTO Cliente(nombre, diaDeVisita) VALUES (‘"+nombre+"‘,‘"+diaDeVisita+"‘)";
           
           
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