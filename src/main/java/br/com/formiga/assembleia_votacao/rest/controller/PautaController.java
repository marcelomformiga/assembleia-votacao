
package br.com.formiga.assembleia_votacao.rest.controller;


import br.com.formiga.assembleia_votacao.service.PautaService;
import br.com.formiga.assembleia_votacao.service.business.dto.PautaDTO;
import java.util.List;
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
@RestController(value = "pautaController")
@RequestMapping(path = "/assembleia")
public class PautaController {
    
    @Autowired
    private PautaService service;
    
    
    @PostMapping(path = "/pauta")
    public ResponseEntity<PautaDTO> criar(@RequestBody PautaDTO dto) {
        return new ResponseEntity<>(this.service.salvar(dto), HttpStatus.CREATED);
    }
    
    @PutMapping(path = "/pauta")
    public ResponseEntity<PautaDTO> atualizar(@RequestBody PautaDTO dto) {
        return new ResponseEntity<>(this.service.salvar(dto), HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/pauta/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        this.service.excluir(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @GetMapping(path = "/pauta")
    public ResponseEntity<List<PautaDTO>> listar() {
        return new ResponseEntity<>(this.service.listar(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/pauta/{id}")
    public ResponseEntity<PautaDTO> recuperarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.recuperarPorId(id), HttpStatus.OK);
    }
    
}