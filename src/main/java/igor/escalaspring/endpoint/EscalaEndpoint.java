package igor.escalaspring.endpoint;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import igor.escalaspring.error.ResourceNotFoundException;
import igor.escalaspring.model.Escala;
import igor.escalaspring.repository.EscalaRepository;
import igor.escalaspring.repository.PessoaRepository;
import igor.escalaspring.service.EscalaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;

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

	//-------------GET METHODS--------------
	
	@GetMapping(path = "escalas")
	@ApiOperation(value="Retorna uma lista de escalas")
	public ResponseEntity<?> listAll() {
		return new ResponseEntity<>(escalaDAO.findAll(Sort.by("id").ascending()), HttpStatus.OK);
	}
	
	@GetMapping(path = "escalas/{id}")
	@ApiOperation(value="Retorna uma unica escala")
	public ResponseEntity<?> get(@PathVariable Long id){
		return new ResponseEntity<>(escalaDAO.findById(id), HttpStatus.OK);
	}

	@GetMapping(path = "/escalas/findByNome/{nome}")
	@ApiOperation(value="Retorna escalas pelo nome")
	public ResponseEntity<?> findEscalaByNome(@PathVariable String nome){
		return new ResponseEntity<>(escalaDAO.findByNomeIgnoreCaseContaining(nome), HttpStatus.OK);
	}

	@GetMapping(path = "/escalas/findByDate/{dataInicio}/{dataFim}")
	@ApiOperation(value="Retorna as escalas dentro de um intervalo de datas")
	public ResponseEntity<?> findEscalaByData(@PathVariable Date dataInicio, @PathVariable Date dataFim){
		return new ResponseEntity<>(escalaDAO.findByDataBetween(dataInicio, dataFim), HttpStatus.OK);
	}

	//-------------POST METHODS--------------

//	@PostMapping(path = "admin/escalas")
	@PostMapping(path = "/escalas")
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value="Salva uma escala")
	public ResponseEntity<?> save(@Valid @RequestBody Escala escala) {
		return new ResponseEntity<>(escalaDAO.save(escala), HttpStatus.CREATED);
	}


	//-------------PUT METHODS--------------


//	@PutMapping(path = "admin/escalas")
	@PutMapping(path = "/escalas")
	@ApiOperation(value="Atualiza uma escala")
	public ResponseEntity<?> update(@RequestBody Escala escala) {
		escalaService.verifyIfEscalaExists(escala.getId());
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
	
	@PutMapping(path = "/escalas/{escalas_id}/pessoa/{pessoas_id}")
	@ApiOperation(value="Adiciona uma pessoa a uma escala")
	public ResponseEntity<?> addPessoaEscala(@PathVariable Long escalas_id, @PathVariable Long pessoas_id) {
		escalaService.adicionarPessoaEscala(escalas_id, pessoas_id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@PutMapping(path = "/escalas/{escalas_id}/local/{local_id}")
	@ApiOperation(value="Adiciona um local a uma escala")
	public ResponseEntity<?> addLocalEscala(@PathVariable Long escalas_id, @PathVariable Long local_id) {
		escalaService.adicionarLocalEscala(escalas_id, local_id);
		return new ResponseEntity<>(HttpStatus.OK);

	}


	//-------------DELETE METHODS--------------


	@DeleteMapping(path = "/escalas/{id}")
	@ApiOperation(value="Remove uma escala")
	public ResponseEntity<?> delete(@PathVariable Long id){
		escalaService.verifyIfEscalaExists(id);
		escalaDAO.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}


	@DeleteMapping(path = "/escalas/{escalas_id}/local/{local_id}")
	@ApiOperation(value="Remove um local de uma escala")
	public ResponseEntity<?> removeLocalEscala(@PathVariable Long escalas_id, @PathVariable Long local_id) {
		escalaService.removerLocalEscala(escalas_id, local_id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@DeleteMapping(path = "/escalas/{escalas_id}/pessoa/{pessoas_id}")
	@ApiOperation(value="Remove uma pessoa de uma escala")
	public ResponseEntity<?> removePessoaEscala(@PathVariable Long escalas_id, @PathVariable Long pessoas_id) {
		escalaService.removerPessoalEscala(escalas_id, pessoas_id);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	
}
