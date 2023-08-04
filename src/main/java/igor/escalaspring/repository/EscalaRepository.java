package igor.escalaspring.repository;

import java.util.Date;
import java.util.List;

import igor.escalaspring.dto.EscalaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import igor.escalaspring.model.Escala;

public interface EscalaRepository  extends JpaRepository<Escala, Long> {
	List<Escala> findByNomeIgnoreCaseContaining(String nome);

	List<Escala> findByDataBetween(Date inicio, Date fim);



}
