package br.fatec.evento;

import android.app.Activity; 
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class EventoActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	
	public String[] items = new String[] { "Evento 1","Evento 2","Evento 3"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento);
        
        int pos = -1;
        String nomeItemSelecionado = "";
		Bundle params = getIntent().getExtras();
        
		if ( params != null ) {
			pos = params.getInt("Codigo");
        	nomeItemSelecionado = items[pos];
        }
        
        Button btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(this);
        
        Button btnAssociar = (Button) findViewById(R.id.btnAssociar);
        btnAssociar.setOnClickListener(this);
        
        TextView txtNome = (TextView) findViewById(R.id.txtNomeEvento);
        if (!nomeItemSelecionado.equals("") )
        	txtNome.setText(nomeItemSelecionado);
        
    }
    
    public void onClick(View v) {
    	
    	switch (v.getId()) {
		case R.id.btnEditar:
			
			break;

		case R.id.btnAssociar:
			
			break;
			
		default:
			break;
		}
    }
}
