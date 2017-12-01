package model;

import java.util.HashSet;
import java.util.Iterator;

public class GrupoDeEstudo {

	private String tema;
	private HashSet<Aluno> alunos;
	
	public GrupoDeEstudo(String tema) {
		if(validaGrupoDeEstudo(tema)) {
			this.tema = tema;
			this.alunos = new HashSet<Aluno>();
		}
		else {
			throw new IllegalArgumentException("Foi inserido algum dado nulo ou vazio.");
		}
	}
	
	public String getTema() {
		return tema;
	}
	
	public void alocarAluno(Aluno aluno) {
		alunos.add(aluno);
	}
	
	public String listaAlunos() {
		String s = "";
						
	    for(Aluno aluno : alunos) {
	    	s += "* " + aluno.toString() + System.lineSeparator();
	    }
	    
	    return s;
	}
	
	public boolean validaGrupoDeEstudo(String tema) {
		if(tema.trim().equals("") || tema == null) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alunos == null) ? 0 : alunos.hashCode());
		result = prime * result + ((tema == null) ? 0 : tema.toLowerCase().hashCode());
		return result;
	}
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
