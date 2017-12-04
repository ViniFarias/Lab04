package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste da classe GrupoDeEstudo
 * 
 * @see GrupoDeEstudo
 * @author Marcus Vinícius
 */
public class GrupoDeEstudoTest {
	
	GrupoDeEstudo grupo;
	
	@Before
	public void setUp() throws Exception {
		grupo = new GrupoDeEstudo("Aleatório");
	}

	@Test
	public void grupoCorreto() {
		GrupoDeEstudo grupo = new GrupoDeEstudo("XGH");
	}
	
	@Test(expected= NullPointerException.class)
	public void grupoTemaNulo() {
		GrupoDeEstudo grupo = new GrupoDeEstudo(null);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void grupoTemaVazio() {
		GrupoDeEstudo grupo = new GrupoDeEstudo("");
	}
	
	@Test
	public void alocarAlunoCorreto() {
		Aluno aluno = new Aluno("40020922", "Yudi", "Como fazer sushi");
		
		grupo.alocarAluno(aluno);
	}
	
	@Test(expected= NullPointerException.class)
	public void alocarAlunoNulo() {		
		grupo.alocarAluno(null);
	}
	
	@Test
	public void listaAlunos() {
		Aluno aluno1 = new Aluno("12345", "Reghar", "Medicina");
		Aluno aluno2 = new Aluno("09876", "Kerrigan", "Astronomia");
		
		grupo.alocarAluno(aluno1);
		grupo.alocarAluno(aluno2);
		
		String s = grupo.listaAlunos();
		String msg = "Esperando toString correto";
		
		assertEquals(msg, "* 09876 - Kerrigan - Astronomia\n* 12345 - Reghar - Medicina\n", s);
	}
	
	@Test
	public void TestaEqualsNomesIguais() {
		GrupoDeEstudo grupoAux = new GrupoDeEstudo("Aleatório");
		
		assertEquals(true, grupo.equals(grupoAux));
	}
	
	@Test
	public void TestaEqualsNomeMaiusculo() {
		GrupoDeEstudo grupoAux = new GrupoDeEstudo("ALEATÓRIO");
		
		assertEquals(true, grupo.equals(grupoAux));
	}
	
	@Test
	public void TestaEqualsNomeMinusculo() {
		GrupoDeEstudo grupoAux = new GrupoDeEstudo("aleatório");
		
		assertEquals(true, grupo.equals(grupoAux));
	}
	
	@Test
	public void TestaEqualsNomesDiferentes() {
		GrupoDeEstudo grupoAux = new GrupoDeEstudo("Aleatória");
		
		assertEquals(false, grupo.equals(grupoAux));
	}
	
	
}
