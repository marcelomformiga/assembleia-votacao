
package br.com.formiga.assembleia_votacao.service.business.mapper;


import br.com.formiga.assembleia_votacao.domain.model.SessaoVotantes;
import br.com.formiga.assembleia_votacao.service.business.dto.SessaoVotantesDTO;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


/**
 *
 * @author formiga
 */
@Component(value = "sessaoVotantesMapper")
public class SessaoVotantesMapper implements GenericMapper<SessaoVotantes, SessaoVotantesDTO> {

    private final ModelMapper MAPPER;
    
    
    public SessaoVotantesMapper() {
        this.MAPPER = new ModelMapper();
    }
    
    
    @Override
    public SessaoVotantesDTO converterEntidadeParaDTO(final SessaoVotantes entidade) {
        return MAPPER.map(entidade, SessaoVotantesDTO.class);
    }

    @Override
    public SessaoVotantes converterDTOParaEntidade(final SessaoVotantesDTO dto) {
        return this.MAPPER.map(dto, SessaoVotantes.class);
    }

    @Override
    public List<SessaoVotantesDTO> converterListaEntidadeParaListaDTO(final List<SessaoVotantes> lista) {
        
        List<SessaoVotantesDTO> sessoesVotantes = new ArrayList<>();
        
        lista.stream().map((entidade) -> this.converterEntidadeParaDTO(entidade)).forEachOrdered((sessaoVotantes) -> {
            sessoesVotantes.add(sessaoVotantes);
        });
        
        return sessoesVotantes;
    }

    @Override
    public List<SessaoVotantes> converterListaDTOParaListaEntidade(final List<SessaoVotantesDTO> lista) {
        
        List<SessaoVotantes> sessoesVotantes = new ArrayList<>();
        
        lista.stream().map((dto) -> this.converterDTOParaEntidade(dto)).forEachOrdered((sessaoVotantes) -> {
            sessoesVotantes.add(sessaoVotantes);
        });
        
        return sessoesVotantes;
    }
    
}