package br.fatec.igreja;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.fatec.igreja.classes.Convidado;
 
public class CallSoap {
    private static final String SOAP_ACTION = "http://tempuri.org/List";
    private static final String METHOD_NAME = "List";
    private static final String NAMESPACE = "http://tempuri.org/";
    private static final String URL = "http://fiveslivraria.no-ip.org:8081/wsIgreja/wsIgreja.asmx";
 
    public String Call(int idConvidado) {
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        //request.addProperty("idConvidado", idConvidado);
 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call(SOAP_ACTION, envelope);
            //SoapObject result = (SoapObject) envelope.getResponse();
            SoapObject responseObject =(SoapObject)envelope.bodyIn;
            for (int i = 0; i < responseObject.getPropertyCount(); i++){
            	//SoapObject array = (SoapObject)responseObject.getProperty(0);
            	
            }
            Convidado c = new Convidado();
            SoapObject array = (SoapObject)responseObject.getProperty(0);
            SoapObject ret = (SoapObject)array.getProperty(0);
            c.idConvidado = Integer.parseInt(((SoapPrimitive)ret.getProperty(0)).toString());
            c.nome 		  = ((SoapPrimitive)ret.getProperty(1)).toString();
            c.email 	  = ((SoapPrimitive)ret.getProperty(2)).toString();
            c.telefone 	  = ((SoapPrimitive)ret.getProperty(3)).toString();
            c.origem 	  = ((SoapPrimitive)ret.getProperty(4)).toString();
            //c.funcao 	  = ((SoapPrimitive)ret.getProperty(5)).toString();
            return c.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    
    public String Insert(String nome, String email, String telefone, String origem, String funcao) {
    	String retorno = null;
    	SoapObject request = new SoapObject(NAMESPACE, "InsertCliente");
    	request.addProperty("nome", nome);
    	request.addProperty("email", email);
    	request.addProperty("telefone", telefone);
    	request.addProperty("origem", origem);
    	request.addProperty("funcao", funcao);
    	 
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        try {
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
            androidHttpTransport.call("http://tempuri.org/InsertCliente", envelope);
            SoapPrimitive result = (SoapPrimitive) envelope.getResponse();
            retorno = result.toString();
        } catch (Exception e) {
            return e.getMessage();
        }
    	
    	return retorno;
    }
}
