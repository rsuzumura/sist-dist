package br.fatec.evento;

import java.util.List;

import br.fatec.evento.classes.Convidado;
import br.fatec.evento.classes.ConvidadoSOAP;
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


public class ListaConvidadoActivity extends ListActivity implements OnClickListener {
	/** Called when the activity is first created. */
	
	//String[] items = new String[] { "Evento 1","Evento 2","Evento 3"};
	String[] items;
	ListView listaConvidados;
	List<Convidado> convidados;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_convidados);
        
    	TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
    	SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
    	String url = pref.getString("url", "");
    	ConvidadoSOAP cs = new ConvidadoSOAP(url);
    	convidados = cs.list();
    	
    	if (convidados != null) {
    		items = new String[convidados.size()];
    		int i = 0;
    		for (Convidado conv : convidados) {
				items[i] = conv.nome;
				i++;
			}
    	}
    	
    	ImageView btnInsert = (ImageView)findViewById(R.id.btnInsert);
    	btnInsert.setOnClickListener(this);
    	txtTitulo.setText(R.string.btnConvidado);
    	if (items != null)
    		this.setListAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
    	else {
    		this.setListAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new String[]{ }));
    	}

    }

    public void onClick(View v) {
    	Intent it = new Intent("EVENTO_CAD_CONVIDADO");
   	 	it.addCategory("BR_FATECSP");
   	 	startActivity( it );
	}

    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
    	Convidado c = convidados.get(position);
    	Intent it = new Intent("EVENTO_CAD_CONVIDADO");
		it.putExtra("idConvidado", c.id);
		it.putExtra("nome", c.nome);
		it.putExtra("email", c.email);
		it.putExtra("telefone", c.telefone);
		it.putExtra("origem", c.origem);
		it.addCategory("BR_FATECSP");
		startActivity(it);
	}
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
    	String url = pref.getString("url", "");
    	ConvidadoSOAP cs = new ConvidadoSOAP(url);
    	convidados = cs.list();
    	
    	if (convidados != null) {
    		items = new String[convidados.size()];
    		int i = 0;
    		for (Convidado conv : convidados) {
				items[i] = conv.nome;
				i++;
			}
    	}
    	if (items != null)
    		this.setListAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
    	else {
    		this.setListAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new String[]{ }));
    	}
    }
}
