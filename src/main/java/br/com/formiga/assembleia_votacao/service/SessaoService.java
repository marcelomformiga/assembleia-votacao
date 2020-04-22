
package br.com.formiga.assembleia_votacao.service;


import br.com.formiga.assembleia_votacao.rest.request.CriarSessaoRequest;
import br.com.formiga.assembleia_votacao.rest.request.VotarRequest;
import br.com.formiga.assembleia_votacao.service.business.dto.SessaoDTO;


/**
 *
 * @author formiga
 */
public interface SessaoService extends GenericService<SessaoDTO> {
    
    abstract SessaoDTO criar(final CriarSessaoRequest request);
    
    abstract SessaoDTO votar(final VotarRequest request);
}