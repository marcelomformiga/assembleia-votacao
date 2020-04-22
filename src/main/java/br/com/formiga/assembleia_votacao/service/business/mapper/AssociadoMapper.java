
package br.com.formiga.assembleia_votacao.service.business.mapper;


import br.com.formiga.assembleia_votacao.domain.model.Associado;
import br.com.formiga.assembleia_votacao.service.business.dto.AssociadoDTO;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


/**
 *
 * @author formiga
 */
@Component(value = "associadoMapper")
public class AssociadoMapper implements GenericMapper<Associado, AssociadoDTO> {
    
    private final ModelMapper MAPPER;
    
    
    public AssociadoMapper() {
        this.MAPPER = new ModelMapper();
    }
    
    
    @Override
    public AssociadoDTO converterEntidadeParaDTO(final Associado entidade) {
        return MAPPER.map(entidade, AssociadoDTO.class);
    }

    @Override
    public Associado converterDTOParaEntidade(final AssociadoDTO dto) {
        return this.MAPPER.map(dto, Associado.class);
    }

    @Override
    public List<AssociadoDTO> converterListaEntidadeParaListaDTO(final List<Associado> lista) {
        
        List<AssociadoDTO> associados = new ArrayList<>();
        
        lista.stream().map((entidade) -> this.converterEntidadeParaDTO(entidade)).forEachOrdered((associado) -> {
            associados.add(associado);
        });
        
        return associados;
    }

    @Override
    public List<Associado> converterListaDTOParaListaEntidade(final List<AssociadoDTO> lista) {
        
        List<Associado> associados = new ArrayList<>();
        
        lista.stream().map((dto) -> this.converterDTOParaEntidade(dto)).forEachOrdered((associado) -> {
            associados.add(associado);
        });
        
        return associados;
    }
    
}