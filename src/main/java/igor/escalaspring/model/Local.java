package igor.escalaspring.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@SuppressWarnings("serial")
@Entity
@SQLDelete(sql = "UPDATE Escala SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Local extends AbstractEntity implements Serializable{

	
	@NotEmpty(message = "O campo nome é obrigatorio!")
	private String nome;
	
	private String endereco;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "local")
	private List<Escala> escalas;
	
	
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


	
	
	
	

}
