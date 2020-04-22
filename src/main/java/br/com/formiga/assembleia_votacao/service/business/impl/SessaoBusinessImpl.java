
package br.com.formiga.assembleia_votacao.service.business.impl;


import br.com.formiga.assembleia_votacao.domain.enumeration.VotoEnum;
import br.com.formiga.assembleia_votacao.service.AssociadoService;
import br.com.formiga.assembleia_votacao.service.PautaService;
import br.com.formiga.assembleia_votacao.service.SessaoVotantesService;
import br.com.formiga.assembleia_votacao.service.business.SessaoBusiness;
import br.com.formiga.assembleia_votacao.service.business.dto.AssociadoDTO;
import br.com.formiga.assembleia_votacao.service.business.dto.PautaDTO;
import br.com.formiga.assembleia_votacao.service.business.dto.SessaoDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author formiga
 */
@Service(value = "sessaoBusiness")
public class SessaoBusinessImpl implements SessaoBusiness {

    @Autowired
    private PautaService pautaService;
    
    @Autowired
    private AssociadoService associadoService;
    
    @Autowired
    private SessaoVotantesService sessaoVotantesService;
    
    
    @Override
    public SessaoDTO montarSessao(final Long pautaId, final Long tempoEmMinutos) {
        
        final PautaDTO pautaDTO = this.pautaService.recuperarPorId(pautaId);
        
        final LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        return SessaoDTO.builder()
                .pauta(pautaDTO)
                .dataHoraInicio(dataHoraAtual)
                .dataHoraFim((Objects.isNull(tempoEmMinutos)) ? dataHoraAtual.plusMinutes(1L) : dataHoraAtual.plusMinutes(tempoEmMinutos))
                .totalVotosSim(0)
                .totalVotosNao(0)
                .associadosVotantes(new ArrayList<>())
                .build();
    }
    
    @Override
    public Boolean validarSessaoAberta(final SessaoDTO sessaoDTO) {
        
        final LocalDateTime horaAtual = LocalDateTime.now();
        
        if ((horaAtual.isBefore(sessaoDTO.getDataHoraInicio())) || (horaAtual.isAfter(sessaoDTO.getDataHoraFim()))) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
    
    @Override
    public Boolean validarAssociadoAptoParaVotar(final SessaoDTO sessaoDTO, final Long associadoId) {
        
        if (this.sessaoVotantesService.verificarSeAssociadoExisteNaSessao(sessaoDTO, associadoId)) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
    
    @Override
    public SessaoDTO montarSessaoParaVoto(final SessaoDTO sessaoDTO, final Long associadoId, final VotoEnum votoEnum) {
        
        SessaoDTO sessaoParaSalvar = sessaoDTO;
        
        final AssociadoDTO associadoDTO = this.associadoService.recuperarPorId(associadoId);
        
        sessaoParaSalvar.getAssociadosVotantes().add(associadoDTO);
        
        final String voto = votoEnum.name();
        
        if (VotoEnum.SIM.name().equals(voto)) {
            sessaoParaSalvar.setTotalVotosSim(sessaoDTO.getTotalVotosSim() + 1);
        } else {
            sessaoParaSalvar.setTotalVotosNao(sessaoDTO.getTotalVotosNao() + 1);
        }
        
        return sessaoParaSalvar;
    }
    
}