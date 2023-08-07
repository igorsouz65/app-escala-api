package igor.escalaspring.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;


@SuppressWarnings("serial")
@Entity
@SQLDelete(sql = "UPDATE Pessoa SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Pessoa extends AbstractEntity implements Serializable{


	@NotEmpty(message = "O campo nome é obrigatorio!")
	@Length(min = 5, max = 100)
	private String nome;

	@NotEmpty(message = "A data de nascimento é obrigatorio!")
	private LocalDateTime dataNascimento;

	@NotEmpty(message = "O campo idade é obrigatorio!")
	private int idade;

	@NotEmpty(message = "O campo telefone é obrigatorio!")
	private String telefone;
	
	private String endereco;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "pessoas", fetch=FetchType.EAGER)
	private Set<Escala> escalas;
	
	
	
	public Pessoa() {
		super();
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDateTime dataNascimento) {
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
	
	public Set<Escala> getEscalas() {
		return escalas;
	}
	
	public void setEscala(Set<Escala> escalas) {
		this.escalas = escalas;
	}
	

	
		
}