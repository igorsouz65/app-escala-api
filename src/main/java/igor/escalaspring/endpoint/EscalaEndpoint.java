package igor.escalaspring.endpoint;

import igor.escalaspring.dto.EscalaDTO;
import igor.escalaspring.service.EscalaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Validated
@RestController
@RequestMapping("v1")
@Api(value="Escala API")
public class EscalaEndpoint {

	@Autowired
	private EscalaService escalaService;


	//-------------GET METHODS--------------

	@GetMapping(path = "escalas")
	@ApiOperation(value = "Retorna uma lista de escalas")
	public ResponseEntity<List<EscalaDTO>> listAll() {
		return new ResponseEntity<>(escalaService.findAllEscalas(), HttpStatus.OK);
	}

	@GetMapping(path = "escalas/{id}")
	@ApiOperation(value = "Retorna uma única escala")
	public ResponseEntity<EscalaDTO> get(@PathVariable @NotNull @Positive Long id) {
		return new ResponseEntity<>(escalaService.findEscalaById(id), HttpStatus.OK);
	}

	@GetMapping(path = "/escalas/findByNome/{nome}")
	@ApiOperation(value = "Retorna escalas pelo nome")
	public ResponseEntity<List<EscalaDTO>> findEscalaByNome(@PathVariable String nome) {
		return new ResponseEntity<>(
				escalaService.findEscalasByNome(nome),
				HttpStatus.OK);
	}

	@GetMapping(path = "/escalas/findByDate/{dataInicio}/{dataFim}")
	@ApiOperation(value = "Retorna as escalas dentro de um intervalo de datas")
	public ResponseEntity<List<EscalaDTO>> findEscalaByData(
			@PathVariable Date dataInicio,
			@PathVariable Date dataFim) {
		return new ResponseEntity<>(
				escalaService.findEscalasByData(dataInicio, dataFim),
				HttpStatus.OK);
	}

	//-------------POST METHODS--------------

//	@PostMapping(path = "admin/escalas")
	@PostMapping(path = "/escalas")
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation(value="Salva uma escala")
	public ResponseEntity<EscalaDTO> save(@Valid @RequestBody EscalaDTO escala) {
		return new ResponseEntity<>(escalaService.saveEscala(escala), HttpStatus.CREATED);
	}


	//-------------PUT METHODS--------------


//	@PutMapping(path = "admin/escalas")
	@PutMapping(path = "/escalas")
	@ApiOperation(value="Atualiza uma escala")
	public ResponseEntity<EscalaDTO> update(@RequestBody EscalaDTO escala) {
		escalaService.updateEscala(escala);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@PutMapping(path = "/escalas/{escalasId}/pessoa/{pessoasId}")
	@ApiOperation(value="Adiciona uma pessoa a uma escala")
	public ResponseEntity<EscalaDTO> addPessoaEscala(@PathVariable @NotNull @Positive Long escalasId, @PathVariable @NotNull @Positive Long pessoasId) {
		escalaService.adicionarPessoaEscala(escalasId, pessoasId);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@PutMapping(path = "/escalas/{escalas_id}/local/{local_id}")
	@ApiOperation(value="Adiciona um local a uma escala")
	public ResponseEntity<EscalaDTO> addLocalEscala(@PathVariable @NotNull @Positive Long escalasId, @PathVariable @NotNull @Positive Long localId) {
		escalaService.adicionarLocalEscala(escalasId, localId);
		return new ResponseEntity<>(HttpStatus.OK);

	}


	//-------------DELETE METHODS--------------


	@DeleteMapping(path = "/escalas/{id}")
	@ApiOperation(value="Remove uma escala")
	public ResponseEntity<HttpStatus> delete(@PathVariable @NotNull @Positive Long id){
		escalaService.deleteEscala(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}


	@DeleteMapping(path = "/escalas/{escalas_id}/local/{local_id}")
	@ApiOperation(value="Remove um local de uma escala")
	public ResponseEntity<HttpStatus> removeLocalEscala(@PathVariable @NotNull @Positive Long escalasId, @PathVariable @NotNull @Positive Long localId) {
		escalaService.removerLocalEscala(escalasId, localId);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@DeleteMapping(path = "/escalas/{escalasId}/pessoa/{pessoasId}")
	@ApiOperation(value="Remove uma pessoa de uma escala")
	public ResponseEntity<HttpStatus> removePessoaEscala(@PathVariable @NotNull @Positive Long escalasId, @PathVariable @NotNull @Positive Long pessoasId) {
		escalaService.removerPessoalEscala(escalasId, pessoasId);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	
}
