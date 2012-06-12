package br.fatec.evento.classes;

import java.util.List;

public class FuncaoService extends Thread {
	public FuncaoSOAP fs;
	public String URL;
	public int methodRequest;
	public Funcao request;
	public List<Funcao> funcoesResult;
	public int idFuncaoResult;
	public String result;
	boolean updateResult;
	
	public void run(){
		try{
			fs = new FuncaoSOAP(URL);
			switch (methodRequest) {
			case 0:
				funcoesResult = fs.list();
				break;
			case 1:
				idFuncaoResult = fs.Add(request.nome);
				break;
			case 2:
				updateResult = fs.Save(request.id, request.nome);
				break;
			}
		} catch(Exception ex) {
			result = ex.toString();
		}
	}
}
