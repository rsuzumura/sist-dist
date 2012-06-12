package br.fatec.evento;

import android.app.Activity; 
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


public class EventoActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	
	public String[] items = new String[] { "Evento 1","Evento 2","Evento 3"};
	public final int RETORNO = 0;
	Button btnAssociar;
	Button btnEditar;
	
	//-----------------------------
	// campos da interface
	//-----------------------------
	TextView txtNome;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento);
        
        int pos = -1;
        String nomeItemSelecionado = "";
		Bundle params = getIntent().getExtras();
        
		if ( params != null ) {
			pos = params.getInt("Codigo");
        	nomeItemSelecionado = items[pos];
        }
		
		TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
		txtTitulo.setText(R.string.txtCadastroEvento);
        
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(this);
        
        btnAssociar = (Button) findViewById(R.id.btnAssociar);
        btnAssociar.setOnClickListener(this);
        
        txtNome = (TextView) findViewById(R.id.txtNomeEvento);
        if (!nomeItemSelecionado.equals("") )
        	txtNome.setText(nomeItemSelecionado);
        
    }
    
    public void onClick(View v) {
    	Intent it;
    	
    	switch (v.getId()) {
		case R.id.btnEditar:
			it = new Intent("EVENTO_CAD_EVENTO");
			it.putExtra("idEvento", txtNome.getText());
			it.addCategory("BR_FATECSP");
			startActivity( it );
			break;

		case R.id.btnAssociar:
			it = new Intent("EVENTO_ASSOCIAR");
			it.putExtra("nome", txtNome.getText());
			it.addCategory("BR_FATECSP");
			startActivityForResult(it, RETORNO);
			break;
			
		default:
			break;
		}
    }
    
    @Override
	protected void onActivityResult(int codigo, int resultado, Intent it) {
		super.onActivityResult(codigo, resultado, it);
    	if(codigo==22){
			if(codigo==RETORNO){
				// ------------------------------
				//  Tratar retorno dos dados da 
				// associação
				// ------------------------------
				if ( it != null) {
					Bundle param = it.getExtras();
					// -----------------------------------------------------
					//   Inserir variáveis utilizadas 
					// para identificar os dados
					// -----------------------------------------------------
				}
			}
		}
	}
}
