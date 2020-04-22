
package br.com.formiga.assembleia_votacao.service.business.mapper;


import java.util.List;


/**
 *
 * @author formiga
 * @param <T>
 * @param <S>
 */
public interface GenericMapper<T, S> {
    
    S converterEntidadeParaDTO(final T t);

    T converterDTOParaEntidade(final S s);

    List<S> converterListaEntidadeParaListaDTO(final List<T> t);

    List<T> converterListaDTOParaListaEntidade(final List<S> s);
    
}