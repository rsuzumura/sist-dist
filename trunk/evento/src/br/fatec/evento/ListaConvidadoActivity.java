package br.fatec.evento;

import br.fatec.evento.classes.ConvidadoService;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ListaConvidadoActivity extends ListActivity implements OnClickListener {
	/** Called when the activity is first created. */
	
	String[] items = new String[] { "Evento 1","Evento 2","Evento 3"};
	ListView listaConvidados;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_convidados);
        
    	TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
    	SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
    	String url = pref.getString("url", "");
    	ConvidadoService cs = new ConvidadoService();
    	try {    		
    		cs.URL = url;
    		cs.methodRequest = 1;
    		cs.join();
    		cs.start();
    		
    		while (cs.result == "START") {
    			Thread.sleep(10);
    		}
    	} catch (Exception ex) {
    		
    	}
    	
    	
    	txtTitulo.setText(R.string.btnConvidado);
    	this.setListAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));        

    }

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
