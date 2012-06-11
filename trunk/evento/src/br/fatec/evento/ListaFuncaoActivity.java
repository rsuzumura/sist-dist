package br.fatec.evento;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;


public class ListaFuncaoActivity extends ListActivity implements OnClickListener {
	/** Called when the activity is first created. */
	
	public String[] items = new String[] { "Função 1","Função 2","Função 3"};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_funcao);
        
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);
        
        this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));

    }
    
    @Override
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
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) 
    {
    	Intent it = new Intent("EVENTO_CAD_FUNCAO");
		it.putExtra("Codigo", position);
		it.addCategory("BR_FATECSP");
		startActivity( it );
    }
}
