package br.fatec.evento;

import java.util.List;

import br.fatec.evento.classes.TipoEvento;
import br.fatec.evento.classes.TipoEventoSOAP;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class ListaTipoEventoActivity extends ListActivity implements OnClickListener {
	/** Called when the activity is first created. */
	//public String[] items = new String[] { "Tipo Evento 1","Tipo Evento 2","Tipo Evento 3"};
	public String[] items;
	List<TipoEvento> tiposEvento;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_tipo_evento);
        
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setVisibility(0);
        btnInsert.setOnClickListener(this);
        
    	TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
    	txtTitulo.setText(R.string.btnTipoEvento);
        
    	SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
    	String url = pref.getString("url", "");
    	TipoEventoSOAP tes = new TipoEventoSOAP(url);
    	tiposEvento = tes.list();
    	
    	if (tiposEvento != null) {
    		items = new String[tiposEvento.size()];
    		int i = 0;
    		for (TipoEvento tipEv : tiposEvento) {
				items[i] = tipEv.nome;
				i++;
			}
    	}
    	if (items != null)
    		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
    	else {
    		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new String[]{ }));
    	}
    }
    
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
    
	protected void onListItemClick(ListView l, View v, int position, long id) 
    {
    	Intent it = new Intent("EVENTO_CAD_TIPOEVENTO");
    	TipoEvento tipoEvento = tiposEvento.get(position);
		it.putExtra("id", tipoEvento.id);
		it.putExtra("nome", tipoEvento.nome);
		it.addCategory("BR_FATECSP");
		startActivity( it );
    }
    
    public String[] getItems()
    {
    	return (this.items);
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
    	String url = pref.getString("url", "");
    	TipoEventoSOAP tes = new TipoEventoSOAP(url);
    	tiposEvento = tes.list();
    	
    	if (tiposEvento != null) {
    		items = new String[tiposEvento.size()];
    		int i = 0;
    		for (TipoEvento tipEv : tiposEvento) {
				items[i] = tipEv.nome;
				i++;
			}
    	}
    	if (items != null)
    		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
    	else {
    		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new String[]{ }));
    	}
    }
}
