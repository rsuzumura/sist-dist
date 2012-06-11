package br.fatec.evento;

import android.app.Activity; 
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;


public class CadastroTipoEventoActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	public String[] items = new String[] { "Tipo Evento 1","Tipo Evento 2","Tipo Evento 3"};
	public final int INVISIBILITY = 4;
	private boolean edicao = false;
	
	Button btnGravar;
	EditText txtNomeTpEvento;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	String nomeItemSelecionado = "";
    	int pos = -1;
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_tipo_evento);
        
		Bundle params = getIntent().getExtras();
        
		if ( params != null ) {
			pos = params.getInt("Codigo");
        	nomeItemSelecionado = items[pos];
        	edicao = true;
        }
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setClickable(true);
        btnInsert.setVisibility(0);
        
        txtNomeTpEvento = (EditText) findViewById(R.id.txtTipoEvento);
        txtNomeTpEvento.setEnabled(edicao);
        
        txtNomeTpEvento.setText(nomeItemSelecionado);
        
        btnGravar = (Button) findViewById(R.id.btnGravar);
        btnGravar.setOnClickListener(this);
        atualizaTela();
    }
    
    public void onClick(View v) {
    	switch (v.getId()) {
		case R.id.btnEditar:
			edicao = !edicao;
			break;

		default:
			break;
		}
    	atualizaTela();
    }
    
    private void atualizaTela() {
    	String textoBotaoEdicao = ( edicao ? "Gravar" : "Editar");
   		btnGravar.setText(textoBotaoEdicao);
    	txtNomeTpEvento.setEnabled(edicao);    		
    }
}
