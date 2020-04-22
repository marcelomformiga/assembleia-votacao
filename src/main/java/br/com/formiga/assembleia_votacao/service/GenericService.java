
package br.com.formiga.assembleia_votacao.service;


import java.util.List;


/**
 *
 * @author formiga
 * @param <S>
 */
public interface GenericService<S> {
    
    S salvar(S s);
    
    void excluir(final Long id);

    List<S> listar();
    
    S recuperarPorId(final Long id);
    
}