
package br.com.formiga.assembleia_votacao.rest.controller;


import br.com.formiga.assembleia_votacao.rest.request.CriarSessaoRequest;
import br.com.formiga.assembleia_votacao.rest.request.VotarRequest;
import br.com.formiga.assembleia_votacao.service.SessaoService;
import br.com.formiga.assembleia_votacao.service.business.dto.SessaoDTO;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author formiga
 */
@RestController(value = "sessaoController")
@RequestMapping(path = "/assembleia")
public class SessaoController {
    
    @Autowired
    private SessaoService service;
    
    
    @PostMapping(path = "/sessao")
    public ResponseEntity<SessaoDTO> criar(final @Valid @RequestBody CriarSessaoRequest  request) {
        return new ResponseEntity<>(this.service.criar(request), HttpStatus.CREATED);
    }
    
    @PutMapping(path = "/sessao")
    public ResponseEntity<SessaoDTO> atualizar(@RequestBody SessaoDTO dto) {
        return new ResponseEntity<>(this.service.salvar(dto), HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/sessao/{id}")
    public ResponseEntity<Void> excluir(final @PathVariable Long id) {
        this.service.excluir(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @GetMapping(path = "/sessao")
    public ResponseEntity<List<SessaoDTO>> listar() {
        return new ResponseEntity<>(this.service.listar(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/sessao/{id}")
    public ResponseEntity<SessaoDTO> recuperarPorId(final @PathVariable Long id) {
        return new ResponseEntity<>(this.service.recuperarPorId(id), HttpStatus.OK);
    }
    
    @PostMapping(path = "/sessao/votar")
    public ResponseEntity<SessaoDTO> votar(final @Valid @RequestBody VotarRequest request) {
        return new ResponseEntity<>(this.service.votar(request), HttpStatus.OK);
    }
    
}