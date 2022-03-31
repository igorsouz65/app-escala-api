package igor.escalaspring.endpoint;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igor.escalaspring.error.ResourceNotFoundException;
import igor.escalaspring.model.Escala;
import igor.escalaspring.model.Pessoa;
import igor.escalaspring.repository.EscalaRepository;
import igor.escalaspring.repository.PessoaRepository;
import igor.escalaspring.service.EscalaService;

@RestController
@RequestMapping("v1")
public class EscalaEndpoint {

	private EscalaRepository escalaDAO;
	private PessoaRepository pessoaDAO;
	
	@Autowired
	private EscalaService escalaService;
	
	@Autowired
	public EscalaEndpoint(EscalaRepository escalaDAO, PessoaRepository pessoaDAO) {
		super();
		this.escalaDAO = escalaDAO;
		this.pessoaDAO = pessoaDAO;
	}
	
	@GetMapping(path = "escalas")
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(escalaDAO.findAll(Sort.by("id").ascending()), HttpStatus.OK);
	}
	
	@GetMapping(path = "escalas/{id}")
	public ResponseEntity<?> get(@PathVariable Long id){
		return new ResponseEntity<>(escalaDAO.findById(id), HttpStatus.OK);
	}
	
//	@PostMapping(path = "admin/escalas")
	@PostMapping(path = "/escalas")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> save(@Valid @RequestBody Escala escala) {
		return new ResponseEntity<>(escalaDAO.save(escala), HttpStatus.CREATED);
	}
	
//	@PutMapping(path = "admin/escalas")
	@PutMapping(path = "/escalas")
	public ResponseEntity<?> update(@RequestBody Escala escala) {
		verifyIfEscalaExists(escala.getId());
		escalaDAO.save(escala);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
//	@PutMapping(path = "admin/escalas/pessoaescala/{id}")
	@PutMapping(path = "/escalas/pessoaescala/{id}")
	public ResponseEntity<?> addPessoaEscala(@PathVariable Long id, @RequestBody Escala escala) {
		verifyIfEscalaExists(escala.getId());
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoaDAO.findById(id);
		pessoas.add((Pessoa)pessoaDAO.findById(id).get());
		escala.setPessoas(pessoas);
		escalaDAO.save(escala);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PutMapping(path = "/escalas/{escalas_id}/pessoa/{pessoas_id}")
	public ResponseEntity<?> add2PessoaEscala(@PathVariable Long escalas_id, @PathVariable Long pessoas_id) {
		escalaService.adicionarPessoaEscala(escalas_id, pessoas_id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	
	private void verifyIfEscalaExists(Long id) {

		if (!escalaDAO.findById(id).isPresent())
			throw new ResourceNotFoundException("Escala not found for ID: " + id);
	}
	
}