package main;

import java.util.Scanner;

import model.Controle;

public class Menu {
	
	private static final  String CADASTRAR_ALUNO_OP = "C";
	private static final  String EXIBIR_ALUNO_OP = "E";
	private static final  String NOVO_GRUPO_OP = "N";
	private static final  String ALOCAR_ALUNO_IMPRIMIR_GRUPOS_OP = "A";
	private static final  String REGISTRAR_RESPOSTA_OP = "R";
	private static final  String IMPRIMIR_ALUNOS_OP = "I";
	private static final  String FECHAR_PROGRAMA_OP = "O";
	
	private static final String OPCAO_INVALIDA_MSG = "OPÇÃO INVÁLIDA!";
	private static final String MATRICULA_JA_CADASTRADA_MSG = "POSIÇÃO INVÁLIDA!";
	private static final String ALUNO_NAO_CADASTRADO_MSG = "Aluno não cadastrado.";
	private static final String CADASTRO_REALIZADO_MSG = "CADASTRADO REALIZADO!";
	
	/**
	 * Scanner utilizado para ler as entradas.
	 */
	private Scanner sc = new Scanner(System.in);
	
	private Controle controle;
	
	/**
	 * Método main que dá início a aplicação.
	 * Cria um objeto do tipo Menu para exibir o menu de opções da aplicação.
	 * 
	 * @param args Argumentos recebidos durante a execução.
	 */
	public static void main(String[] args) {
		new Menu().exibeMenu();
	}
	
	/**
	 * Exibe o menu de opções.
	 */
	private void exibeMenu() {
		
		String opcao = "";
		
		do {
				System.out.print("(C)adastrar Aluno\n" +
						"(E)xibir Aluno\n" +
						"(N)ovo Grupo\n" +
						"(A)locar Aluno no Grupo e Imprimir Grupos\n" +
						"(R)egistrar Resposta de Aluno\n" +
						"(I)mprimir Alunos que Responderam\n" +
						"(O)ra, vamos fechar o programa!\n");
				
				System.out.print("\nOpção> ");
		
				opcao = sc.next();
				this.interpretaOpcao(opcao);
			
		}while(!opcao.equals(FECHAR_PROGRAMA_OP));
	}
	
	/**
	 * Interpreta uma opção do menu.
	 * 
	 * @param op Opção do menu.
	 */
	private void interpretaOpcao(String op) {
		switch (op) {
			case CADASTRAR_ALUNO_OP:
				this.cadastrarAluno();
				break;
			case EXIBIR_ALUNO_OP:
				this.exibirAluno();
				break;
			case NOVO_GRUPO_OP:
				this.cadastrarGrupo();
				break;
			case ALOCAR_ALUNO_IMPRIMIR_GRUPOS_OP:
				this.alocarAlunoImprimirGrupos();
				break;
			case REGISTRAR_RESPOSTA_OP:
				this.registrarRespostas();
				break;
			case IMPRIMIR_ALUNOS_OP:
				this.imprimirAlunosQueRespondem();
				break;
			case FECHAR_PROGRAMA_OP:
				break;
			default:
				System.out.println(OPCAO_INVALIDA_MSG);
			}
	}
	
	/**
	 * Cadastra um aluno no sistema.
	 */
	private void cadastrarAluno() {
		
		System.out.print("Matrícula: ");
		String matricula = sc.nextLine();
		
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		
		System.out.print("Curso: ");
		String curso = sc.nextLine();
		
		if(controle.cadastrarAluno(matricula, nome, curso)) {
			System.out.println(System.lineSeparator() + CADASTRO_REALIZADO_MSG + System.lineSeparator());
		}
		else {
			System.out.println(System.lineSeparator() + MATRICULA_JA_CADASTRADA_MSG + System.lineSeparator());
		}
	}
	
	/**
	 * Exibe um aluno de acordo com a matrícula.
	 */
	private void exibirAluno() {
		System.out.print("Matrícula: ");
		String matricula = sc.nextLine();
		
		String alunoString = System.lineSeparator() + "Aluno: " + this.controle.exibirAluno(matricula);
		
		
		if(alunoString != "") {
			System.out.println(alunoString + System.lineSeparator());
		}
		else {
			System.out.println(ALUNO_NAO_CADASTRADO_MSG + System.lineSeparator());
		}
	}
	
	/**
	 * Cadastra um grupo de estudo.
	 */
	private void cadastrarGrupo() {
		
		System.out.print("Nome: ");
		String nome = sc.nextLine();
		
		if(controle.cadastrarGrupo(nome)) {
			System.out.println(System.lineSeparator() + CADASTRO_REALIZADO_MSG + System.lineSeparator());
		}
		else {
			System.out.println(System.lineSeparator() + MATRICULA_JA_CADASTRADA_MSG + System.lineSeparator());
		}
	}
	
	private void alocarAlunoImprimirGrupos() {
		System.out.println("(A)locar Aluno ou (I)mprimir Grupo? ");
		String op = sc.nextLine();
		
		if(op.equals("A")) {
			System.out.print("Matricula: ");
			String matricula = sc.nextLine();
			
			System.out.print("Grupo: ");
			String grupo = sc.nextLine();
			
			System.out.println(controle.alocarAluno(matricula, grupo));
		}
		else if(op.equals("I")) {
			System.out.print("Grupo: ");
			String grupo = sc.nextLine();
			
			System.out.println(controle.imprimirGrupo(grupo));
		}
	}
	
	private void registrarRespostas() {
		System.out.print("Matricula: ");
		String matricula = sc.nextLine();
		
		System.out.println(controle.registrarResposta(matricula));
	}
	
	private void imprimirAlunosQueRespondem() {
		System.out.println("Aluno ");
		System.out.println(controle.getAlunosQueResponderam());
	}
	
}
