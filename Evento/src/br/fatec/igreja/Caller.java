package br.fatec.evento;

public class Caller extends Thread{
	public CallSoap cs;
	public int idConvidado;
	public int method;
	public String nome;
	public String email;
	public String telefone;
	public String origem;
	public String funcao;
	
	public void run(){
		try{
			cs = new CallSoap();
			String resp = null;
			switch (method) {
			case 1:
				resp = cs.Call(idConvidado);
				MainActivity.rslt = resp;
				break;
			case 2:
				resp = cs.Insert(nome, email, telefone, origem, funcao);
				ConvidadoActivity.rslt = resp;
				break;
			}
		} catch(Exception ex) {
			MainActivity.rslt = ex.toString();
		}
	}
}
