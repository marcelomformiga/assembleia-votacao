
package br.com.formiga.assembleia_votacao.service.business.impl;


import br.com.formiga.assembleia_votacao.domain.enumeration.VotoEnum;
import br.com.formiga.assembleia_votacao.service.AssociadoService;
import br.com.formiga.assembleia_votacao.service.PautaService;
import br.com.formiga.assembleia_votacao.service.SessaoVotantesService;
import br.com.formiga.assembleia_votacao.service.business.dto.AssociadoDTO;
import br.com.formiga.assembleia_votacao.service.business.dto.PautaDTO;
import br.com.formiga.assembleia_votacao.service.business.dto.SessaoDTO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


/**
 *
 * @author formiga
 */
@RunWith(MockitoJUnitRunner.class)
public class SessaoBusinessImplTest {
    
    @InjectMocks
    private SessaoBusinessImpl sessaoBusinessImplMock;
    
    @Mock
    private PautaService pautaServiceMock;
    
    @Mock
    private AssociadoService associadoServiceMock;
    
    @Mock
    private SessaoVotantesService sessaoVotantesServiceMock;
    
    private static final Long PAUTA_ID = 1L;
    private static final Long ASSOCIADO_ID = 10L;
    
    
    @Before
    public void setUp() {
    }

    @Test
    public void testMontarSessao_TempoEmMinutosInformado() {
        
        PautaDTO pautaDTO = PautaDTO.builder()
                .descricao("Reformas")
                .build();
        
        pautaDTO.setId(PAUTA_ID);
        
        Mockito.when(this.pautaServiceMock.recuperarPorId(PAUTA_ID)).thenReturn(pautaDTO);
        
        final SessaoDTO resposta = this.sessaoBusinessImplMock.montarSessao(PAUTA_ID, 30L);
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertEquals("[2]Objetos devem ser iguais!", resposta.getPauta(), pautaDTO);
        Assert.assertEquals("[3]Objetos devem ser iguais!", resposta.getDataHoraFim(), resposta.getDataHoraInicio().plusMinutes(30L));
        
        Mockito.verify(this.pautaServiceMock, Mockito.times(1)).recuperarPorId(PAUTA_ID);
    }
    
    @Test
    public void testMontarSessao_TempoEmMinutosPadrao() {
        
        PautaDTO pautaDTO = PautaDTO.builder()
                .descricao("Reformas")
                .build();
        
        pautaDTO.setId(PAUTA_ID);
        
        Mockito.when(this.pautaServiceMock.recuperarPorId(PAUTA_ID)).thenReturn(pautaDTO);
        
        final SessaoDTO resposta = this.sessaoBusinessImplMock.montarSessao(PAUTA_ID, null);
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertEquals("[2]Objetos devem ser iguais!", resposta.getPauta(), pautaDTO);
        Assert.assertEquals("[3]Objetos devem ser iguais!", resposta.getDataHoraFim(), resposta.getDataHoraInicio().plusMinutes(1L));
        
        Mockito.verify(this.pautaServiceMock, Mockito.times(1)).recuperarPorId(PAUTA_ID);
    }

    @Test
    public void testValidarSessaoAberta_SessaoAberta() {
        
        PautaDTO pautaDTO = PautaDTO.builder()
                .descricao("Reformas")
                .build();
        
        final AssociadoDTO associadoDTO1 = AssociadoDTO.builder()
                .nome("Marcelo")
                .build();
        
        final AssociadoDTO associadoDTO2 = AssociadoDTO.builder()
                .nome("Formiga")
                .build();
        
        final List<AssociadoDTO> associados = Arrays.asList(associadoDTO1, associadoDTO2);
        
        final LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        final SessaoDTO sessaoDTO = SessaoDTO.builder()
                .pauta(pautaDTO)
                .dataHoraInicio(dataHoraAtual)
                .dataHoraFim(dataHoraAtual.plusMinutes(60))
                .totalVotosSim(10)
                .totalVotosNao(5)
                .associadosVotantes(associados)
                .build();
        
        final Boolean resposta = this.sessaoBusinessImplMock.validarSessaoAberta(sessaoDTO);
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertTrue("[2]Resposta deve ser verdadeira!", resposta);
    }
    
    @Test
    public void testValidarSessaoAberta_SessaoFechada() {
        
        PautaDTO pautaDTO = PautaDTO.builder()
                .descricao("Reformas")
                .build();
        
        final AssociadoDTO associadoDTO1 = AssociadoDTO.builder()
                .nome("Marcelo")
                .build();
        
        final AssociadoDTO associadoDTO2 = AssociadoDTO.builder()
                .nome("Formiga")
                .build();
        
        final List<AssociadoDTO> associados = Arrays.asList(associadoDTO1, associadoDTO2);
        
        final LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        final SessaoDTO sessaoDTO = SessaoDTO.builder()
                .pauta(pautaDTO)
                .dataHoraInicio(dataHoraAtual.minusMinutes(5))
                .dataHoraFim(dataHoraAtual)
                .totalVotosSim(10)
                .totalVotosNao(5)
                .associadosVotantes(associados)
                .build();
        
        final Boolean resposta = this.sessaoBusinessImplMock.validarSessaoAberta(sessaoDTO);
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertFalse("[2]Resposta deve ser falsa!", resposta);
    }

    @Test
    public void testValidarAssociadoAptoParaVotar_AssociadoApto() {
        
        PautaDTO pautaDTO = PautaDTO.builder()
                .descricao("Reformas")
                .build();
        
        final LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        final SessaoDTO sessaoDTO = SessaoDTO.builder()
                .pauta(pautaDTO)
                .dataHoraInicio(dataHoraAtual)
                .dataHoraFim(dataHoraAtual.plusMinutes(60))
                .totalVotosSim(10)
                .totalVotosNao(5)
                .build();
        
        Mockito.when(this.sessaoVotantesServiceMock.verificarSeAssociadoExisteNaSessao(sessaoDTO, ASSOCIADO_ID)).thenReturn(Boolean.FALSE);
        
        final Boolean resposta = this.sessaoBusinessImplMock.validarAssociadoAptoParaVotar(sessaoDTO, ASSOCIADO_ID);
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertTrue("[2]Resposta deve ser verdadeira!", resposta);
        
        Mockito.verify(this.sessaoVotantesServiceMock, Mockito.times(1)).verificarSeAssociadoExisteNaSessao(sessaoDTO, ASSOCIADO_ID);
    }
    
    @Test
    public void testValidarAssociadoAptoParaVotar_AssociadoNaoApto() {
        
        PautaDTO pautaDTO = PautaDTO.builder()
                .descricao("Reformas")
                .build();
        
        final LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        final SessaoDTO sessaoDTO = SessaoDTO.builder()
                .pauta(pautaDTO)
                .dataHoraInicio(dataHoraAtual)
                .dataHoraFim(dataHoraAtual.plusMinutes(60))
                .totalVotosSim(10)
                .totalVotosNao(5)
                .build();
        
        Mockito.when(this.sessaoVotantesServiceMock.verificarSeAssociadoExisteNaSessao(sessaoDTO, ASSOCIADO_ID)).thenReturn(Boolean.TRUE);
        
        final Boolean resposta = this.sessaoBusinessImplMock.validarAssociadoAptoParaVotar(sessaoDTO, ASSOCIADO_ID);
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertFalse("[2]Resposta deve ser falsa!", resposta);
        
        Mockito.verify(this.sessaoVotantesServiceMock, Mockito.times(1)).verificarSeAssociadoExisteNaSessao(sessaoDTO, ASSOCIADO_ID);
    }

    @Test
    public void testMontarSessaoParaVoto_VotoSIM() {
        
        PautaDTO pautaDTO = PautaDTO.builder()
                .descricao("Reformas")
                .build();
        
        final AssociadoDTO associadoDTO = AssociadoDTO.builder()
                .nome("Marcelo")
                .build();
        
        final LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        final SessaoDTO sessaoDTO = SessaoDTO.builder()
                .pauta(pautaDTO)
                .dataHoraInicio(dataHoraAtual.minusMinutes(5))
                .dataHoraFim(dataHoraAtual)
                .totalVotosSim(10)
                .totalVotosNao(5)
                .associadosVotantes(new ArrayList<>())
                .build();
        
        Mockito.when(this.associadoServiceMock.recuperarPorId(ASSOCIADO_ID)).thenReturn(associadoDTO);
        
        final SessaoDTO resposta = this.sessaoBusinessImplMock.montarSessaoParaVoto(sessaoDTO, ASSOCIADO_ID, VotoEnum.SIM);
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertEquals("[2]Objetos devem ser iguais!", resposta.getPauta(), sessaoDTO.getPauta());
        Assert.assertEquals("[3]Objetos devem ser iguais!", resposta.getDataHoraInicio(), sessaoDTO.getDataHoraInicio());
        Assert.assertEquals("[4]Objetos devem ser iguais!", resposta.getDataHoraFim(), sessaoDTO.getDataHoraFim());
        Assert.assertEquals("[5]Objetos devem ser iguais!", resposta.getTotalVotosSim(), Integer.valueOf(11));
        Assert.assertEquals("[6]Objetos devem ser iguais!", resposta.getTotalVotosNao(), sessaoDTO.getTotalVotosNao());
        Assert.assertEquals("[7]Objetos devem ser iguais!", resposta.getAssociadosVotantes().get(0), associadoDTO);
        
        Mockito.verify(this.associadoServiceMock, Mockito.times(1)).recuperarPorId(ASSOCIADO_ID);
    }
    
    @Test
    public void testMontarSessaoParaVoto_VotoNAO() {
        
        PautaDTO pautaDTO = PautaDTO.builder()
                .descricao("Reformas")
                .build();
        
        final AssociadoDTO associadoDTO = AssociadoDTO.builder()
                .nome("Marcelo")
                .build();
        
        final LocalDateTime dataHoraAtual = LocalDateTime.now();
        
        final SessaoDTO sessaoDTO = SessaoDTO.builder()
                .pauta(pautaDTO)
                .dataHoraInicio(dataHoraAtual.minusMinutes(5))
                .dataHoraFim(dataHoraAtual)
                .totalVotosSim(10)
                .totalVotosNao(5)
                .associadosVotantes(new ArrayList<>())
                .build();
        
        Mockito.when(this.associadoServiceMock.recuperarPorId(ASSOCIADO_ID)).thenReturn(associadoDTO);
        
        final SessaoDTO resposta = this.sessaoBusinessImplMock.montarSessaoParaVoto(sessaoDTO, ASSOCIADO_ID, VotoEnum.NAO);
        
        Assert.assertNotNull("[1]Resposta não pode ser nula!", resposta);
        Assert.assertEquals("[2]Objetos devem ser iguais!", resposta.getPauta(), sessaoDTO.getPauta());
        Assert.assertEquals("[3]Objetos devem ser iguais!", resposta.getDataHoraInicio(), sessaoDTO.getDataHoraInicio());
        Assert.assertEquals("[4]Objetos devem ser iguais!", resposta.getDataHoraFim(), sessaoDTO.getDataHoraFim());
        Assert.assertEquals("[5]Objetos devem ser iguais!", resposta.getTotalVotosSim(), sessaoDTO.getTotalVotosSim());
        Assert.assertEquals("[6]Objetos devem ser iguais!", resposta.getTotalVotosNao(), Integer.valueOf(6));
        Assert.assertEquals("[7]Objetos devem ser iguais!", resposta.getAssociadosVotantes().get(0), associadoDTO);
        
        Mockito.verify(this.associadoServiceMock, Mockito.times(1)).recuperarPorId(ASSOCIADO_ID);
    }
    
}