
package br.com.formiga.assembleia_votacao.service.impl;


import br.com.formiga.assembleia_votacao.domain.infrasctructure.repository.AssociadoRepository;
import br.com.formiga.assembleia_votacao.domain.model.Associado;
import br.com.formiga.assembleia_votacao.exceptions.AssociadoException;
import br.com.formiga.assembleia_votacao.exceptions.ExceptionMensagens;
import br.com.formiga.assembleia_votacao.service.AssociadoService;
import br.com.formiga.assembleia_votacao.service.business.dto.AssociadoDTO;
import br.com.formiga.assembleia_votacao.service.business.mapper.AssociadoMapper;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author formiga
 */
@Service(value = "associadoService")
@Transactional
public class AssociadoServiceImpl implements AssociadoService {
    
    @Autowired
    private AssociadoMapper mapper;
    
    @Autowired
    private AssociadoRepository repository;
    

    @Override
    public AssociadoDTO salvar(final AssociadoDTO dto) {
        final Associado associado = this.repository.save(this.mapper.converterDTOParaEntidade(dto));
        
        return this.mapper.converterEntidadeParaDTO(associado);
    }

    @Override
    public void excluir(final Long id) {
        final AssociadoDTO associadoDTO = this.recuperarPorId(id);
        
        if (Objects.nonNull(associadoDTO)) {
            this.repository.delete(this.mapper.converterDTOParaEntidade(associadoDTO));
        }
    }

    @Override
    public List<AssociadoDTO> listar() {
        return this.mapper.converterListaEntidadeParaListaDTO(this.repository.findAll());
    }

    @Override
    public AssociadoDTO recuperarPorId(Long id) {
        final Associado associado = this.repository.findById(id).orElseThrow(() -> new AssociadoException(ExceptionMensagens.ASSOCIADO_NAO_ENCONTRADO, null));
        
        return this.mapper.converterEntidadeParaDTO(associado);
    }
    
}