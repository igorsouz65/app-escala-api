package igor.escalaspring.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import igor.escalaspring.model.Escala;

public interface EscalaRepository  extends PagingAndSortingRepository<Escala, Long>{
	List<Escala> findByNomeIgnoreCaseContaining(String nome);
}
