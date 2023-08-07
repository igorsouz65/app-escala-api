package igor.escalaspring.dto.mapper;

import igor.escalaspring.dto.EscalaDTO;
import igor.escalaspring.dto.PessoaDTO;
import igor.escalaspring.model.Escala;
import igor.escalaspring.model.Pessoa;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        Set<Pessoa> pessoas = escalaDTO.pessoas().stream().map(pessoaDTO -> {
            var pessoa = new Pessoa();
            pessoa.setId(pessoaDTO.id());
            pessoa.setNome(pessoaDTO.nome());
            pessoa.setDataNascimento(pessoaDTO.dataNascimento());
            pessoa.setIdade(pessoaDTO.idade());
            pessoa.setTelefone(pessoaDTO.telefone());
            pessoa.setEndereco(pessoaDTO.endereco());
            return pessoa;}).collect(Collectors.toSet());

        escala.setPessoas(pessoas);

        return escala;
    }
}
