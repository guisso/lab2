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
import javax.transaction.Transactional;

/**
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Transactional // <-- Torna cada método uma transação completa
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

            // Pessoa1
            Pessoa p1 = new Pessoa();
            p1.setNome("Ana Zaira");
            p1.setNascimento(LocalDate.of(1990, 1, 1));
            
            // Pessoa2
            Pessoa p2 = new Pessoa();
            p2.setNome("Beatriz Yana");
            p2.setNascimento(LocalDate.of(2000, 1, 1));

            // Credencial1
            Credencial c1 = new Credencial();
            c1.setEmail("ana@mail.com");
            c1.setSenha("123456");

            // Telefone1
            Telefone t1 = new Telefone();
            t1.setDdd((byte) 38);
            t1.setNumero(12341234);
            
            // Telefone2
            Telefone t2 = new Telefone();
            t2.setDdd((byte) 38);
            t2.setNumero(43214321);
            
            // Telefone3
            Telefone t3 = new Telefone();
            t3.setDdd((byte) 44);
            t3.setNumero(78947894);
            
            // Endereco1
            Endereco e1 = new Endereco();
            e1.setLogradouro("Rua Dois");
            e1.setBairro("Village do Lago I");
            e1.setNumero(300);
            
            // Endereco2
            Endereco e2 = new Endereco();
            e2.setLogradouro("Rua Três");
            e2.setBairro("Village do Lago II");
            e2.setNumero(900);

            // Pessoa1 <-> Credencial
            p1.setCredencial(c1);
            c1.setPessoa(p1);
            
            // Pessoa1 <-> Telefone1, Telefone2 e Telefone3
            p1.getTelefones().add(t1);
            t1.setPessoa(p1);
            p1.getTelefones().add(t2);
            t2.setPessoa(p1);
            p1.getTelefones().add(t3);
            t3.setPessoa(p1);
            
            // Pessoa1 <-> Endereco1 e Endereco2
            p1.getEnderecos().add(e1);
            e1.getPessoas().add(p1);
            p1.getEnderecos().add(e2);
            e2.getPessoas().add(p1);
            
            // Pessoa2 <-> Endereco2
            p2.getEnderecos().add(e2);
            e2.getPessoas().add(p2);

            // Salvamento de Pessoa1 e Pessoa2
            pessoaService.salvar(p1);
            pessoaService.salvar(p2);

            // Recuperação de Pessoa1
            // --> Obervar se o Endereco2 mostra a ID de Pessoa2
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
