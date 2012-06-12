package br.fatec.evento;

import android.app.Activity; 
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;


public class AssociarActivity extends ListActivity implements OnClickListener {
	/** Called when the activity is first created. */
	public String[] items = new String[] { "Pessoa 1","Pessoa 2","Pessoa 3",
										   "Pessoa 4","Pessoa 5","Pessoa 6"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.associar);
        
        // ------------------------------------------------
        //  Receber parâmetro para ajustar o título do evento
        // e filtrar os registro que podem ser exibidos
        // ------------------------------------------------
        
        this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked, items));
        
        ImageView btnConfirmar = (ImageView) findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(this);

    }
    
    public void onClick( View v ){
    	
    	switch (v.getId()) {
		case R.id.btnConfirmar:
	        // ------------------------------------------------
	        //  Botão confirmar com função de retornar o conteúdo
	        // selecionado para a janela anterior
	        // proporcionando a gravação como um todo do Evento
	        // ------------------------------------------------ 
			finish();
			break;

		default:
			break;
		}
    }
}
