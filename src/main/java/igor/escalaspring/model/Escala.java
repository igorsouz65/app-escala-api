package igor.escalaspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@SQLDelete(sql = "UPDATE Escala SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Escala extends AbstractEntity implements Serializable{


	@NotEmpty(message = "O campo nome é obrigatorio!")
	@Length(min = 5, max = 100)
	private String nome;

	@NotNull(message = "O campo data é obrigatorio!")
	private Date data;

	public String getNome() {
		return nome;
	}

	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Pessoa> pessoas;

	@ManyToMany(fetch=FetchType.EAGER)
	private List<Local> local;



	public List<Local> getLocal() {
		return local;
	}

	public void setLocal(List<Local> local) {
		this.local = local;
	}

	public Escala() {
		super();
	}

	public Set<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(Set<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
