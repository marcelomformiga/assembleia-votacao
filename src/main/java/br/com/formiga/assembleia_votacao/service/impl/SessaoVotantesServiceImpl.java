
package br.com.formiga.assembleia_votacao.service.impl;


import br.com.formiga.assembleia_votacao.domain.infrasctructure.repository.SessaoVotantesRepository;
import br.com.formiga.assembleia_votacao.domain.model.SessaoVotantes;
import br.com.formiga.assembleia_votacao.service.AssociadoService;
import br.com.formiga.assembleia_votacao.service.SessaoService;
import br.com.formiga.assembleia_votacao.service.SessaoVotantesService;
import br.com.formiga.assembleia_votacao.service.business.dto.AssociadoDTO;
import br.com.formiga.assembleia_votacao.service.business.dto.SessaoDTO;
import br.com.formiga.assembleia_votacao.service.business.dto.SessaoVotantesDTO;
import br.com.formiga.assembleia_votacao.service.business.mapper.SessaoVotantesMapper;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author formiga
 */
@Service(value = "sessaoVotantesService")
@Transactional
public class SessaoVotantesServiceImpl implements SessaoVotantesService {

    @Autowired
    private SessaoVotantesMapper mapper;
    
    @Autowired
    private SessaoVotantesRepository repository;
    
    @Autowired
    private SessaoService sessaoService;
    
    @Autowired
    private AssociadoService associadoService;
    
    
    @Override
    public SessaoVotantesDTO salvar(final SessaoVotantesDTO dto) {
        final SessaoVotantes sessaoVotantes = this.repository.save(this.mapper.converterDTOParaEntidade(dto));
        
        return this.mapper.converterEntidadeParaDTO(sessaoVotantes);
    }

    @Override
    public void excluir(final Long id) {
        final SessaoVotantesDTO sessaoVotantesDTO = this.recuperarPorId(id);
        
        if (Objects.nonNull(sessaoVotantesDTO)) {
            this.repository.delete(this.mapper.converterDTOParaEntidade(sessaoVotantesDTO));
        }
    }

    @Override
    public List<SessaoVotantesDTO> listar() {
        return this.mapper.converterListaEntidadeParaListaDTO(this.repository.findAll());
    }

    @Override
    public SessaoVotantesDTO recuperarPorId(Long id) {
        final SessaoVotantes sessaoVotantes = this.repository.findById(id).orElse(null);
        
        if (Objects.isNull(sessaoVotantes)) {
            return null;
        }
        
        return this.mapper.converterEntidadeParaDTO(sessaoVotantes);
    }

    @Override
    public SessaoVotantesDTO recuperarPorSessaoPorAssociado(final SessaoDTO sessaoDTO, final Long associadoId) {
        
        final AssociadoDTO associadoDTO = this.associadoService.recuperarPorId(associadoId);
        
        final SessaoVotantesDTO sessaoVotantesDTO = SessaoVotantesDTO.builder()
                .sessaoDTO(sessaoDTO)
                .associadoDTO(associadoDTO)
                .build();
        
        final SessaoVotantes sessaoVotantes = this.mapper.converterDTOParaEntidade(sessaoVotantesDTO);
        final SessaoVotantes sessaoVotantesParaValidar = this.repository.findBySessaoAndAssociado(sessaoVotantes.getSessao(), sessaoVotantes.getAssociado()).orElse(null);
        
        if (Objects.isNull(sessaoVotantesParaValidar)) {
            return null;
        }
        
        return this.mapper.converterEntidadeParaDTO(sessaoVotantesParaValidar);
    }
    
    @Override
    public Boolean verificarSeAssociadoExisteNaSessao(final SessaoDTO sessaoDTO, final Long associadoId) {
        
        if (Objects.nonNull(this.recuperarPorSessaoPorAssociado(sessaoDTO, associadoId))) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    
}