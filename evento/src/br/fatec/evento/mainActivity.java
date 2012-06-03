package br.fatec.evento;

import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class mainActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	public final int CONFIG = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btnAdmin = (Button) findViewById(R.id.btnAdmin);
        btnAdmin.setOnClickListener(this);
        
        Button btnEvento = (Button) findViewById(R.id.btnEvento);
        btnEvento.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
    	Intent it;
    	switch (v.getId()) {
    	case R.id.btnAdmin :  
    		// executar intent com chamada de tela para cadastro de tipos de eventos e funções
    		it = new Intent("EVENTO_ADMIN");
    		it.addCategory("BR_FATECSP");
//    		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		startActivity( it );
    		break; 
    	case R.id.btnEvento : 
    		// executar intent com chamada de tela para registro dos convidados ou ligação com eventos
    		it = new Intent("EVENTO_ACOES");
    		it.addCategory("BR_FATECSP");
//    		it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    		startActivity( it ); 
    		break; 
    	}
    }
    
    @Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
		// Adiciona opção ABOUT AO MENU
		menu.add(0, CONFIG, 0, "Configurações");
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		Intent it;
		switch (item.getItemId()) {
		case CONFIG:
			it = new Intent("EVENTO_CONFIG");
			startActivity(it);
			return true;
		default: 
			return true;
		}
	}
}
