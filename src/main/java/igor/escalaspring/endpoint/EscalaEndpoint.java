package igor.escalaspring.endpoint;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igor.escalaspring.error.ResourceNotFoundException;
import igor.escalaspring.model.Escala;
import igor.escalaspring.repository.EscalaRepository;
import igor.escalaspring.repository.PessoaRepository;
import igor.escalaspring.service.EscalaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1")
@Api(value="Escala API")
public class EscalaEndpoint {

	private EscalaRepository escalaDAO;
	
	@Autowired
	private EscalaService escalaService;
	
	@Autowired
	public EscalaEndpoint(EscalaRepository escalaDAO, PessoaRepository pessoaDAO) {
		super();
		this.escalaDAO = escalaDAO;

	}
	
	@GetMapping(path = "escalas")
	@ApiOperation(value="Retorna uma lista de escalas")
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(escalaDAO.findAll(Sort.by("id").ascending()), HttpStatus.OK);
	}
	
	@GetMapping(path = "admin/escalas/{id}")
	@ApiOperation(value="Retorna uma unica escala")
	public ResponseEntity<?> get(@PathVariable Long id){
		return new ResponseEntity<>(escalaDAO.findById(id), HttpStatus.OK);
	}
	
	@GetMapping(path = "/escalas/findByNome/{nome}")
	@ApiOperation(value="Retorna uma escala pelo nome")
	public ResponseEntity<?> findEscalaByNome(@PathVariable String nome){
		return new ResponseEntity<>(escalaDAO.findByNomeIgnoreCaseContaining(nome), HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*")
//	@PostMapping(path = "admin/escalas")
	@PostMapping(path = "/escalas")
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value="Salva uma escala")
	public ResponseEntity<?> save(@Valid @RequestBody Escala escala) {
		return new ResponseEntity<>(escalaDAO.save(escala), HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@PutMapping(path = "admin/escalas")
//	@PutMapping(path = "/escalas")
	@ApiOperation(value="Atualiza uma escala")
	public ResponseEntity<?> update(@RequestBody Escala escala) {
		verifyIfEscalaExists(escala.getId());
		escalaDAO.save(escala);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
//	@PutMapping(path = "admin/escalas/pessoaescala/{id}")
/*	@PutMapping(path = "/escalas/pessoaescala/{id}")
	public ResponseEntity<?> addPessoaEscala(@PathVariable Long id, @RequestBody Escala escala) {
		verifyIfEscalaExists(escala.getId());
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoaDAO.findById(id);
		pessoas.add((Pessoa)pessoaDAO.findById(id).get());
		escala.setPessoas(pessoas);
		escalaDAO.save(escala);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	*/
	
	@CrossOrigin
//	@PutMapping(path = "admin/escalas/{escalas_id}/pessoa/{pessoas_id}")
	@PutMapping(path = "/escalas/{escalas_id}/pessoa/{pessoas_id}")
	@ApiOperation(value="Adiciona uma pessoa uma escala")
	public ResponseEntity<?> add2PessoaEscala(@PathVariable Long escalas_id, @PathVariable Long pessoas_id) {
		escalaService.adicionarPessoaEscala(escalas_id, pessoas_id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@CrossOrigin
	@DeleteMapping(path = "admin/escalas/{id}")
	@ApiOperation(value="Exclui uma escala")
	public ResponseEntity<?> delete(@PathVariable Long id){
		verifyIfEscalaExists(id);
		escalaDAO.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	
	
	private void verifyIfEscalaExists(Long id) {

		if (!escalaDAO.findById(id).isPresent())
			throw new ResourceNotFoundException("Escala not found for ID: " + id);
	}
	
}
