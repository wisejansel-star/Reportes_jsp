package servlets;

import datos.Conexion;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;


import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;

@WebServlet("/SL_VW_Customer_List_Reporte")
public class SL_VW_Customer_List_Reporte extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            Connection cn = Conexion.getInstance();
            String pais = "";

            pais = request.getParameter("paises");
            System.out.println("Parametro: " + pais);
            
            
            HashMap<String, Object> hm = new HashMap<String, Object>();
            hm.put("pais", pais);

            System.out.println(hm);
            OutputStream otps = response.getOutputStream();
            ServletContext context = getServletContext();
            String path = context.getRealPath("/");
            String template = "reportes\\Flower.jasper";
            Exporter exporter = new JRPdfExporter();
            System.out.println(path+template);
            JasperPrint jasperPrint = JasperFillManager.fillReport(path+template, hm, cn);
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=\"Flower.pdf\"");
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(otps));
            exporter.exportReport();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("REPORTE: Error al generar el reporte " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);
    }
}
