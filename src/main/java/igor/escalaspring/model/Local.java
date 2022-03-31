package igor.escalaspring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity
public class Local extends AbstractEntity implements Serializable{

	
	@NotEmpty(message = "O campo nome é obrigatorio!")
	private String nome;
	
	private String endereco;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "local")
	private List<Escala> escalas;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "local")
	private List<Pessoa> pessoas;
	
	
	public Local() {
		super();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Escala> getEscalas() {
		return escalas;
	}

	public void setEscalas(List<Escala> escalas) {
		this.escalas = escalas;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	
	
	
	

}
