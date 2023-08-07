package igor.escalaspring.service;

import igor.escalaspring.dto.EscalaDTO;
import igor.escalaspring.dto.mapper.EscalaMapper;
import igor.escalaspring.error.ResourceNotFoundException;
import igor.escalaspring.model.Escala;
import igor.escalaspring.model.Local;
import igor.escalaspring.model.Pessoa;
import igor.escalaspring.repository.EscalaRepository;
import igor.escalaspring.repository.LocalRepository;
import igor.escalaspring.repository.PessoaRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EscalaService {

	private final EscalaRepository escalaDAO;

	private final EscalaMapper escalaMapper;

	private final PessoaRepository pessoaDAO;

	private final LocalRepository localDAO;

	public EscalaService(EscalaRepository escalaDAO, EscalaMapper escalaMapper, PessoaRepository pessoaDAO, LocalRepository localDAO) {
		this.escalaDAO = escalaDAO;
		this.escalaMapper = escalaMapper;
		this.pessoaDAO = pessoaDAO;
		this.localDAO = localDAO;
	}


	//-------------GET METHODS--------------

	public List<EscalaDTO> findAllEscalas() {
		return escalaDAO.findAll()
				.stream()
				.map(escalaMapper::toDTO)
				.toList();
	}

	public EscalaDTO findEscalaById( @NotNull @Positive Long id) {
		return escalaDAO.findById(id)
				.map(escalaMapper::toDTO)
				.orElseThrow(() -> new ResourceNotFoundException(id.toString()));
	}

	public List<EscalaDTO> findEscalasByNome(String nome) {
		return escalaDAO.findByNomeIgnoreCaseContaining(nome)
				.stream()
				.map(escalaMapper::toDTO)
				.toList();
	}

	public List<EscalaDTO> findEscalasByData(Date dataInicio, Date dataFim) {
		return escalaDAO.findByDataBetween(dataInicio, dataFim)
				.stream()
				.map(escalaMapper::toDTO)
				.toList();
	}

	//-------------POST METHODS--------------

	@Transactional(rollbackFor = Exception.class)
	public EscalaDTO saveEscala(@Valid @NotNull EscalaDTO escala){
		return escalaMapper.toDTO(escalaDAO.save(escalaMapper.toEntity(escala)));
	}

	//-------------PUT METHODS--------------

	public EscalaDTO updateEscala(@Valid @NotNull EscalaDTO escala) {
		verifyIfEscalaExists(escala.id());
		return escalaMapper.toDTO(escalaDAO.save(escalaMapper.toEntity(escala)));
	}

	//-------------Delete METHODS--------------

	public void deleteEscala(@NotNull @Positive Long id){
		verifyIfEscalaExists(id);
		escalaDAO.deleteById(id);
	}



	public Escala adicionarPessoaEscala(@NotNull @Positive Long escalasId, @NotNull @Positive Long pessoasId) {
		
		Optional<Escala> escalaExistente = escalaDAO.findById(escalasId);
		Optional<Pessoa> pessoaExistente = pessoaDAO.findById(pessoasId);
		
		if(escalaExistente.isPresent() && pessoaExistente.isPresent()) {
			escalaExistente.get().getPessoas().add(pessoaExistente.get());
			
			return escalaDAO.save(escalaExistente.get());
		}
		
		return escalaDAO.save(escalaExistente.get());
	}

	public Escala adicionarLocalEscala(@NotNull @Positive Long escalasId, @NotNull @Positive Long localId) {

		Optional<Escala> escalaExistente = escalaDAO.findById(escalasId);
		Optional<Local> localExistente = localDAO.findById(localId);

		if(escalaExistente.isPresent() && localExistente.isPresent()) {
			escalaExistente.get().getLocal().add(localExistente.get());
			escalaDAO.save(escalaExistente.get());

			return escalaDAO.save(escalaExistente.get());
		}

		return null;
	}

	public Escala removerLocalEscala(@NotNull @Positive Long escalasId, @NotNull @Positive Long localId){
		Optional<Escala> escalaExistente = escalaDAO.findById(escalasId);
		Optional<Local> localExistente = localDAO.findById(localId);

		if(escalaExistente.isPresent() && localExistente.isPresent()) {
			escalaExistente.get().getLocal().remove(localExistente.get());
			escalaDAO.save(escalaExistente.get());

			return escalaDAO.save(escalaExistente.get());
		}

		return null;

	}

	public Escala removerPessoalEscala(@NotNull @Positive Long escalasId, @NotNull @Positive Long pessoasId){
		Optional<Escala> escalaExistente = escalaDAO.findById(escalasId);
		Optional<Pessoa> pessoaExistente = pessoaDAO.findById(pessoasId);

		if(escalaExistente.isPresent() && pessoaExistente.isPresent()) {
			escalaExistente.get().getPessoas().size();
			escalaExistente.get().getPessoas().remove(pessoaExistente.get());


			return escalaDAO.save(escalaExistente.get());
		}

		return escalaDAO.save(escalaExistente.get());

	}

	public void verifyIfEscalaExists(@NotNull @Positive Long id) {

		if (escalaDAO.findById(id).isPresent()){
			return;
		} throw new ResourceNotFoundException("Escala not found for ID: " + id);
	}
	
}
