
package br.com.formiga.assembleia_votacao.service.impl;


import br.com.formiga.assembleia_votacao.domain.infrasctructure.repository.PautaRepository;
import br.com.formiga.assembleia_votacao.domain.model.Pauta;
import br.com.formiga.assembleia_votacao.exceptions.ExceptionMensagens;
import br.com.formiga.assembleia_votacao.exceptions.PautaException;
import br.com.formiga.assembleia_votacao.service.PautaService;
import br.com.formiga.assembleia_votacao.service.business.PautaBusiness;
import br.com.formiga.assembleia_votacao.service.business.dto.PautaDTO;
import br.com.formiga.assembleia_votacao.service.business.mapper.PautaMapper;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author formiga
 */
@Service(value = "pautaService")
@Transactional
public class PautaServiceImpl implements PautaService {
    
    @Autowired
    private PautaMapper mapper;
    
    @Autowired
    private PautaBusiness business;
    
    @Autowired
    private PautaRepository repository;
    
    
    @Override
    public PautaDTO salvar(final PautaDTO dto) {
        
        final Pauta pauta = this.repository.save(this.mapper.converterDTOParaEntidade(dto));
        
        return this.mapper.converterEntidadeParaDTO(pauta);
    }

    @Override
    public void excluir(final Long id) {
        
        final PautaDTO pautaDTO = this.recuperarPorId(id);
        
        if (Objects.nonNull(pautaDTO)) {
            this.repository.delete(this.mapper.converterDTOParaEntidade(pautaDTO));
        }
    }

    @Override
    public List<PautaDTO> listar() {
        return this.mapper.converterListaEntidadeParaListaDTO(this.repository.findAll());
    }

    @Override
    public PautaDTO recuperarPorId(final Long id) {
        final Pauta pauta = this.repository.findById(id).orElseThrow(() -> new PautaException(ExceptionMensagens.PAUTA_NAO_ENCONTRADA, null));

        return this.mapper.converterEntidadeParaDTO(pauta);
    }
    
}