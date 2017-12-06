package model;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Classe que representa um grupo de estudo;
 * 
 * @author Marcus Vinícius
 */
public class GrupoDeEstudo {
	
	/**
	 * Tema ou nome do grupo.
	 */
	private String tema;
	
	/**
	 * Representa o conjunto de alunos que um grupo possui.
	 */
	private HashSet<Aluno> alunos;
	
	/**
	 * Construtor da classe.
	 * 
	 * @param tema tema ou nome do grupo.
	 */
	public GrupoDeEstudo(String tema) {
		validaTema(tema);
		
		this.tema = tema;
		this.alunos = new HashSet<Aluno>();
	}
	
	/**
	 * Aloca um aluno no grupo.
	 * 
	 * @param aluno um objeto do tipo Aluno
	 */
	public void alocarAluno(Aluno aluno) {
		if(aluno == null) {
			throw new NullPointerException("Dado nulo inserido");
		}
		
		alunos.add(aluno);
	}
	
	/**
	 * Lista os alunos do grupo.
	 * 
	 * @return uma <code>string</code> que representa a lista de alunos do grupo.
	 */
	public String listaAlunos() {
		String s = "";
						
	    for(Aluno aluno : alunos) {
	    	s += "* " + aluno.toString() + System.lineSeparator();
	    }
	    
	    return s;
	}
	
	/**
	 * Verifica se um tema é válido.
	 * 
	 * @param tema tema/nome do grupo.
	 */
	private void validaTema(String tema) {
		if(tema == null) {
			throw new NullPointerException("Tema nulo");
		}
		if(tema.trim().equals("")) {
			throw new IllegalArgumentException("Tema vazio");
		}
	}
	
	public String getTema() {
		return tema;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alunos == null) ? 0 : alunos.hashCode());
		result = prime * result + ((tema == null) ? 0 : tema.toLowerCase().hashCode());
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoDeEstudo other = (GrupoDeEstudo) obj;
		if (alunos == null) {
			if (other.alunos != null)
				return false;
		} else if (!alunos.equals(other.alunos))
			return false;
		if (tema == null) {
			if (other.tema != null)
				return false;
		} else if (!tema.toLowerCase().equals(other.tema.toLowerCase()))
			return false;
		return true;
	}
	
	
}
