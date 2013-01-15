package br.fatec.evento;

import java.lang.reflect.Array;

import br.fatec.evento.classes.Convidado;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;


public class RecepcaoActivity extends ListActivity implements OnClickListener {
	/** Called when the activity is first created. */
	
	String[] eventos = new String[] { "Evento 1","Evento 2","Evento 3"};
	String[] items1 = new String[] { "Ev 1 : Conv 1","Ev 1 : Conv 2","Ev 1 : Conv 3","Ev 1 : Conv 4", "Ev 1 : Conv 5", "Ev 1 : Conv 6"};
	String[] items2 = new String[] { "Ev 1 : Conv 1","Ev 1 : Conv 2","Ev 1 : Conv 3","Ev 1 : Conv 4", "Ev 1 : Conv 5", "Ev 1 : Conv 6"};
	String[] items3 = new String[] { "Ev 1 : Conv 1","Ev 1 : Conv 2","Ev 1 : Conv 3","Ev 1 : Conv 4", "Ev 1 : Conv 5", "Ev 1 : Conv 6"};

	
	
	Spinner listaEventos;
	
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
        
        listaEventos = (Spinner) findViewById(R.id.boxEvento);
        // -------------------------------------------
        //  buscar a lista de eventos permitidos para visualização
        // -------------------------------------------
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, eventos);
        listaEventos.setAdapter( adapter );
        listaEventos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView parent, View v, int posicao, long id) {
				
				switch (posicao) {
				case 0:
					refreshList(items1);
					break;

				case 1:
					refreshList(items2);
					break;
				case 2:
					refreshList(items3);
					break;
				default:
					break;
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView parent) {
			}
		});
        
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
		boolean temp = !check.isChecked();
		check.setChecked(temp);
		
		if ( temp )
			
		else
			
	} 
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    }
    
    protected void refreshList( String[] items ){
		this.setListAdapter( new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items));
    }
}
