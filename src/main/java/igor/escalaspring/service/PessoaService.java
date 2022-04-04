package igor.escalaspring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import igor.escalaspring.model.Local;
import igor.escalaspring.model.Pessoa;
import igor.escalaspring.repository.LocalRepository;
import igor.escalaspring.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private LocalRepository localDAO;
	
	@Autowired
	private PessoaRepository pessoaDAO;
	
	public Local adicionarLocalPessoa(long pessoas_id, long local_id) {
		
		Optional<Local> localExistente = localDAO.findById(local_id);
		Optional<Pessoa> pessoaExistente = pessoaDAO.findById(pessoas_id);
		
		if(localExistente.isPresent() && pessoaExistente.isPresent()) {
			localExistente.get().getPessoas().add(pessoaExistente.get());
			localDAO.save(localExistente.get());
			
			return localDAO.save(localExistente.get());
		}
		
		return null;
	}

}
