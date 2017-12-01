package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Controle {
	
	private HashMap<String, GrupoDeEstudo> grupos;
	private ArrayList<Aluno> alunosQueResponderam;
	private HashMap<String, Aluno> alunos;
	
	public Controle() {
		this.grupos = new HashMap<String, GrupoDeEstudo>();
		this.alunosQueResponderam = new ArrayList<Aluno>();
		this.alunos = new HashMap<String, Aluno>();
	}
	
	public boolean cadastrarAluno(String matricula, String nome, String curso) {
		
		Aluno aluno = new Aluno(matricula, nome, curso);
		
		if(this.alunoCadastrado(matricula)) {
			return false;
		}
		
		alunos.put(matricula, aluno);
		return true;
		
		
	}
	
	public String exibirAluno(String matricula) {
		
		if(this.alunoCadastrado(matricula)) {
			return alunos.get(matricula).toString();
		}
		else {
			return null;			
		}
	}
	
	public boolean cadastrarGrupo(String nome) {
		
		String keyGrupo = nome.toLowerCase();
		
		GrupoDeEstudo grupo = new GrupoDeEstudo(nome);
		
		if(this.grupoCadastrado(nome)) {
			return false;
		}
		
		grupos.put(keyGrupo, grupo);
		return true;
	}
	
	public String alocarAluno(String matricula, String grupo) {
		
		if(!alunoCadastrado(matricula)){
			return "Aluno não cadastrado";
		}
		if(!grupoCadastrado(grupo)) {
			return "Grupo não cadastrado";
		}
		
		grupos.get(grupo.toLowerCase()).alocarAluno(alunos.get(matricula));
		
		return "ALUNO ALOCADO!";
	}
	
	public String imprimirGrupo(String grupo) {
		if(!this.grupoCadastrado(grupo)) {
			return "Grupo não cadastrado";
		}
		
		return grupos.get(grupo.toLowerCase()).listaAlunos();
	}
	
	public String registrarResposta(String matricula) {
		if(!alunoCadastrado(matricula)) {
			return "Aluno não cadastrado";
		}
		
		this.alunosQueResponderam.add(alunos.get(matricula));
		return "ALUNO REGISTRADO!";
	}
	
	public String getAlunosQueResponderam() {
		String s = "";
		
		for(int i = 0; i < alunosQueResponderam.size(); i++) {
			s += i+1 + ". " + alunosQueResponderam.get(i).toString();
		}
		
		return s;
	}
	
	public boolean alunoCadastrado(String matricula) {
		
		if(!this.validaMatricula(matricula)) {
			throw new IllegalArgumentException("Foi inserido algum dado nulo ou vazio.");
		}
		
		return alunos.containsKey(matricula);
	}
	
	public boolean grupoCadastrado(String grupo) {
		
		String keyGrupo = grupo.toLowerCase();
		
		if(!this.validaGrupo(keyGrupo)) {
			throw new IllegalArgumentException("Foi inserido algum dado nulo ou vazio.");
		}
		
		return grupos.containsKey(keyGrupo);
	}
	
	public boolean validaMatricula(String matricula) {
		if(matricula.trim().equals("") || matricula == null) {
			return false;
		}
		
		return true;
	}
	
	public boolean validaGrupo(String grupo) {
		if(grupo.trim().equals("") || grupo == null) {
			return false;
		}
		
		return true;
	}
}
