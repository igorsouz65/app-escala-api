package igor.escalaspring.dto.mapper;

import igor.escalaspring.dto.EscalaDTO;
import igor.escalaspring.model.Escala;
import org.springframework.stereotype.Component;

@Component
public class EscalaMapper {
    public EscalaDTO toDTO(Escala escala) {
        if(escala == null){
            return null;
        }
        return new EscalaDTO(escala.getId(), escala.getNome(), escala.getData());
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
        return escala;
    }
}
