package br.fatec.evento.classes;

import java.util.List;

public class EventoService extends Thread{
	public Evento request;
	public List<Evento> resultEventos;
	public EventoSOAP es;
	public int tipoEventoIdRequest;
	public int idConvidadoRequest;
	public int methodRequest;
	public String URL;
	public String nomeRequest;
	public String result;
	boolean updateResult;
	public int idEventoResult;
	
	public void run(){
		try{
			es = new EventoSOAP(URL);
			switch (methodRequest) {
			case 0:
				resultEventos = es.list();
				break;
			case 1:
				resultEventos = es.listByTipoEvento(tipoEventoIdRequest);
				break;
			case 2:
				resultEventos = es.listByConvidado(idConvidadoRequest);
				break;
			case 3:
				resultEventos = es.listByNome(nomeRequest);
				break;
			case 4:
				idEventoResult = es.Add(request.nome, request.local, request.responsavel, request.inicioEvento, request.fimEvento, request.status, request.tipoEventoId); 
				break;
			case 5:
				updateResult = es.Save(request.id, request.nome, request.local, request.responsavel, request.inicioEvento, request.fimEvento, request.status, request.tipoEventoId); 
				break;
			}
		} catch(Exception ex) {
			result = ex.toString();
		}
	}
}
