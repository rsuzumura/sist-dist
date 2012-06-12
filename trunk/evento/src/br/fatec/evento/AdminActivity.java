package br.fatec.evento;

import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class AdminActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        
        TextView btnTipoEvento = (TextView) findViewById(R.id.btnTipoEvento);
        btnTipoEvento.setOnClickListener(this);
        
        TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtTitulo.setText(R.string.txtParam);
        
        TextView btnFuncoes = (TextView) findViewById(R.id.btnFuncoes);
        btnFuncoes.setOnClickListener(this);

    }
    
    public void onClick(View v) {
    	Intent it;
    	
    	switch (v.getId()) {

    	case R.id.btnTipoEvento:
    		it = new Intent("EVENTO_LISTA_TIPOEVENTO");
    		it.addCategory("BR_FATECSP");
    		startActivity( it ); 
			break;

    	case R.id.btnFuncoes:
    		it = new Intent("EVENTO_LISTA_FUNCAO");
    		it.addCategory("BR_FATECSP");
    		startActivity( it ); 
			break;
			
		default:
			break;
		}
    }
}
