package igor.escalaspring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;




@SuppressWarnings("serial")
@Entity
public class Pessoa extends AbstractEntity implements Serializable{


	@NotEmpty(message = "O campo nome é obrigatorio!")
	private String nome;

	@NotEmpty(message = "A data de nascimento é obrigatorio!")
	private Date dataNascimento;
	
	private int idade;

	@NotEmpty(message = "O campo telefone é obrigatorio!")
	private String telefone;
	
	private String endereco;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "pessoas", fetch=FetchType.EAGER)
	private List<Escala> escalas;
	
	@ManyToMany(fetch=FetchType.LAZY)
	private List<Local> local;
	
	
	
	public Pessoa() {
		super();
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public List<Local> getLocal() {
		return local;
	}
	
	public void setLocal(List<Local> local) {
		this.local = local;
	}
	
	public List<Escala> getEscalas() {
		return escalas;
	}
	
	public void setEscala(List<Escala> escalas) {
		this.escalas = escalas;
	}
	

	
		
}