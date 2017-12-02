package controller;

import static org.junit.Assert.*;

import java.nio.channels.AlreadyConnectedException;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import model.Aluno;

public class ControleTest {

	Controle controle;
	
	@Before
	public void setUp() throws Exception {
		controle = new Controle();
	}

	@Test
	public void cadastraAlunoCorreto(){		
		controle.cadastrarAluno("0172817", "Kakaroto", "Transformação Sayajin");
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void cadastraAlunoMatriculaVazia(){
		controle.cadastrarAluno("", "Kakaroto", "Transformação Sayajin");
	}
	
	@Test(expected= NullPointerException.class)
	public void cadastraAlunoMatriculaNula(){
		controle.cadastrarAluno(null, "Kakaroto", "Transformação Sayajin");
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void cadastraAlunoNomeVazio(){
		controle.cadastrarAluno("0172817", "", "Transformação Sayajin");
	}
	
	@Test(expected= NullPointerException.class)
	public void cadastraAlunoNomeNulo(){
		controle.cadastrarAluno("0172817", null, "Transformação Sayajin");
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void cadastraAlunoCursoVazio(){
		controle.cadastrarAluno("0172817", "Kakaroto", "");
	}
	
	@Test(expected= NullPointerException.class)
	public void cadastraAlunoCursoNulo(){
		controle.cadastrarAluno("0172817", "Kakaroto", null);
	}
	
	@Test(expected= AlreadyConnectedException.class)
	public void cadastraAlunoJaCadastrado(){
		
		controle.cadastrarAluno("0172817", "Kakaroto", "Transformação Sayajin");		
		controle.cadastrarAluno("0172817", "Kakaroto", "Transformação Sayajin");
	}
	
	@Test
	public void exibeAluno() {
		String matricula = "0172817";
		controle.cadastrarAluno("0172817", "Kakaroto", "Transformação Sayajin");
		
		String msg = "Esperando informações complestas de aluno";
		assertEquals(msg, "0172817 - Kakaroto - Transformação Sayajin", 
				controle.exibirAluno(matricula));
	}
	
	@Test(expected= NullPointerException.class)
	public void exibeAlunoMatriculaNula() {		
		controle.exibirAluno(null);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void exibeAlunoMatriculaVazia() {		
		controle.exibirAluno("");
	}
	
	@Test(expected= NoSuchElementException.class)
	public void exibeAlunoMatriculaInexistente() {		
		controle.exibirAluno("12428");
	}
	
	@Test
	public void cadastrarGrupo() {
		controle.cadastrarGrupo("Reprovados em calc");
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void cadastrarGrupoNomeVazio() {
		controle.cadastrarGrupo("");
	}
	
	@Test(expected= NullPointerException.class)
	public void cadastrarGrupoNomeNulo() {
		controle.cadastrarGrupo(null);
	}
	
	@Test(expected= AlreadyConnectedException.class)
	public void cadastrarGrupoJaCadastrado() {
		controle.cadastrarGrupo("Mito 2018");
		controle.cadastrarGrupo("Mito 2018");
	}
	
	@Test
	public void alocarAluno() {
		controle.cadastrarGrupo("a");
		controle.cadastrarAluno("12345", "Jão", "adm");
		
		controle.alocarAluno("12345", "a");
	}
	
	@Test(expected= NoSuchElementException.class)
	public void alocarAlunoNaoCadastrado() {
		controle.cadastrarGrupo("a");
		
		controle.alocarAluno("12345", "a");
	}
	
	@Test(expected= NoSuchElementException.class)
	public void alocarAlunoGrupoNaoCadastrado() {
		controle.cadastrarAluno("12345", "Jão", "adm");
		
		controle.alocarAluno("12345", "a");
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void alocarAlunoMatriculaVazia() {
		controle.cadastrarGrupo("a");
		controle.cadastrarAluno("12345", "Jão", "adm");
		
		controle.alocarAluno("", "a");
	}
	
	@Test(expected= NullPointerException.class)
	public void alocarAlunoMatriculaNula() {
		controle.cadastrarGrupo("a");
		controle.cadastrarAluno("12345", "Jão", "adm");
		
		controle.alocarAluno(null, "a");
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void alocarAlunoNomeVazio() {
		controle.cadastrarGrupo("a");
		controle.cadastrarAluno("12345", "Jão", "adm");
		
		controle.alocarAluno("12345", "");
	}
	
	@Test(expected= NullPointerException.class)
	public void alocarAlunoNomeNulo() {
		controle.cadastrarGrupo("a");
		controle.cadastrarAluno("12345", "Jão", "adm");
		
		controle.alocarAluno("12345", null);
	}
	

	@Test
	public void imprimirGrupo() {
		controle.cadastrarGrupo("a");
		controle.cadastrarAluno("12345", "Jão", "adm");
		controle.cadastrarAluno("10292", "Joana", "adm");
		
		controle.alocarAluno("12345", "a");
		controle.alocarAluno("10292", "a");
		
		String s = controle.imprimirGrupo("a");
		String msg = "Esperando representação de grupo";
		
		assertEquals(msg, "* 10292 - Joana - adm\n* 12345 - Jão - adm\n", s);
	}
	
	@Test(expected= NoSuchElementException.class)
	public void imprimirGrupoNaoCadastrado() {
		controle.imprimirGrupo("a");
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void imprimirGrupoNomeVazio() {
		controle.imprimirGrupo("");
	}
	
	@Test(expected= NullPointerException.class)
	public void imprimirGrupoNomeNulo() {
		controle.imprimirGrupo(null);
	}
	
	@Test
	public void registrarResposta() {
		controle.cadastrarAluno("12345", "Jão", "adm");
		
		controle.registrarResposta("12345");
	}
	
	@Test(expected= NullPointerException.class)
	public void registrarRespostaMatriculaNula() {		
		controle.registrarResposta(null);
	}
	
	@Test(expected= IllegalArgumentException.class)
	public void registrarRespostaMatriculaVazia() {		
		controle.registrarResposta("");
	}
	
	@Test
	public void alunosQueResponderam() {
		controle.cadastrarAluno("12345", "Jão", "adm");
		controle.cadastrarAluno("02931", "Joana", "adm");

		controle.registrarResposta("12345");
		controle.registrarResposta("02931");
		
		String s = controle.alunosQueResponderam();
		String msg = "Esperando lista de alunos que responderam";
		String expected = "1. 12345 - Jão - adm\n" +
							   "2. 02931 - Joana - adm\n";
		
		assertEquals(msg, expected, s);
	}
	
	@Test(expected= NoSuchElementException.class)
	public void alunosQueResponderamListaVazia() {
		controle.alunosQueResponderam();
	}
}
