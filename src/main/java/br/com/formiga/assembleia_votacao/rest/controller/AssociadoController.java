
package br.com.formiga.assembleia_votacao.rest.controller;


import br.com.formiga.assembleia_votacao.service.AssociadoService;
import br.com.formiga.assembleia_votacao.service.business.dto.AssociadoDTO;
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
@RestController(value = "associadoController")
@RequestMapping(path = "/assembleia")
public class AssociadoController {
    
    @Autowired
    private AssociadoService service;
    
    
    @PostMapping(path = "/associado")
    public ResponseEntity<AssociadoDTO> criar(@RequestBody AssociadoDTO dto) {
        return new ResponseEntity<>(this.service.salvar(dto), HttpStatus.CREATED);
    }
    
    @PutMapping(path = "/associado")
    public ResponseEntity<AssociadoDTO> atualizar(@RequestBody AssociadoDTO dto) {
        return new ResponseEntity<>(this.service.salvar(dto), HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/associado/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        this.service.excluir(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @GetMapping(path = "/associado")
    public ResponseEntity<List<AssociadoDTO>> listar() {
        return new ResponseEntity<>(this.service.listar(), HttpStatus.OK);
    }
    
    @GetMapping(path = "/associado/{id}")
    public ResponseEntity<AssociadoDTO> recuperarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.recuperarPorId(id), HttpStatus.OK);
    }
    
}