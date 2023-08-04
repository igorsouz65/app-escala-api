package igor.escalaspring.dto.mapper;

import igor.escalaspring.dto.EscalaDTO;
import igor.escalaspring.model.Escala;
import org.springframework.stereotype.Component;

@Component
public class EscalaMapper {
    public EscalaDTO toDTO(Escala escala) {
        return new EscalaDTO(escala.getId(), escala.getNome(), escala.getData());
    }
}
