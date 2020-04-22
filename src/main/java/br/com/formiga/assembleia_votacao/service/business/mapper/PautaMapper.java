
package br.com.formiga.assembleia_votacao.service.business.mapper;


import br.com.formiga.assembleia_votacao.domain.model.Pauta;
import br.com.formiga.assembleia_votacao.service.business.dto.PautaDTO;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


/**
 *
 * @author formiga
 */
@Component(value = "pautaMapper")
public class PautaMapper implements GenericMapper<Pauta, PautaDTO> {
    
    private final ModelMapper MAPPER;


    public PautaMapper() {
        this.MAPPER = new ModelMapper();
    }    
    
    @Override
    public PautaDTO converterEntidadeParaDTO(final Pauta entidade) {
        return MAPPER.map(entidade, PautaDTO.class);
    }

    @Override
    public Pauta converterDTOParaEntidade(final PautaDTO dto) {
        return this.MAPPER.map(dto, Pauta.class);
    }

    @Override
    public List<PautaDTO> converterListaEntidadeParaListaDTO(final List<Pauta> lista) {
        
        List<PautaDTO> pautas = new ArrayList<>();
        
        lista.stream().map((entidade) -> this.converterEntidadeParaDTO(entidade)).forEachOrdered((pauta) -> {
            pautas.add(pauta);
        });
        
        return pautas;
    }

    @Override
    public List<Pauta> converterListaDTOParaListaEntidade(final List<PautaDTO> lista) {
        
        List<Pauta> pautas = new ArrayList<>();
        
        lista.stream().map((dto) -> this.converterDTOParaEntidade(dto)).forEachOrdered((pauta) -> {
            pautas.add(pauta);
        });
        
        return pautas;
    }
    
}