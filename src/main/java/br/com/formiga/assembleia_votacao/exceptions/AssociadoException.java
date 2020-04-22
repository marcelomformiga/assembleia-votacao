
package br.com.formiga.assembleia_votacao.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 *
 * @author formiga
 */
@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class AssociadoException extends RuntimeException {
    
    public AssociadoException() {
        super("Associado Exception!");
    }
    
    public AssociadoException(String mensagem, Exception exception) {
        super(mensagem, exception);
    }
    
}