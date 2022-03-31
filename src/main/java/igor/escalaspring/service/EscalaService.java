package igor.escalaspring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import igor.escalaspring.model.Escala;
import igor.escalaspring.model.Pessoa;
import igor.escalaspring.repository.EscalaRepository;
import igor.escalaspring.repository.PessoaRepository;

@Service
public class EscalaService {
	
	@Autowired
	private EscalaRepository escalaDAO;
	
	@Autowired
	private PessoaRepository pessoaDAO;
	
	public Escala adicionarPessoaEscala(long escalas_id, long pessoas_id) {
		
		Optional<Escala> escalaExistente = escalaDAO.findById(escalas_id);
		Optional<Pessoa> pessoaExistente = pessoaDAO.findById(pessoas_id);
		
		if(escalaExistente.isPresent() && pessoaExistente.isPresent()) {
			escalaExistente.get().getPessoas().add(pessoaExistente.get());
			escalaDAO.save(escalaExistente.get());
			
			return escalaDAO.save(escalaExistente.get());
		}
		
		return null;
	}
	
}
