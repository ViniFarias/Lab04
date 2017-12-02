package model;

/**
 * Classe que representa um aluno;
 * 
 * @author Marcus Vinícius
 */
public class Aluno {
	
	/**
	 * Matrícula do aluno.
	 */
	private String matricula;
	
	/**
	 * Nome do aluno.
	 */
	private String nome;
	
	/**
	 * Curso do aluno.
	 */
	private String curso;
	
	/**
	 * Construtor da classe.
	 * @param matricula matrícula do aluno.
	 * @param nome nome do aluno.
	 * @param curso curso do aluno.
	 */
	public Aluno(String matricula, String nome, String curso) {
		validaAluno(matricula, nome, curso);
		
		this.matricula = matricula;
		this.nome = nome;
		this.curso = curso;
	}
	
	/**
	 * Valida as informações de um aluno.
	 * 
	 * @param matricula matrícula do aluno.
	 * @param nome nome do aluno.
	 * @param curso curso do aluno.
	 */
	public void validaAluno(String matricula, String nome, String curso) {
		if(matricula == null) {
			throw new NullPointerException("Matrícula nula!");
		}
		if(matricula.trim().equals("")) {
			throw new IllegalArgumentException("Matrícula vazia!");
		}
		if(nome == null) {
			throw new NullPointerException("Nome nulo!");
		}
		if(nome.trim().equals("")) {
			throw new IllegalArgumentException("Nome vazio!");
		}
		if(curso == null) {
			throw new NullPointerException("Curso nulo!");
		}
		if(curso.trim().equals("")) {
			throw new IllegalArgumentException("Curso vazio!");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return this.matricula + " - " + this.nome + " - " + this.curso;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
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
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	
	
}
