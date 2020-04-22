
package br.com.formiga.assembleia_votacao.service.business;


import br.com.formiga.assembleia_votacao.domain.enumeration.VotoEnum;
import br.com.formiga.assembleia_votacao.service.business.dto.SessaoDTO;


/**
 *
 * @author formiga
 */
public interface SessaoBusiness {
    
    abstract SessaoDTO montarSessao(final Long pautaId, final Long tempoEmMinutos);
    
    abstract Boolean validarSessaoAberta(final SessaoDTO sessaoDTO);
    
    abstract Boolean validarAssociadoAptoParaVotar(final SessaoDTO sessaoDTO, final Long associadoId);

    abstract SessaoDTO montarSessaoParaVoto(final SessaoDTO sessaoDTO, final Long associadoId, final VotoEnum voto);
    
}