package io.github.guisso.sistematests;

import javax.ejb.Local;

/**
 *
 * @author Luis Guisso &lt;luis.guisso at ifnmg.edu.br&gt;
 */
@Local
public interface PessoaServiceLocal {

    void salvar(Pessoa pessoa);

    Pessoa localizarPorId(Long id);
    
}
