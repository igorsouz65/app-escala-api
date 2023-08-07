package igor.escalaspring.dto;

import igor.escalaspring.model.Escala;

import java.time.LocalDateTime;
import java.util.Set;

public record PessoaDTO(
        Long id,
        String nome,
        LocalDateTime dataNascimento,
        int idade,
        String telefone,
        String endereco


) {
}
