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
		
		if(alunos.containsValue(aluno)) {
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
			return "Aluno não cadastrado";			
		}
	}
	
	public boolean cadastrarGrupo(String nome) {
		
		GrupoDeEstudo grupo = new GrupoDeEstudo(nome);
		
		if(grupos.containsKey(nome)) {
			return false;
		}
		
		grupos.put(nome, grupo);
		return true;
	}
	
	public String alocarAluno(String matricula, String grupo) {
		
		if(!alunoCadastrado(matricula)){
			return "Aluno não cadastrado";
		}
		if(!grupoCadastrado(grupo)) {
			return "Grupo não cadastrado";
		}
		
		grupos.get(grupo).alocarAluno(alunos.get(matricula));
		
		return "ALUNO ALOCADO!";
	}
	
	public String imprimirGrupo(String grupo) {
		if(!this.grupoCadastrado(grupo)) {
			return "Grupo não cadastrado";
		}
		 return grupos.get(grupo).listaAlunos();
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
		
		if(!this.validaGrupo(grupo)) {
			throw new IllegalArgumentException("Foi inserido algum dado nulo ou vazio.");
		}
		
		return grupos.containsKey(grupo);
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
