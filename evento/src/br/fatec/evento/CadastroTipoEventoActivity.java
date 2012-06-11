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
	
	Button btnEditar;
	EditText txtNomeTpEvento;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	String nomeItemSelecionado = "";
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_tipo_evento);
        
		Bundle params = getIntent().getExtras();
        int pos = params.getInt("Codigo");

        if ( pos != 0)
        	nomeItemSelecionado = items[pos];
        
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setClickable(true);
        btnInsert.setVisibility(0);
        
        txtNomeTpEvento = (EditText) findViewById(R.id.txtTipoEvento);
        txtNomeTpEvento.setEnabled(edicao);
        
        txtNomeTpEvento.setText(nomeItemSelecionado);
        
        btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(this);
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
    	String textoBotaoEdicao = (String) ( edicao ? "Gravar" : R.string.btnEditar );
   		btnEditar.setText(textoBotaoEdicao);
    	txtNomeTpEvento.setEnabled(edicao);    		
    }
}
