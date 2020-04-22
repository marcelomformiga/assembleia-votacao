
package br.com.formiga.assembleia_votacao.service;


import br.com.formiga.assembleia_votacao.service.business.dto.SessaoDTO;
import br.com.formiga.assembleia_votacao.service.business.dto.SessaoVotantesDTO;


/**
 *
 * @author formiga
 */
public interface SessaoVotantesService extends GenericService<SessaoVotantesDTO> {
    
    abstract SessaoVotantesDTO recuperarPorSessaoPorAssociado(final SessaoDTO sessaoDTO, final Long associadoId);
    
    abstract Boolean verificarSeAssociadoExisteNaSessao(final SessaoDTO sessaoDTO, final Long associadoId);
}