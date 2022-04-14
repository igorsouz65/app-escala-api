package igor.escalaspring.endpoint;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import igor.escalaspring.error.ResourceNotFoundException;
import igor.escalaspring.model.Pessoa;
import igor.escalaspring.repository.PessoaRepository;
import igor.escalaspring.service.PessoaService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("v1")
public class PessoaEndpoint {

	private final PessoaRepository pessoaDAO;

	@Autowired
	public PessoaEndpoint(PessoaRepository pessoaDAO) {
		super();
		this.pessoaDAO = pessoaDAO;
	}
	
	private PessoaService pessoaService;

	//@GetMapping(path = "protected/pessoas")
	@GetMapping(path = "/pessoas")
	@ApiOperation(value="Retorna uma lista de pessoas")
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(pessoaDAO.findAll(Sort.by("id").ascending()), HttpStatus.OK);
	}

//	@GetMapping(path = "protected/pessoas/{id}")
	@GetMapping(path = "/pessoas/{id}")
	@ApiOperation(value="retorna uma pessoa pelo ID")
	public ResponseEntity<?> getPersonById(@PathVariable("id") Long id, Authentication authentication) {
		System.out.println(authentication);
		Optional<Pessoa> pessoa = pessoaDAO.findById(id);
		verifyIfPessoaExists(id);
		return new ResponseEntity<>(pessoa, HttpStatus.OK);
	}
	
//	@GetMapping(path = "protected/pessoas/findByNome/{nome}")
	@GetMapping(path = "/pessoas/findByNome/{nome}")
	@ApiOperation(value="Retorna uma pessoa pelo nome")
	public ResponseEntity<?> findPersonByNome(@PathVariable String nome){
		return new ResponseEntity<>(pessoaDAO.findByNomeIgnoreCaseContaining(nome), HttpStatus.OK);
	}

//	@PostMapping(path = "admin/pessoas")
	@PostMapping(path = "/pessoas")
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value="Adiciona uma pessoa")
	public ResponseEntity<?> save(@Valid @RequestBody Pessoa pessoa) {
		return new ResponseEntity<>(pessoaDAO.save(pessoa), HttpStatus.CREATED);
	}

//	@DeleteMapping(path = "admin/pessoas/{id}")
	@DeleteMapping(path = "/pessoas/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value="Exclui uma pessoa")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		verifyIfPessoaExists(id);
		pessoaDAO.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

//	@PutMapping(path = "admin/pessoas")
	@PutMapping(path = "/pessoas")
	@ApiOperation(value="Atualiza uma pessoa")
	public ResponseEntity<?> update(@RequestBody Pessoa pessoa) {
		verifyIfPessoaExists(pessoa.getId());
		pessoaDAO.save(pessoa);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path = "/pessoas/{pessoas_id}/local/{local_id}")
	@ApiOperation(value="Adiciona um local para uma pessoa")
	public ResponseEntity<?> addLocalPessoa(@PathVariable Long pessoa_id, @PathVariable Long local_id){
		pessoaService.adicionarLocalPessoa(pessoa_id, local_id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	private void verifyIfPessoaExists(Long id) {

		if (!pessoaDAO.findById(id).isPresent())
			throw new ResourceNotFoundException("Pessoa not found for ID: " + id);
	}

}
