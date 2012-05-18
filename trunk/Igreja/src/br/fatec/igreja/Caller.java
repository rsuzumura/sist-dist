package br.fatec.igreja;

public class Caller extends Thread{
	public CallSoap cs;
	public int idConvidado;
	
	public void run(){
		try{
			cs = new CallSoap();
			String resp = cs.Call(idConvidado);
			IgrejaActivity.rslt = resp;
		} catch(Exception ex) {
			IgrejaActivity.rslt = ex.toString();
		}
	}
}
