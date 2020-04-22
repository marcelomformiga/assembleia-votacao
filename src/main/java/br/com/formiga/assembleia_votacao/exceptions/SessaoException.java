
package br.com.formiga.assembleia_votacao.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 *
 * @author formiga
 */
@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class SessaoException extends RuntimeException {
    
    public SessaoException() {
        super("Sessao Exception!");
    }
    
    public SessaoException(String mensagem, Exception exception) {
        super(mensagem, exception);
    }
    
}