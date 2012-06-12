package br.fatec.evento.classes;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class ConvidadoSOAP {
	public ConvidadoSOAP(String URL)
	{
		this.URL = URL;
	}
    private static final String NAMESPACE = "http://tempuri.org/";
    private String URL;
 
    public Convidado get(int idConvidado) {
    	String METHOD_NAME = "ObterConvidado";
    	String SOAP_ACTION = "http://tempuri.org/ObterConvidado";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("idConvidado", idConvidado);
 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject responseObject =(SoapObject)envelope.bodyIn;

            Convidado c = new Convidado();
            SoapObject array = (SoapObject)responseObject.getProperty(0);
            SoapObject ret = (SoapObject)array.getProperty(0);
            c.id 	  	  = Integer.parseInt(((SoapPrimitive)ret.getProperty(0)).toString());
            c.nome 		  = ((SoapPrimitive)ret.getProperty(1)).toString();
            c.email 	  = ((SoapPrimitive)ret.getProperty(2)).toString();
            c.telefone 	  = ((SoapPrimitive)ret.getProperty(3)).toString();
            c.origem 	  = ((SoapPrimitive)ret.getProperty(4)).toString();
            return c;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Convidado> list() {
    	List<Convidado> result = new ArrayList<Convidado>();
    	String METHOD_NAME = "ObterConvidados";
    	String SOAP_ACTION = "http://tempuri.org/ObterConvidados";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject responseObject =(SoapObject)envelope.bodyIn;
            for (int i = 0; i < responseObject.getPropertyCount(); i++){
            	Convidado c = new Convidado();
                SoapObject array = (SoapObject)responseObject.getProperty(0);
                SoapObject ret = (SoapObject)array.getProperty(i);
                c.id 	  	  = Integer.parseInt(((SoapPrimitive)ret.getProperty(0)).toString());
                c.nome 		  = ((SoapPrimitive)ret.getProperty(1)).toString();
                c.email 	  = ((SoapPrimitive)ret.getProperty(2)).toString();
                c.telefone 	  = ((SoapPrimitive)ret.getProperty(3)).toString();
                c.origem 	  = ((SoapPrimitive)ret.getProperty(4)).toString();
                result.add(c);
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Convidado> listByEvento(int idEvento) {
    	List<Convidado> result = new ArrayList<Convidado>();
    	String METHOD_NAME = "ObterConvidadosPorEvento";
    	String SOAP_ACTION = "http://tempuri.org/ObterConvidadosPorEvento";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("idEvento", idEvento);
 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject responseObject =(SoapObject)envelope.bodyIn;
            for (int i = 0; i < responseObject.getPropertyCount(); i++){
            	Convidado c = new Convidado();
                SoapObject array = (SoapObject)responseObject.getProperty(0);
                SoapObject ret = (SoapObject)array.getProperty(i);
                c.id 	  	  = Integer.parseInt(((SoapPrimitive)ret.getProperty(0)).toString());
                c.nome 		  = ((SoapPrimitive)ret.getProperty(1)).toString();
                c.email 	  = ((SoapPrimitive)ret.getProperty(2)).toString();
                c.telefone 	  = ((SoapPrimitive)ret.getProperty(3)).toString();
                c.origem 	  = ((SoapPrimitive)ret.getProperty(4)).toString();
                result.add(c);
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean Save(int id, String nome, String email, String telefone, String origem) {
    	boolean retorno = false;
    	String METHOD_NAME = "AlterarConvidado";
    	String SOAP_ACTION = "http://tempuri.org/AlterarConvidado";
    	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    	request.addProperty("id", id);
    	request.addProperty("nome", nome);
    	request.addProperty("email", email);
    	request.addProperty("telefone", telefone);
    	request.addProperty("origem", origem);
    	 
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
    
    public int Add(String nome, String email, String telefone, String origem) {
    	int retorno = 0;
    	String METHOD_NAME = "IncluirConvidado";
    	String SOAP_ACTION = "http://tempuri.org/IncluirConvidado";
    	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    	request.addProperty("nome", nome);
    	request.addProperty("email", email);
    	request.addProperty("telefone", telefone);
    	request.addProperty("origem", origem);
    	 
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
