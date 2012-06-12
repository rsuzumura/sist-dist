package br.fatec.evento;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class AssociarActivity extends ListActivity implements OnClickListener {
	/** Called when the activity is first created. */
	public String[] items = new String[] { "Pessoa 1","Pessoa 2","Pessoa 3",
										   "Pessoa 4","Pessoa 5","Pessoa 6"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.associar);
        
        // ------------------------------------------------
        //  Receber par�metro para ajustar o t�tulo do evento
        // e filtrar os registro que podem ser exibidos
        // ------------------------------------------------
        
        String nomeEvento = "";
        
		Bundle params = getIntent().getExtras();
		if ( params != null ) {
			nomeEvento = params.getString("nome");
        }
        
        this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked, items));
        
        TextView lblNomeEvento = (TextView) findViewById(R.id.lblNomeEvento);
        lblNomeEvento.setText(nomeEvento);
        
        ImageView btnConfirmar = (ImageView) findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(this);

    }
    
    public void onClick( View v ){
    	
    	switch (v.getId()) {
		case R.id.btnConfirmar:
	        // ------------------------------------------------
	        //  Bot�o confirmar com fun��o de retornar o conte�do
	        // selecionado para a janela anterior
	        // proporcionando a grava��o como um todo do Evento
	        // ------------------------------------------------ 
			finish();
			break;

		default:
			break;
		}
    }
    
	protected void onListItemClick(ListView l, View v, int position, long id) 
    {
		CheckedTextView check = (CheckedTextView) v;
		check.setChecked(!check.isChecked());
		// ------------------------------------------------------------
		//  Inserir tratamento para inclus�o em vari�veis auxiliares
		// ------------------------------------------------------------
    }
}
