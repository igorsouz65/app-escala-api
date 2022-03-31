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
import igor.escalaspring.model.Local;
import igor.escalaspring.repository.LocalRepository;

@RestController
@RequestMapping("v1")
public class LocalEndpoint {

	private final LocalRepository localDAO;

	@Autowired
	public LocalEndpoint(LocalRepository localDAO) {
		super();
		this.localDAO = localDAO;
	}

	//@GetMapping(path = "protected/local")
	@GetMapping(path = "/local")
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(localDAO.findAll(Sort.by("id").ascending()), HttpStatus.OK);
	}

	//@GetMapping(path = "protected/local/{id}")
	@GetMapping(path = "/local/{id}")
	public ResponseEntity<?> getPersonById(@PathVariable("id") Long id, Authentication authentication) {
		System.out.println(authentication);
		Optional<Local> local = localDAO.findById(id);
		verifyIfLocalExists(id);
		return new ResponseEntity<>(local, HttpStatus.OK);
	}
	
	//@GetMapping(path = "protected/local/findByNome/{nome}")
	@GetMapping(path = "/local/findByNome/{nome}")
	public ResponseEntity<?> findPersonByNome(@PathVariable String nome){
		return new ResponseEntity<>(localDAO.findByNomeIgnoreCaseContaining(nome), HttpStatus.OK);
	}

//	@PostMapping(path = "admin/local")
	@PostMapping(path = "/local")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<?> save(@Valid @RequestBody Local local) {
		return new ResponseEntity<>(localDAO.save(local), HttpStatus.CREATED);
	}

//	@DeleteMapping(path = "admin/local/{id}")
	@DeleteMapping(path = "/local/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		verifyIfLocalExists(id);
		localDAO.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

//	@PutMapping(path = "admin/local")
	@PutMapping(path = "/local")
	public ResponseEntity<?> update(@RequestBody Local local) {
		verifyIfLocalExists(local.getId());
		localDAO.save(local);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	private void verifyIfLocalExists(Long id) {

		if (!localDAO.findById(id).isPresent())
			throw new ResourceNotFoundException("Pessoa not found for ID: " + id);
	}
	
}
