package io.github.guisso.sistematests;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Entity
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String logradouro;

    @Column(length = 80, nullable = false)
    private String bairro;

    @Column(nullable = false)
    private Integer numero;

    @ManyToMany(cascade = CascadeType.ALL,
            mappedBy = "enderecos")
    private List<Pessoa> pessoas;

    public Endereco() {
        pessoas = new ArrayList<>();
    }

    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="hashCode/equals/toString">
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return hashCode() == obj.hashCode();
    }

    @Override
    public String toString() {

//        String idsPessoas = "[ ";
//        for (Pessoa pessoa : pessoas) {
//            idsPessoas += pessoa.getId() + ", ";
//        }
//        idsPessoas += " ]";

        // Concatenação adequada de IDs por programação funcional
        String idsPessoas = pessoas.stream()
                .map(p -> Long.toString(p.getId()))
                .collect(Collectors.joining(", "));

        return "Endereco{"
                + "id=" + id
                + ", logradouro=" + logradouro
                + ", bairro=" + bairro
                + ", numero=" + numero
                + ", pessoasId=" + idsPessoas
                + '}';
    }
    //</editor-fold>

}
