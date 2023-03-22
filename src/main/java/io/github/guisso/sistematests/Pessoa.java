package io.github.guisso.sistematests;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Entity
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = false)
    private String nome;

    private LocalDate nascimento;

    @OneToOne(cascade = CascadeType.ALL)
    private Credencial credencial;

    // One-to-One Uni
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "pessoa_id")
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "pessoa")
    private List<Telefone> telefones;

    @ManyToMany(cascade = CascadeType.ALL)
            // TODO Por que gera falha "multiple bags"
            // SOLUÇÃO @Transactional no Servlet
            // fetch = FetchType.EAGER)
    @JoinTable(
//            name = "tbl_123456_xyz",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "endereco_id")
    )
    private List<Endereco> enderecos;

    @Transient
    private Byte idade;

    public Pessoa() {
        telefones = new ArrayList<>();
        enderecos = new ArrayList<>();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        idade = (byte) nascimento.until(LocalDate.now(), ChronoUnit.YEARS);
        System.out.println("Idade >> " + idade);
    }

    public Byte getIdade() {
        return idade;
    }

    public Credencial getCredencial() {
        return credencial;
    }

    public void setCredencial(Credencial Credencial) {
        this.credencial = Credencial;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode/equals/toString">
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pessoa)) {
            return false;
        }
//        Pessoa other = (Pessoa) object;
//        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
//            return false;
//        }
//        return true;
        return hashCode() == object.hashCode();
    }

    @Override
    public String toString() {
        return "Pessoa{"
                + "id=" + id
                + ", nome=" + nome
                + ", nascimento=" + nascimento
                + ", credencial=" + credencial
                + ", idade=" + idade
                + ", telefones=" + telefones
                + ", enderecos=" + enderecos
                + '}';
    }
    //</editor-fold>

}
