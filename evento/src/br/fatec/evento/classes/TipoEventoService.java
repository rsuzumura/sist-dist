package br.fatec.evento.classes;

import java.util.List;

public class TipoEventoService extends Thread {
	public TipoEventoSOAP tes;
	public String URL;
	public int methodRequest;
	public TipoEvento request;
	public List<TipoEvento> tiposEventoResult;
	public int idTipoEventoResult;
	public String result;
	boolean updateResult;
	
	public void run(){
		try{
			tes = new TipoEventoSOAP(URL);
			switch (methodRequest) {
			case 0:
				tiposEventoResult = tes.list();
				break;
			case 1:
				idTipoEventoResult = tes.Add(request.nome);
				break;
			case 2:
				updateResult = tes.Save(request.id, request.nome);
				break;
			}
		} catch(Exception ex) {
			result = ex.toString();
		}
	}
}
