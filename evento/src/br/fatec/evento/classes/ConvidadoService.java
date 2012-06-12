package br.fatec.evento.classes;

import java.util.List;

public class ConvidadoService extends Thread{
	public Convidado request;
	public Convidado resultConvidado;
	public List<Convidado> resultConvidados;
	public ConvidadoSOAP cs;
	public int idConvidadoRequest;
	public String URL;
	public int idConvidadoResult;
	public int methodRequest;
	public int idEventoRequest;
	public String result;
	boolean updateResult;
	
	public void run(){
		try{
			cs = new ConvidadoSOAP(URL);
			switch (methodRequest) {
			case 0:
				resultConvidado = cs.get(idConvidadoRequest);
				break;
			case 1:
				resultConvidados = cs.list();
				break;
			case 2:
				resultConvidados = cs.listByEvento(idEventoRequest);
				break;
			case 3:
				idConvidadoResult = cs.Add(request.nome, request.email, request.telefone, request.origem);
				break;
			case 4:
				updateResult = cs.Save(request.id, request.nome, request.email, request.telefone, request.origem); 
				break;
			}
		} catch(Exception ex) {
			result = ex.toString();
		}
	}
}
