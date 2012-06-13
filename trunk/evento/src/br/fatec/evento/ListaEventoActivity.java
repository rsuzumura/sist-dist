package br.fatec.evento;

import java.util.List;

import br.fatec.evento.classes.Evento;
import br.fatec.evento.classes.EventoSOAP;

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


public class ListaEventoActivity extends ListActivity implements OnClickListener {
	/** Called when the activity is first created. */
	//public String[] items = new String[] { "Evento 1","Evento 2","Evento 3"};
	public String[] items;
	List<Evento> eventos;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_evento);
        
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);
        
    	TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
    	txtTitulo.setText(R.string.btnEvento);
    	
    	SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
    	String url = pref.getString("url", "");
    	EventoSOAP es = new EventoSOAP(url);
        eventos = es.list();
        
        if (eventos != null) {
    		items = new String[eventos.size()];
    		int i = 0;
    		for (Evento ev : eventos) {
				items[i] = ev.nome;
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
			it = new Intent("EVENTO_CAD_EVENTO");
			it.addCategory("BR_FATECSP");
			startActivity( it );
			break;

		default:
			break;
		}
    }
    
	protected void onListItemClick(ListView l, View v, int position, long id) 
    {
    	Intent it = new Intent("EVENTO_EVENTO");
    	Evento evento = eventos.get(position);
    	
		it.putExtra("id", evento.id);
		it.putExtra("nome", evento.nome);
		it.putExtra("local", evento.local);
		it.putExtra("responsavel", evento.responsavel);
		it.putExtra("tipoEventoId", evento.tipoEventoId);
		it.putExtra("inicioEvento", evento.inicioEvento);
		it.putExtra("fimEvento", evento.fimEvento);
		
		it.addCategory("BR_FATECSP");
		startActivity( it );
    }
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
    	String url = pref.getString("url", "");
    	EventoSOAP es = new EventoSOAP(url);
        eventos = es.list();
        
        if (eventos != null) {
    		items = new String[eventos.size()];
    		int i = 0;
    		for (Evento ev : eventos) {
				items[i] = ev.nome;
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
