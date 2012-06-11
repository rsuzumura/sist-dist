package br.fatec.evento;

import android.app.Activity; 
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class CadastroEventoActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	public final int INVISIBILITY = 4;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_evento);
     
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setVisibility(INVISIBILITY);
        
        Button btnGravar = (Button) findViewById(R.id.btnGravar);
        btnGravar.setOnClickListener(this);
    }
    
    public void onClick(View v) {

    	switch (v.getId()) {
		case R.id.btnGravar:
			// executar processamento de gravação
			break;

		default:
			break;
		}
    }
}
