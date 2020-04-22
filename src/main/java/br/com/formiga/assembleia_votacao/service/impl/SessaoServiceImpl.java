
package br.com.formiga.assembleia_votacao.service.impl;


import br.com.formiga.assembleia_votacao.domain.infrasctructure.repository.SessaoRepository;
import br.com.formiga.assembleia_votacao.domain.model.Sessao;
import br.com.formiga.assembleia_votacao.exceptions.ExceptionMensagens;
import br.com.formiga.assembleia_votacao.exceptions.SessaoException;
import br.com.formiga.assembleia_votacao.rest.request.CriarSessaoRequest;
import br.com.formiga.assembleia_votacao.rest.request.VotarRequest;
import br.com.formiga.assembleia_votacao.service.SessaoService;
import br.com.formiga.assembleia_votacao.service.business.SessaoBusiness;
import br.com.formiga.assembleia_votacao.service.business.dto.SessaoDTO;
import br.com.formiga.assembleia_votacao.service.business.mapper.SessaoMapper;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author formiga
 */
@Service(value = "sessaoService")
@Transactional
public class SessaoServiceImpl implements SessaoService {

    @Autowired
    private SessaoMapper mapper;
    
    @Autowired
    private SessaoBusiness business;
    
    @Autowired
    private SessaoRepository repository;

    
    @Override
    public SessaoDTO criar(final CriarSessaoRequest request) {
        return this.salvar(this.business.montarSessao(request.getPautaId(), request.getTempoEmMinutos()));
    }
    
    @Override
    public SessaoDTO salvar(final SessaoDTO dto) {
        final Sessao sessao = this.repository.save(this.mapper.converterDTOParaEntidade(dto));
        
        return this.mapper.converterEntidadeParaDTO(sessao);
    }

    @Override
    public void excluir(final Long id) {
        final SessaoDTO sessaoDTO = this.recuperarPorId(id);
        
        if (Objects.nonNull(sessaoDTO)) {
            this.repository.delete(this.mapper.converterDTOParaEntidade(sessaoDTO));
        }
    }

    @Override
    public List<SessaoDTO> listar() {
        return this.mapper.converterListaEntidadeParaListaDTO(this.repository.findAll());
    }

    @Override
    public SessaoDTO recuperarPorId(final Long id) {
        final Sessao sessao = this.repository.findById(id).orElseThrow(() -> new SessaoException(ExceptionMensagens.SESSAO_NAO_ENCONTRADA, null));
        
        return this.mapper.converterEntidadeParaDTO(sessao);
    }
    
    @Override
    public SessaoDTO votar(final VotarRequest request) {
        
        final SessaoDTO sessaoDTO = this.recuperarPorId(request.getSessaoId());
        
        if (this.business.validarSessaoAberta(sessaoDTO)) {
            
            if (this.business.validarAssociadoAptoParaVotar(sessaoDTO, request.getAssociadoId())) {
                
                return this.salvar(this.business.montarSessaoParaVoto(sessaoDTO, request.getAssociadoId(), request.getVoto()));
            } else {
                throw new SessaoException(ExceptionMensagens.ASSOCIADO_NAO_APTO, null);
            }               
        } else {
            throw new SessaoException(ExceptionMensagens.SESSAO_FECHADA, null);
        }
    }
    
}