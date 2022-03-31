package igor.escalaspring.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import igor.escalaspring.model.Pessoa;

public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Long>{
	List<Pessoa> findByNomeIgnoreCaseContaining(String nome);
}
