package igor.escalaspring.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import igor.escalaspring.model.Local;

public interface LocalRepository extends PagingAndSortingRepository<Local, Long> {
	List<Local> findByNomeIgnoreCaseContaining(String nome);
}
