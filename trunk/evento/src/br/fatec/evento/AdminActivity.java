package br.fatec.evento;

import android.app.Activity; 
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class AdminActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin);
        
        Button btnTipoEvento = (Button) findViewById(R.id.btnTipoEvento);
        btnTipoEvento.setOnClickListener(this);
        
        Button btnFuncoes = (Button)findViewById(R.id.btnFuncoes);
        btnFuncoes.setOnClickListener(this);
    }
    
    public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.btnTipoEvento:
    		break;
    	case R.id.btnFuncoes:
    		break;
    	}
    }
}
