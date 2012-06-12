package br.fatec.evento.classes;

import java.util.ArrayList;
import java.util.List;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class TipoEventoSOAP {
	public TipoEventoSOAP(String URL)
	{
		this.URL = URL;
	}
    private static final String NAMESPACE = "http://tempuri.org/";
    private String URL;
         
    public List<TipoEvento> list() {
    	List<TipoEvento> result = new ArrayList<TipoEvento>();
    	String METHOD_NAME = "ObterTiposEvento";
    	String SOAP_ACTION = "http://tempuri.org/ObterTiposEvento";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            SoapObject responseObject =(SoapObject)envelope.bodyIn;
            SoapObject array = (SoapObject)responseObject.getProperty(0);
            
            for (int i = 0; i < responseObject.getPropertyCount(); i++){
            	TipoEvento te = new TipoEvento();                
                SoapObject ret = (SoapObject)array.getProperty(i);
                te.id 	= Integer.parseInt(((SoapPrimitive)ret.getProperty(0)).toString());
                te.nome = ((SoapPrimitive)ret.getProperty(1)).toString();
                result.add(te);
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    
    public boolean Save(int id, String nome) {
    	boolean retorno = false;
    	String METHOD_NAME = "AlterarTipoEvento";
    	String SOAP_ACTION = "http://tempuri.org/AlterarTipoEvento";
    	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    	request.addProperty("id", id);
    	request.addProperty("nome", nome);
    	 
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
    
    public int Add(String nome) {
    	int retorno = 0;
    	String METHOD_NAME = "IncluirTipoEvento";
    	String SOAP_ACTION = "http://tempuri.org/IncluirTipoEvento";
    	SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
    	request.addProperty("nome", nome);
    	 
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
