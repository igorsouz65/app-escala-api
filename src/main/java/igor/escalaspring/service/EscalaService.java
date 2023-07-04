package igor.escalaspring.service;

import java.util.Optional;

import igor.escalaspring.error.ResourceNotFoundException;
import igor.escalaspring.model.Local;
import igor.escalaspring.repository.LocalRepository;
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

	@Autowired
	private LocalRepository localDAO;
	
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

	public Escala adicionarLocalEscala(long escalas_id, long local_id) {

		Optional<Escala> escalaExistente = escalaDAO.findById(escalas_id);
		Optional<Local> localExistente = localDAO.findById(local_id);

		if(escalaExistente.isPresent() && localExistente.isPresent()) {
			escalaExistente.get().getLocal().add(localExistente.get());
			escalaDAO.save(escalaExistente.get());

			return escalaDAO.save(escalaExistente.get());
		}

		return null;
	}

	public Escala removerLocalEscala(long escalas_id, long local_id){
		Optional<Escala> escalaExistente = escalaDAO.findById(escalas_id);
		Optional<Local> localExistente = localDAO.findById(local_id);

		if(escalaExistente.isPresent() && localExistente.isPresent()) {
			escalaExistente.get().getLocal().remove(localExistente.get());
			escalaDAO.save(escalaExistente.get());

			return escalaDAO.save(escalaExistente.get());
		}

		return null;

	}

	public Escala removerPessoalEscala(long escalas_id, long pessoas_id){
		Optional<Escala> escalaExistente = escalaDAO.findById(escalas_id);
		Optional<Pessoa> pessoaExistente = pessoaDAO.findById(pessoas_id);

		if(escalaExistente.isPresent() && pessoaExistente.isPresent()) {
			escalaExistente.get().getPessoas().remove(pessoaExistente.get());
			escalaDAO.save(escalaExistente.get());

			return escalaDAO.save(escalaExistente.get());
		}

		return null;

	}

	public void verifyIfEscalaExists(Long id) {

		if (!escalaDAO.findById(id).isPresent())
			throw new ResourceNotFoundException("Escala not found for ID: " + id);
	}
	
}
