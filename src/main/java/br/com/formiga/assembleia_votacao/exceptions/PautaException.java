
package br.com.formiga.assembleia_votacao.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 *
 * @author formiga
 */
@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class PautaException extends RuntimeException {
    
    public PautaException() {
        super("Associado Exception!");
    }
    
    public PautaException(String mensagem, Exception exception) {
        super(mensagem, exception);
    }
    
}