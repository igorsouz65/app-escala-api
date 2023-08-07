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


}
