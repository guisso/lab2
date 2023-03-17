package io.github.guisso.sistematests;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@WebServlet(name = "PessoaServlet", urlPatterns = {"/PessoaServlet"})
public class PessoaServlet extends HttpServlet {

    @Inject
    PessoaServiceLocal pessoaService;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            Pessoa p = new Pessoa();
            p.setNome("Ana Zaira");
            p.setNascimento(LocalDate.of(1990, 1, 1));

            Credencial c = new Credencial();
            c.setEmail("ana@mail.com");
            c.setSenha("123456");

            Telefone t1 = new Telefone();
            t1.setDdd((byte) 38);
            t1.setNumero(12341234);
            
            Telefone t2 = new Telefone();
            t2.setDdd((byte) 38);
            t2.setNumero(43214321);
            
            Telefone t3 = new Telefone();
            t3.setDdd((byte) 44);
            t3.setNumero(78947894);
            
            Endereco e1 = new Endereco();
            e1.setLogradouro("Rua Dois");
            e1.setBairro("Village do Lago I");
            e1.setNumero(300);

            p.setCredencial(c);
            c.setPessoa(p);
            
            p.getTelefones().add(t1);
            p.getTelefones().add(t2);
            p.getTelefones().add(t3);
            t1.setPessoa(p);
            t2.setPessoa(p);
            t3.setPessoa(p);
            
            p.getEnderecos().add(e1);

            pessoaService.salvar(p);

            Pessoa pessoaAux = pessoaService.localizarPorId(1L);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Teste realizado com sucesso</h1>");
            out.println("<p>" + pessoaAux + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
