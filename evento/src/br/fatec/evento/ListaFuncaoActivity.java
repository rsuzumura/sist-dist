package br.fatec.evento;

import java.util.List;

import br.fatec.evento.classes.Funcao;
import br.fatec.evento.classes.FuncaoSOAP;
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


public class ListaFuncaoActivity extends ListActivity implements OnClickListener {
	/** Called when the activity is first created. */
	List<Funcao> funcoes;
	//public String[] items = new String[] { "Função 1","Função 2","Função 3"};
	public String[] items;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_funcao);
        
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);
        
    	TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
    	txtTitulo.setText(R.string.btnFuncoes);
    	
    	SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
    	String url = pref.getString("url", "");
    	FuncaoSOAP fs = new FuncaoSOAP(url);
        funcoes = fs.list();
    	
        if (funcoes != null) {
    		items = new String[funcoes.size()];
    		int i = 0;
    		for (Funcao func : funcoes) {
				items[i] = func.nome;
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
    		it = new Intent("EVENTO_CAD_FUNCAO");
    		it.addCategory("BR_FATECSP");
    		startActivity( it ); 
			break;

		default:
			break;
		}

    }
    
	protected void onListItemClick(ListView l, View v, int position, long id) 
    {
    	Intent it = new Intent("EVENTO_CAD_FUNCAO");
    	Funcao func = funcoes.get(position);
		it.putExtra("id", func.id);
		it.putExtra("nome", func.nome);
		it.addCategory("BR_FATECSP");
		startActivity( it );
    }
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
    	String url = pref.getString("url", "");
    	FuncaoSOAP fs = new FuncaoSOAP(url);
        funcoes = fs.list();
    	
        if (funcoes != null) {
    		items = new String[funcoes.size()];
    		int i = 0;
    		for (Funcao func : funcoes) {
				items[i] = func.nome;
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
