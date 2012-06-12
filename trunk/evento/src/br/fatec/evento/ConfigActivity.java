package br.fatec.evento;

import android.app.Activity; 
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView.BufferType;


public class ConfigActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	protected static String EVENTO = "EVENTO";
	EditText txtNomeEmpresa;
	EditText txtUrl;
	Button btnEditar;
	
	protected boolean edicao = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
        
        String nomeEmpresa = "";
        String Url = "";

		btnEditar = (Button) findViewById(R.id.btnEditar);
		btnEditar.setOnClickListener(this);
        
		txtNomeEmpresa = (EditText) findViewById(R.id.txtNomeEmpresa);
		txtUrl = (EditText) findViewById(R.id.txtWebService);
		
		SharedPreferences pref = getSharedPreferences( EVENTO, MODE_PRIVATE);
		nomeEmpresa = pref.getString("nomeEmpresa", "");
		Url = pref.getString("url", "");
		
		if (!nomeEmpresa.equals(""))
			txtNomeEmpresa.setText(nomeEmpresa);
		
		if (!Url.equals(""))
			txtUrl.setText(Url);
		
		atualizarTela();
    }
    
    public void onClick(View v) {

    	switch (v.getId()) {
		case R.id.btnEditar:
			edicao = !edicao;
			
			if ( !edicao ) {
				SharedPreferences pref = getSharedPreferences( EVENTO, MODE_PRIVATE);
    			SharedPreferences.Editor editor = pref.edit();
    			editor.putString("nomeEmpresa", txtNomeEmpresa.getText().toString());
    			editor.putString("url", txtUrl.getText().toString());
    			editor.commit();
			}
			
			atualizarTela();
			break;

		default:
			break;
		}
    }
    
    private void atualizarTela() {
    	String lblBotao = ( !edicao ? "Editar" : "Gravar" );
    	txtNomeEmpresa.setEnabled(edicao);
    	txtUrl.setEnabled(edicao);
    	btnEditar.setText(lblBotao);
    }
}
