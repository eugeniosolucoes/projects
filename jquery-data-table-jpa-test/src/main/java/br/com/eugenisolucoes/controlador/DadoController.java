/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.eugenisolucoes.controlador;

import br.com.eugenisolucoes.datatable.InputDataTable;
import br.com.eugenisolucoes.datatable.OutputDataTable;
import br.com.eugenisolucoes.servico.DadoServico;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author eugenio
 */
@WebServlet( name = "DadosController", urlPatterns = { "/dados" } )
public class DadoController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        response.setContentType( "application/json" );

        PrintWriter out = response.getWriter();

        InputDataTable dados = new InputDataTable( request, "engine", "browser", 
                "platform", "version", "grade" );

        OutputDataTable outputDataTable = new DadoServico().listar( dados );

        try {

            JSONObject output = new JSONObject( );

            output.put( "sEcho", outputDataTable.getsEcho() );
            output.put( "iTotalRecords", outputDataTable.getiTotalRecords() );
            output.put( "iTotalDisplayRecords", outputDataTable.getiTotalDisplayRecords() );
            output.put( "aaData", outputDataTable.getAaData() );

            out.print( output );
            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        processRequest( request, response );
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
