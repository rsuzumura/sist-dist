package br.fatec.evento;

import android.app.Activity; 
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class ListaConvidadoActivity extends ListActivity implements OnClickListener {
	/** Called when the activity is first created. */
	
	String[] items = new String[] { "Evento 1","Evento 2","Evento 3"};
	ListView listaConvidados; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_convidados);
        
        
    	this.setListAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));        

    }

    @Override
    public void onClick(View v) {

		}
/*
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		switch (position) {
			case 0:
				startActivity(new Intent(this,MenuBasicoLayouts.class));
				break;
			case 1:
				//Aplicação estilo File Explorer para navegar nas pastas
				startActivity(new Intent(this,MenuLayouts.class));
				break;
			case 2:
				startActivity(new Intent(this,MenuLayouts2.class));
				break;
		}
	}
*/
}
