package it.eliacereda;

import model.db.Database;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;

@WebServlet("/prova")
public class Prova extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Database db = Database.getInstance();

        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer.print(db);
        writer.print(Paths.get(".").toAbsolutePath().normalize().toString());
    }
}
