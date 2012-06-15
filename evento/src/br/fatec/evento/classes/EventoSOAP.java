package br.fatec.evento.classes;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class EventoSOAP {
	public EventoSOAP(String URL)
	{
		this.URL = URL;
	}
	private static final String NAMESPACE = "http://tempuri.org/";
    private String URL;
 
    public List<Evento> list() {
    	String METHOD_NAME = "ObterEventos";
    	String SOAP_ACTION = "http://tempuri.org/ObterEventos";
    	List<Evento> result = new ArrayList<Evento>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject responseObject =(SoapObject)envelope.bodyIn;
            SoapObject array = (SoapObject)responseObject.getProperty(0);
            
            for (int i = 0; i < array.getPropertyCount(); i++){
            	Evento e = new Evento();
            	
            	SoapObject ret = (SoapObject)array.getProperty(i);
            	e.id 	  	   = Integer.parseInt(((SoapPrimitive)ret.getProperty(0)).toString());
            	e.nome 		   = (String)ret.getPrimitivePropertySafely("Nome");
            	e.local 	   = (String)ret.getPrimitivePropertySafely("Local");
            	e.responsavel  = (String)ret.getPrimitivePropertySafely("Responsavel");
            	e.inicioEvento = (String)ret.getPrimitivePropertySafely("InicioEvento");
            	e.fimEvento    = (String)ret.getPrimitivePropertySafely("FimEvento");
            	e.status 	   = (String)ret.getPrimitivePropertySafely("Status");
            	e.tipoEventoId = Integer.parseInt((String)ret.getPrimitivePropertySafely("TipoEventoId"));
            	result.add(e);
            }
            
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Evento> listByTipoEvento(int tipoEventoId) {
    	String METHOD_NAME = "ObterEventosPorTipoEvento";
    	String SOAP_ACTION = "http://tempuri.org/ObterEventosPorTipoEvento";
    	List<Evento> result = new ArrayList<Evento>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("tipoEventoId", tipoEventoId);
 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject responseObject =(SoapObject)envelope.bodyIn;
            SoapObject array = (SoapObject)responseObject.getProperty(0);
            
            for (int i = 0; i < array.getPropertyCount(); i++){
            	Evento e = new Evento();            	
            	SoapObject ret = (SoapObject)array.getProperty(i);
            	e.id 	  	   = Integer.parseInt(((SoapPrimitive)ret.getProperty(0)).toString());
            	e.nome 		   = (String)ret.getPrimitivePropertySafely("Nome");
            	e.local 	   = (String)ret.getPrimitivePropertySafely("Local");
            	e.responsavel  = (String)ret.getPrimitivePropertySafely("Responsavel");
            	e.inicioEvento = (String)ret.getPrimitivePropertySafely("InicioEvento");
            	e.fimEvento    = (String)ret.getPrimitivePropertySafely("FimEvento");
            	e.status 	   = (String)ret.getPrimitivePropertySafely("Status");
            	e.tipoEventoId = (Integer)ret.getPrimitivePropertySafely("TipoEventoId");
            	result.add(e);
            }
            
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Evento> listByConvidado(int idConvidado) {
    	String METHOD_NAME = "ObterEventosPorConvidado";
    	String SOAP_ACTION = "http://tempuri.org/ObterEventosPorConvidado";
    	List<Evento> result = new ArrayList<Evento>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("convidadoId", idConvidado);
 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject responseObject =(SoapObject)envelope.bodyIn;
            SoapObject array = (SoapObject)responseObject.getProperty(0);
            
            for (int i = 0; i < array.getPropertyCount(); i++){
            	Evento e = new Evento();            	
            	SoapObject ret = (SoapObject)array.getProperty(i);
            	e.id 	  	   = Integer.parseInt(((SoapPrimitive)ret.getProperty(0)).toString());
            	e.nome 		   = (String)ret.getPrimitivePropertySafely("Nome");
            	e.local 	   = (String)ret.getPrimitivePropertySafely("Local");
            	e.responsavel  = (String)ret.getPrimitivePropertySafely("Responsavel");
            	e.inicioEvento = (String)ret.getPrimitivePropertySafely("InicioEvento");
            	e.fimEvento    = (String)ret.getPrimitivePropertySafely("FimEvento");
            	e.status 	   = (String)ret.getPrimitivePropertySafely("Status");
            	e.tipoEventoId = (Integer)ret.getPrimitivePropertySafely("TipoEventoId");
            	result.add(e);
            }
            
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Evento> listByNome(String nome) {
    	String METHOD_NAME = "ObterEventosPorNome";
    	String SOAP_ACTION = "http://tempuri.org/ObterEventosPorNome";
    	List<Evento> result = new ArrayList<Evento>();
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("nome", nome);
 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject responseObject =(SoapObject)envelope.bodyIn;
            SoapObject array = (SoapObject)responseObject.getProperty(0);
            for (int i = 0; i < array.getPropertyCount(); i++){
            	Evento e = new Evento();            	
            	SoapObject ret = (SoapObject)array.getProperty(i);
            	e.id 	  	   = Integer.parseInt(((SoapPrimitive)ret.getProperty(0)).toString());
            	e.nome 		   = (String)ret.getPrimitivePropertySafely("Nome");
            	e.local 	   = (String)ret.getPrimitivePropertySafely("Local");
            	e.responsavel  = (String)ret.getPrimitivePropertySafely("Responsavel");
            	e.inicioEvento = (String)ret.getPrimitivePropertySafely("InicioEvento");
            	e.fimEvento    = (String)ret.getPrimitivePropertySafely("FimEvento");
            	e.status 	   = (String)ret.getPrimitivePropertySafely("Status");
            	e.tipoEventoId = (Integer)ret.getPrimitivePropertySafely("TipoEventoId");
            	result.add(e);
            }
            
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean Save(int id, String nome, String local, String responsavel, String inicioEvento, String fimEvento, String status, int tipoEventoId) {
    	boolean retorno = false;
    	String METHOD_NAME = "AlterarEvento";
    	String SOAP_ACTION = "http://tempuri.org/AlterarEvento";
    	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    	request.addProperty("id", id);
    	request.addProperty("nome", nome);
    	request.addProperty("local", local);
    	request.addProperty("responsavel", responsavel);
    	request.addProperty("inicioEvento", inicioEvento);
    	request.addProperty("fimEvento", fimEvento);
    	request.addProperty("status", status);
    	request.addProperty("tipoEventoId", tipoEventoId);
    	 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            retorno = Boolean.parseBoolean(result.toString());
        } catch (Exception e) {
            return false;
        }
    	
    	return retorno;
    }
    
    public int Add(String nome, String local, String responsavel, String inicioEvento, String fimEvento, String status, int tipoEventoId) {
    	int retorno = 0;
    	String METHOD_NAME = "IncluirEvento";
    	String SOAP_ACTION = "http://tempuri.org/IncluirEvento";
    	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    	request.addProperty("nome", nome);
    	request.addProperty("local", local);
    	request.addProperty("responsavel", responsavel);
    	request.addProperty("inicioEvento", inicioEvento);
    	request.addProperty("fimEvento", fimEvento);
    	request.addProperty("status", status);
    	request.addProperty("tipoEventoId", tipoEventoId);
    	 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            retorno = Integer.parseInt(result.toString());
        } catch (Exception e) {
            return 0;
        }
    	
    	return retorno;
    }
}
