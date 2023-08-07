package igor.escalaspring.dto.mapper;

import igor.escalaspring.dto.EscalaDTO;
import igor.escalaspring.dto.PessoaDTO;
import igor.escalaspring.model.Escala;
import igor.escalaspring.model.Pessoa;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EscalaMapper {
    public EscalaDTO toDTO(Escala escala) {
        if(escala == null){
            return null;
        }
        List<PessoaDTO> pessoas = escala.getPessoas().stream().map(pessoa -> new PessoaDTO(pessoa.getId(),
                pessoa.getNome(), pessoa.getDataNascimento(), pessoa.getIdade(), pessoa.getTelefone(),
                pessoa.getEndereco())).toList();
        return new EscalaDTO(escala.getId(), escala.getNome(), escala.getData(), pessoas);
    }



    public Escala toEntity(EscalaDTO escalaDTO){

        if(escalaDTO == null){
            return null;
        }
        Escala escala = new Escala();
        if(escalaDTO.id() != null){
            escala.setId(escalaDTO.id());
        }
        escala.setNome(escalaDTO.nome());
        escala.setData(escalaDTO.data());

        escalaDTO.pessoas().stream().map(pessoaDTO -> {
            var pessoa = new Pessoa();
            pessoa.setId(pessoaDTO.id());

            return null;
        });

        return escala;
    }
}
