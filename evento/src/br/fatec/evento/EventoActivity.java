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
	
	//public String[] items = new String[] { "Evento 1","Evento 2","Evento 3"};
	public final int RETORNO = 0;
	Button btnAssociar;
	Button btnEditar;
	int idEvento;
	int idTipoEvento;
	
	//-----------------------------
	// campos da interface
	//-----------------------------
	TextView txtNome;
	TextView txtLocal;
	TextView txtResponsavel;
	TextView lblStatus;
	TextView lblDateIniFim;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.evento);
        txtLocal = (TextView)findViewById(R.id.txtLocal);
        txtResponsavel = (TextView)findViewById(R.id.txtResponsavel);
        lblStatus = (TextView)findViewById(R.id.lblStatus);
        lblDateIniFim = (TextView)findViewById(R.id.lblDateIniFim);
        //int pos = -1;	
        String nomeItemSelecionado = "";
		Bundle params = getIntent().getExtras();
        
		if ( params != null ) {
			idEvento = params.getInt("id");
        	nomeItemSelecionado = params.getString("nome");
        	txtResponsavel.setText(txtResponsavel.getText().toString() + ": " + params.getString("local"));
        	lblStatus.setText(lblStatus.getText().toString() + ": " + params.getString("status"));
        	lblDateIniFim.setText(lblDateIniFim.getText().toString() + ": " + params.getString("inicioEvento") + " - " + params.getString("fimEvento"));
        	idTipoEvento = params.getInt("tipoEventoId");
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
    	Bundle params = getIntent().getExtras();
    	switch (v.getId()) {    	
		case R.id.btnEditar:			
			if ( params != null ) {
				it = new Intent("EVENTO_CAD_EVENTO");
				it.putExtra("id", idEvento);
				it.putExtra("nome", params.getString("nome"));
				it.putExtra("local", params.getString("local"));
				it.putExtra("responsavel", params.getString("responsavel"));
				it.putExtra("inicioEvento", params.getString("inicioEvento"));
				it.putExtra("fimEvento", params.getString("fimEvento"));
				it.putExtra("status", params.getString("status"));
				it.putExtra("idTipoEvento", params.getInt("idTipoEvento"));
				it.addCategory("BR_FATECSP");
				startActivity( it );
			}
			break;

		case R.id.btnAssociar:
			if (params != null) {
				it = new Intent("EVENTO_ASSOCIAR");
				it.putExtra("id", idEvento);
				it.putExtra("nome", params.getString("nome"));
				it.addCategory("BR_FATECSP");
				startActivityForResult(it, RETORNO);
			}
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
