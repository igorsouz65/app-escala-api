package igor.escalaspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import igor.escalaspring.model.Escala;

public interface EscalaRepository  extends PagingAndSortingRepository<Escala, Long>{
	
}
