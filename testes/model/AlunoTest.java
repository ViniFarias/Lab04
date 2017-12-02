package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de testes da classe Aluno.
 * 
 * @see Aluno
 * @author Marcus Vinícius
 *
 */
public class AlunoTest {
	
	Aluno aluno;
	
	@Before
	public void setUp() throws Exception {
		aluno = new Aluno("070707", "Hanzo", "Assassinato");
	}

	@Test
	public void alunoCorreto() {
		Aluno aluno = new Aluno("010203", "Lili", "Enfermagem");
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void alunoMatriculaVazia() {
		Aluno aluno = new Aluno("", "Lili", "Enfermagem");
	}
	
	@Test(expected= NullPointerException.class)
	public void alunoMatriculaNula() {
		Aluno aluno = new Aluno(null, "Lili", "Enfermagem");
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void alunoNomeVazio() {
		Aluno aluno = new Aluno("010203", "", "Enfermagem");
	}
	
	@Test(expected= NullPointerException.class)
	public void alunoNomeNulo() {
		Aluno aluno = new Aluno("010203", null, "Enfermagem");
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void alunoCursoVazio() {
		Aluno aluno = new Aluno("010203", "Lili", "");
	}
	
	@Test(expected= NullPointerException.class)
	public void alunoCursoNulo() {
		Aluno aluno = new Aluno("010203", "Lili", null);
	}
	
	@Test
	public void testaToString() {
		String s = aluno.toString();
		String msg = "Esperando informações complestas de aluno";
		
		assertEquals(msg, "070707 - Hanzo - Assassinato", aluno.toString());
	}
	
	@Test
	public void testaEqualsInformaçõesIguais() {
		Aluno alunoAux = new Aluno("070707", "Hanzo", "Assassinato");
		
		assertEquals(true, aluno.equals(alunoAux));
	}
	
	@Test
	public void testaEqualsMatrículasDiferentes() {
		Aluno alunoAux = new Aluno("010101", "Hanzo", "Assassinato");
		
		assertEquals(false, aluno.equals(alunoAux));
	}
	
	@Test
	public void testaEqualsApenasMatriculasIguais() {
		Aluno alunoAux = new Aluno("070707", "Varian", "Guerreiro");
		
		assertEquals(true, aluno.equals(alunoAux));
	}
}
