
package br.com.formiga.assembleia_votacao.service.business.mapper;


import br.com.formiga.assembleia_votacao.domain.model.Sessao;
import br.com.formiga.assembleia_votacao.service.business.dto.SessaoDTO;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


/**
 *
 * @author formiga
 */
@Component(value = "sessaoMapper")
public class SessaoMapper implements GenericMapper<Sessao, SessaoDTO> {
    
    private final ModelMapper MAPPER;
    
    
    public SessaoMapper() {
        this.MAPPER = new ModelMapper();
    }
    
    
    @Override
    public SessaoDTO converterEntidadeParaDTO(final Sessao entidade) {
        return MAPPER.map(entidade, SessaoDTO.class);
    }

    @Override
    public Sessao converterDTOParaEntidade(final SessaoDTO dto) {
        return this.MAPPER.map(dto, Sessao.class);
    }

    @Override
    public List<SessaoDTO> converterListaEntidadeParaListaDTO(final List<Sessao> lista) {
        
        List<SessaoDTO> sessoes = new ArrayList<>();
        
        lista.stream().map((entidade) -> this.converterEntidadeParaDTO(entidade)).forEachOrdered((sessao) -> {
            sessoes.add(sessao);
        });
        
        return sessoes;
    }

    @Override
    public List<Sessao> converterListaDTOParaListaEntidade(final List<SessaoDTO> lista) {
        
        List<Sessao> sessoes = new ArrayList<>();
        
        lista.stream().map((dto) -> this.converterDTOParaEntidade(dto)).forEachOrdered((sessao) -> {
            sessoes.add(sessao);
        });
        
        return sessoes;
    }
    
}