package br.fatec.evento;

import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class AcoesActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acoes);
        
        TextView btnConvidado = (TextView) findViewById(R.id.btnConvidado);
        btnConvidado.setOnClickListener(this);
        
        TextView btnEvento = (TextView) findViewById(R.id.btnEvento);
        btnEvento.setOnClickListener(this);
    }
    
    public void onClick(View v) {
    	Intent it;
    	switch (v.getId()) {
    	case R.id.btnConvidado :
    		it = new Intent("EVENTO_CONVIDADO");
    		it.addCategory("BR_FATECSP");
    		startActivity( it );
    		break;

    	case R.id.btnEvento :
    		it = new Intent("EVENTO_EVENTO");
    		it.addCategory("BR_FATECSP");
    		startActivity( it );
    		break;

    	}
    }
}
