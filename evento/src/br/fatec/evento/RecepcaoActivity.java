package br.fatec.evento;

import br.fatec.evento.classes.Convidado;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class RecepcaoActivity extends ListActivity implements OnClickListener {
	/** Called when the activity is first created. */
	
	String[] items = new String[] { "Convidado 1","Convidado 2","Convidado 3","Convidado 4", "Convidado 5", "Convidado 6"}; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recepcao);
        
        TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtTitulo.setText(R.string.btnRecepcao);
        
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);
        
        Button btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(this);
        
        this.setListAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked, items));
        
    }
    
    public void onClick(View v) {
    	Intent it;
    	switch (v.getId()) {
		case R.id.btnInsert:
    		it = new Intent("EVENTO_CAD_CONVIDADO");
    		it.addCategory("BR_FATECSP");
    		startActivity( it ); 
			break;

		case R.id.btnConfirmar:
			//--------------------------------------
			//  Incluir os registros marcados
			// no banco, após isso recarregar
			// a lista de convidados
			//--------------------------------------
			break;
		default:
			break;
		}    	
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
    	//--------------------------------------
    	//  Executar marcação e adição dos 
    	// selecionados para lista de gravação 
    	//--------------------------------------
    	
		CheckedTextView check = (CheckedTextView) v;
		check.setChecked(!check.isChecked());
    	
	} 
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    }
}
