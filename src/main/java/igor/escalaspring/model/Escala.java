package igor.escalaspring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
public class Escala extends AbstractEntity implements Serializable{


	@NotEmpty(message = "O campo nome é obrigatorio!")
	private String nome;
	
	private Date data;	
	
	public String getNome() {
		return nome;
	}
	
	@ManyToMany(fetch=FetchType.EAGER)
	private List<Pessoa> pessoas;
	
	@ManyToMany(fetch=FetchType.LAZY)
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

	public List<Pessoa> getPessoas() {
		return pessoas;
	}
	
	public void setPessoas(List<Pessoa> pessoas) {
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
