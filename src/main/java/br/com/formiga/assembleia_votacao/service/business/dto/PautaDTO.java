
package br.com.formiga.assembleia_votacao.service.business.dto;


import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


/**
 *
 * @author formiga
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(access = AccessLevel.PUBLIC)
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class PautaDTO extends GenericDTO {
    
    @NotNull(message = "[Descrição] não pode ser nula!")
    @Length(min = 1, max = 80, message = "[Descrição] deve ter entre {min} e {max} caracteres!")
    @Transient
    private String descricao;
    
}