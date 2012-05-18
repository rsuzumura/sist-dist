package br.fatec.igreja.classes;

public class Convidado {
	public int idConvidado;
	public String nome;
	public String email;
	public String telefone;
	public String origem;
	public String funcao;
	
	@Override
	public String toString() {
		return 
			"idConvidado: " + idConvidado + "\n" +
			"nome: " + nome + "\n" +
			"email: " + email + "\n" +
			"telefone: " + telefone + "\n" +
			"origem: " + origem + "\n" +
			"função: " + funcao;
	}
}
