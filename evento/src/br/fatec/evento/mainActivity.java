package br.fatec.evento;

import android.app.Activity; 
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


public class mainActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.btnAdmin : 
    		// executar intent com chamada de tela para cadastro de tipos de eventos e funções
    		break;
    	case R.id.btnEvento :
    		// executar intent com chamada de tela para registro dos convidados ou ligação com eventos    		
    		break;		  
    	}
    }
}
