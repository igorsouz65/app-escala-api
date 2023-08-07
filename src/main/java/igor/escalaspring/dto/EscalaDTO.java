package igor.escalaspring.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

public record EscalaDTO(
        Long id,
        @NotEmpty @Length(min = 5, max = 100)
        String nome,
        @NotNull Date data,
        List<PessoaDTO> pessoas) {

}
