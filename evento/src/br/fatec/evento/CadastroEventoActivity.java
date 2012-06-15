package br.fatec.evento;

import java.util.List;

import br.fatec.evento.classes.EventoSOAP;
import br.fatec.evento.classes.TipoEvento;
import br.fatec.evento.classes.TipoEventoSOAP;

import android.app.Activity; 
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


public class CadastroEventoActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	public final int INVISIBILITY = 4;
	final private int RETORNO = 1;
	List<TipoEvento> tiposEvento;
	int idEvento;
	int idTipoEvento;
	String[] items;
	
	EditText txtNomeEvento;
	EditText txtLocal;
	EditText txtResponsavel;
	EditText txtDataInicial;
	EditText txtDataFinal;
	Spinner listaTipoEventos;
	Spinner listaStatus;
	//--------------------------------------
	//
	//-------------------------------------
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_evento);
     
        txtNomeEvento 	 = (EditText)findViewById(R.id.txtNomeEvento);
        txtLocal 		 = (EditText)findViewById(R.id.txtLocal);
        txtResponsavel 	 = (EditText)findViewById(R.id.txtResponsavel);
        txtDataInicial 	 = (EditText)findViewById(R.id.txtDataInicial);
        txtDataFinal 	 = (EditText)findViewById(R.id.txtDataFinal);
        listaTipoEventos = (Spinner)findViewById(R.id.listaTipoEventos);
        listaStatus 	 = (Spinner)findViewById(R.id.listaStatus);
        
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setVisibility(INVISIBILITY);
        btnInsert.setClickable(false);
        
        Bundle params = getIntent().getExtras();
		if ( params != null ) {
			idEvento = params.getInt("id");
			txtNomeEvento.setText(params.getString("nome"));
			txtLocal.setText(params.getString("local"));
			txtResponsavel.setText(params.getString("responsavel"));
			
			txtDataInicial.setText(params.getString("inicioEvento"));
			txtDataFinal.setText(params.getString("fimEvento"));
			listaStatus.setSelection((params.getString("status")).equalsIgnoreCase("aberto") ? 0 : 1);
			idTipoEvento = params.getInt("idTipoEvento");
		}
        
        TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtTitulo.setText(R.string.txtCadastroEvento);
        SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
    	String url = pref.getString("url", "");
        TipoEventoSOAP tes = new TipoEventoSOAP(url);
        tiposEvento = tes.list();
        int pos = 0;
        if (tiposEvento != null) {
    		items = new String[tiposEvento.size()];    		
    		int i = 0;
    		for (TipoEvento tpEv : tiposEvento) {
    			if (tpEv.id == idTipoEvento)
    				pos = i;
				items[i] = tpEv.nome;
				i++;
			}
    	}
    	if (items == null)
    		items = new String[]{};
        
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        listaTipoEventos.setAdapter(adapter);
        listaTipoEventos.setSelection(pos);
        Button btnGravar = (Button) findViewById(R.id.btnGravar);
        btnGravar.setOnClickListener(this);
        
        Button btnAssociar = (Button) findViewById(R.id.btnAssociar);
        btnAssociar.setOnClickListener(this);
    }
    
    public void onClick(View v) {
    	Intent it;
    	AlertDialog dialog = new AlertDialog.Builder(this).create();
    	boolean success = false;
    	
    	txtNomeEvento 	 = (EditText)findViewById(R.id.txtNomeEvento);
        txtLocal 		 = (EditText)findViewById(R.id.txtLocal);
        txtResponsavel 	 = (EditText)findViewById(R.id.txtResponsavel);
        txtDataInicial 	 = (EditText)findViewById(R.id.txtDataInicial);
        txtDataFinal 	 = (EditText)findViewById(R.id.txtDataFinal);
        listaTipoEventos = (Spinner)findViewById(R.id.listaTipoEventos);
        listaStatus 	 = (Spinner)findViewById(R.id.listaStatus);
        TipoEvento tpEv  = tiposEvento.get(listaTipoEventos.getSelectedItemPosition());
    	
        switch (v.getId()) {
		case R.id.btnGravar:
			try {
				SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
		    	String url = pref.getString("url", "");
		    	EventoSOAP es = new EventoSOAP(url);
		    	if (idEvento == 0) {
		    		idEvento = es.Add(txtNomeEvento.getText().toString(),
		    				txtLocal.getText().toString(),
		    				txtResponsavel.getText().toString(),
		    				txtDataInicial.getText().toString(),
		    				txtDataFinal.getText().toString(),
		    				listaStatus.getSelectedItem().toString(),
		    				tpEv.id);
		    		if (idEvento != 0) {
	    				dialog.setTitle("Cadastro efetuado com sucesso");
	    				dialog.setMessage(getString(R.string.msgOK));
	    				dialog.setIcon(android.R.drawable.ic_dialog_info);
	    				success = true;
	    			} else {
	    				dialog.setMessage("Erro no Cadastro do Evento.");
	    				dialog.setTitle(R.string.msgError);
	    				dialog.setIcon(android.R.drawable.ic_dialog_alert);
	    			}
		    	} else {
		    		boolean updated = es.Save(idEvento,
		    				txtNomeEvento.getText().toString(),
		    				txtLocal.getText().toString(),
		    				txtResponsavel.getText().toString(),
		    				txtDataInicial.getText().toString(),
		    				txtDataFinal.getText().toString(),
		    				listaStatus.getSelectedItem().toString(),
		    				tpEv.id);
		    		if (updated) {
	    				dialog.setTitle("Cadastro alterado com sucesso");
	    				dialog.setMessage(getString(R.string.msgOK));
	    				dialog.setIcon(android.R.drawable.ic_dialog_info);
	    				success = true;
	    			} else {
	    				dialog.setMessage("Erro na Alteração do Evento.");
	    				dialog.setTitle(R.string.msgError);
	    				dialog.setIcon(android.R.drawable.ic_dialog_alert);
	    			}
		    	}
			} catch (Exception ex) {
				dialog.setMessage("Erro no Cadastro do Evento.");
				dialog.setTitle(R.string.msgError);
				dialog.setIcon(android.R.drawable.ic_dialog_alert);
			}
			
			dialog.setButton("OK", new DialogInterface.OnClickListener() {
	    		   public void onClick(DialogInterface dialog, int which) {
	    		      dialog.cancel();
	    		   }
	    		});
	    	dialog.show();
	    	if (success)
	    		this.finish();
			break;
		case R.id.btnAssociar:
			try {
				SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
		    	String url = pref.getString("url", "");
		    	EventoSOAP es = new EventoSOAP(url);
		    	if (idEvento == 0) {
		    		idEvento = es.Add(txtNomeEvento.getText().toString(),
		    				txtLocal.getText().toString(),
		    				txtResponsavel.getText().toString(),
		    				txtDataInicial.getText().toString(),
		    				txtDataFinal.getText().toString(),
		    				listaStatus.getSelectedItem().toString(),
		    				tpEv.id);
		    		if (idEvento != 0) {
	    				dialog.setTitle("Cadastro efetuado com sucesso");
	    				dialog.setMessage(getString(R.string.msgOK));
	    				dialog.setIcon(android.R.drawable.ic_dialog_info);
	    				success = true;
	    			} else {
	    				dialog.setMessage("Erro no Cadastro do Evento.");
	    				dialog.setTitle(R.string.msgError);
	    				dialog.setIcon(android.R.drawable.ic_dialog_alert);
	    			}
		    	} else {
		    		boolean updated = es.Save(idEvento,
		    				txtNomeEvento.getText().toString(),
		    				txtLocal.getText().toString(),
		    				txtResponsavel.getText().toString(),
		    				txtDataInicial.getText().toString(),
		    				txtDataFinal.getText().toString(),
		    				listaStatus.getSelectedItem().toString(),
		    				tpEv.id);		    		
		    		if (updated) {
	    				dialog.setTitle("Cadastro alterado com sucesso");
	    				dialog.setMessage(getString(R.string.msgOK));
	    				dialog.setIcon(android.R.drawable.ic_dialog_info);
	    				success = true;
	    			} else {
	    				dialog.setMessage("Erro na Alteração do Evento.");
	    				dialog.setTitle(R.string.msgError);
	    				dialog.setIcon(android.R.drawable.ic_dialog_alert);
	    			}
		    		
		    		dialog.setButton("OK", new DialogInterface.OnClickListener() {
		     		   public void onClick(DialogInterface dialog, int which) {
		     		      dialog.cancel();
		     		   }
		     		});
		     	dialog.show();
		    	}
			} catch (Exception ex) {
				dialog.setMessage("Erro no Cadastro do Evento.");
				dialog.setTitle(R.string.msgError);
				dialog.setIcon(android.R.drawable.ic_dialog_alert);
			}
	    	dialog.show();
	    	if (success) {
	    		it = new Intent("EVENTO_ASSOCIAR");
	    		it.putExtra("id", idEvento);
	    		it.putExtra("nome", txtNomeEvento.getText().toString());
	    		it.addCategory("BR_FATECSP");
	    		startActivityForResult( it, RETORNO );
	    	}
			break;
		default:
			break;
		}
    }
    
    @Override
	protected void onActivityResult(int codigo, int resultado, Intent it) {
		super.onActivityResult(codigo, resultado, it);

		if (codigo == RETORNO) {
			if (it !=null) {
				// ---------------------------------
				// Problema? como identificar os 
				// diversos convidados selecionados
				// ---------------------------------
			}
		}
    }
}
