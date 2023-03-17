package io.github.guisso.sistematests;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Stateless
public class PessoaService implements PessoaServiceLocal {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void salvar(Pessoa pessoa) {
        entityManager.persist(pessoa);
    }

    @Override
    public Pessoa localizarPorId(Long id) {
        return entityManager.find(Pessoa.class, id);
    }

}
