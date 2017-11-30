package model;

public class Aluno {
	
	private String matricula;
	private String nome;
	private String curso;
	
	public Aluno(String matricula, String nome, String curso) {
		if(this.validaAluno(matricula, nome, curso)) {
			this.matricula = matricula;
			this.nome = nome;
			this.curso = curso;
		}
		else {
			throw new IllegalArgumentException("Foi inserido algum dado nulo ou vazio");
		}
		
	}

	@Override
	public String toString() {
		return this.matricula + " - " + this.nome + " - " + this.curso;
	}
	
	public boolean validaAluno(String matricula, String nome, String curso) {
		if(matricula.trim().equals("") || matricula == null) {
			return false;
		}
		if(nome.trim().equals("") || nome == null) {
			return false;
		}
		if(curso.trim().equals("") || curso == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	
	
}
