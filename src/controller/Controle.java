package controller;

import java.nio.channels.AlreadyConnectedException;
import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;

import com.sun.org.apache.xml.internal.security.exceptions.AlgorithmAlreadyRegisteredException;

import model.Aluno;
import model.GrupoDeEstudo;

/**
 * Classe responsável por controlar todo o sistema.
 * Armazena os grupos de estudos, alunos, e alunos que responderam
 * as perguntas.
 * 
 * @author Marcus Vinícius
 */
public class Controle {
	
	/**
	 * Representa os grupos de estudos do sistema.
	 * As chaves são os nomes/temas do grupo.
	 * E os valores objetos do tipo GrupoDeEstudo.
	 * 
	 * @see GrupoDeEstudo
	 */
	private HashMap<String, GrupoDeEstudo> grupos;
	
	/**
	 * Representa os alunos que responderam as perguntas do sistema.
	 * 
	 * @see Aluno
	 */
	private ArrayList<Aluno> alunosQueResponderam;
	
	/**
	 * Representa os alunos do sistema.
	 * As chaves são as matrículas do alunos.
	 * E os valores objetos do tipo Aluno.
	 * 
	 * @see Aluno
	 */
	private HashMap<String, Aluno> alunos;
	
	/**
	 * Construtor da classe.
	 */
	public Controle() {
		this.grupos = new HashMap<String, GrupoDeEstudo>();
		this.alunosQueResponderam = new ArrayList<Aluno>();
		this.alunos = new HashMap<String, Aluno>();
	}
	
	/**
	 * Cadastra um aluno no sistema.
	 * 
	 * @param matricula matrícula do aluno.
	 * @param nome nome do aluno.
	 * @param curso curso do aluno.
	 */
	public void cadastrarAluno(String matricula, String nome, String curso) {
		
		Aluno aluno = new Aluno(matricula, nome, curso);
		
		if(this.alunoCadastrado(matricula)) {
			throw new AlreadyConnectedException();
		}
		
		alunos.put(matricula, aluno);
	}
	
	/**
	 * Exibe um aluno cadastrado no sistema.
	 * 
	 * @param matricula matricula do aluno.
	 * @return uma <code>string</code> que representa o aluno.
	 */
	public String exibirAluno(String matricula) {
		
		if(!this.alunoCadastrado(matricula)) {
			throw new NoSuchElementException();
		}
		
		return alunos.get(matricula).toString();
	}
	
	/**
	 * Cadastra um grupo de estudo.
	 * Guarda a chave como o nome/tema do grupo em minúsculo.
	 * 
	 * @param nome nome/tema do grupo de estudo.
	 */
	public void cadastrarGrupo(String nome) {
		
		GrupoDeEstudo grupo = new GrupoDeEstudo(nome);
		
		if(this.grupoCadastrado(nome)) {
			throw new AlreadyConnectedException();
		}
		
		String keyGrupo = nome.toLowerCase();
		grupos.put(keyGrupo, grupo);
	}
	
	/**
	 * Aloca um aluno em grupo de estudo.
	 * 
	 * @param matricula matricula do aluno.
	 * @param grupo nome/tema do grupo.
	 */
	public void alocarAluno(String matricula, String grupo) {
		
		if(!alunoCadastrado(matricula)){
			throw new NoSuchElementException("Aluno não cadastrado.");
		}
		if(!grupoCadastrado(grupo)) {
			throw new NoSuchElementException("Grupo não cadastrado.");
		}
		
		String keyGrupo = grupo.toLowerCase();
		
		grupos.get(keyGrupo).alocarAluno(alunos.get(matricula));
	}
	
	/**
	 * Imprime um grupo de estudo.
	 * 
	 * @param grupo nome/tema do grupo de estudo.
	 * @return uma <code>string</code> que representa a lista de alunos do grupo.
	 */
	public String imprimirGrupo(String grupo) {
		if(!this.grupoCadastrado(grupo)) {
			throw new NoSuchElementException("Grupo não cadastrado.");
		}
		
		String keyGrupo = grupo.toLowerCase();
		
		return grupos.get(keyGrupo).listaAlunos();
	}
	
	/**
	 * Registra um aluno que respondeu a uma pergunta.
	 * 
	 * @param matricula matricula do aluno.
	 */
	public void registrarResposta(String matricula) {
		if(!alunoCadastrado(matricula)) {
			throw new NoSuchElementException("Aluno não cadastrado.");
		}
		
		this.alunosQueResponderam.add(alunos.get(matricula));
	}
	
	/**
	 * Retorna os alunos que responderam.
	 * 
	 * @return uma <code>string</code> que representa os alunos
	 * que responderam.
	 */
	public String alunosQueResponderam() {
		
		if(alunosQueResponderam.isEmpty()) {
			throw new NoSuchElementException("Nenhum aluno respondeu.");
		}
		
		String s = "";
		
		for(int i = 0; i < alunosQueResponderam.size(); i++) {
			s += i+1 + ". " + alunosQueResponderam.get(i).toString() + System.lineSeparator();
		}
		
		return s;
	}
	
	/**
	 * Verifica se um aluno está cadastrado.
	 * 
	 * @param matricula matricula do aluno.
	 * @return um <code>boolean</code> que informa se o aluno
	 * está cadastrado ou não.
	 */
	public boolean alunoCadastrado(String matricula) {
		
		if(!this.validaString(matricula)) {
			throw new IllegalArgumentException("Foi inserido algum dado nulo ou vazio.");
		}
		
		return alunos.containsKey(matricula);
	}
	
	/**
	 * Verifica se um grupo está cadstrado.
	 * 
	 * @param grupo nome/tema do grupo.
	 * @return um <code>boolean</code> que informa se o grupo
	 * está cadastrado ou não.
	 */
	public boolean grupoCadastrado(String grupo) {
				
		if(!this.validaString(grupo)) {
			throw new IllegalArgumentException("Foi inserido algum dado nulo ou vazio.");
		}
		
		String keyGrupo = grupo.toLowerCase();
		
		return grupos.containsKey(keyGrupo);
	}
	
	/**
	 * Valida uma <code>string</code> verificando se ela é vazia ou nula.
	 * 
	 * @param string <code>string</code> que será validada.
	 * @return um <code>boolean</code> que informa se a <code>string</code>\
	 * é válida ou não.
	 */
	public boolean validaString(String string) {
		if(string.trim().equals("") || string == null) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Retorna o nome de cadastrado do grupo.
	 * 
	 * @param nome nome/tema do grupo.
	 * @return uma <code>string</code> que representa o nome/tema do grupo
	 */
	public String getNomeGrupo(String nome) {
		
		if(!this.grupoCadastrado(nome)) {
			throw new NoSuchElementException("Grupo não cadastrado.");
		}
		
		String keyGrupo = nome.toLowerCase();
		
		return this.grupos.get(keyGrupo).getTema();
	}
}
