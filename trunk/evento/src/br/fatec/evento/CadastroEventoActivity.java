package br.fatec.evento;

import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class CadastroEventoActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	public final int INVISIBILITY = 4;
	final private int RETORNO = 1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_evento);
     
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setVisibility(INVISIBILITY);
        
        Button btnGravar = (Button) findViewById(R.id.btnGravar);
        btnGravar.setOnClickListener(this);
        
        Button btnAssociar = (Button) findViewById(R.id.btnAssociar);
        btnAssociar.setOnClickListener(this);
    }
    
    public void onClick(View v) {
    	Intent it;
    	switch (v.getId()) {
		case R.id.btnGravar:
			// executar processamento de gravação
			break;
		case R.id.btnAssociar:
			it = new Intent("EVENTO_ASSOCIAR");
			it.putExtra("idEvento", "Evento Selec");
			it.addCategory("BR_FATECSP");
			startActivityForResult( it, RETORNO );
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
				// -------------------------------
				// Problema? como identificar os 
				// diversos convidados selecionados
				// -------------------------------
			}
		}
    }
}
