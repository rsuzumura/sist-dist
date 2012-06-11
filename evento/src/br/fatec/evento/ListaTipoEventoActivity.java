package br.fatec.evento;

import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


public class ListaTipoEventoActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	public String[] items = new String[] { "Tipo Evento 1","Tipo Evento 2","Tipo Evento 3"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_tipo_evento);
        
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setVisibility(0);
        
        ListView lista = (ListView) findViewById(R.id.listaTipoEventos);
        
        lista.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
        lista.setOnItemClickListener(new OnItemClickListener() {
        	@Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	Intent it = new Intent("EVENTO_CAD_TIPOEVENTO");
	    		it.putExtra("Codigo", position);
	    		it.addCategory("BR_FATECSP");
	    		startActivity( it );
            }
        } );

    }
    
    @Override
    public void onClick(View v) {
    	Intent it;
    	
    	switch (v.getId()) {

    	case R.id.btnInsert:
    		it = new Intent("EVENTO_CAD_TIPOEVENTO");
    		it.addCategory("BR_FATECSP");
    		startActivity( it ); 
			break;

		default:
			break;
		}
    }
    
    public String[] getItems()
    {
    	return (this.items);
    }
   
}
