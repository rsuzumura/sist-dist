package br.fatec.evento;

import android.app.Activity; 
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class CadastroFuncaoActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	public String[] items = new String[] { "Função 1","Função 2","Função 3"};
	
	Button btnGravar;
	TextView txtFuncao;
	boolean edicao;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	String nomeItemSelecionado = "";
    	int pos = -1;
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_funcao);
        
		Bundle params = getIntent().getExtras();
        
		if ( params != null ) {
			pos = params.getInt("Codigo");
        	nomeItemSelecionado = items[pos];
        }
		
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setClickable(true);
        btnInsert.setVisibility(0);
        
        txtFuncao = (EditText) findViewById(R.id.txtNomeFuncao);
        txtFuncao.setEnabled(edicao);
        
        txtFuncao.setText(nomeItemSelecionado);
        
        btnGravar = (Button) findViewById(R.id.btnGravar);
        btnGravar.setOnClickListener(this);
        atualizaTela();
    }
    
    @Override
    public void onClick(View v) {
    	
    	switch (v.getId()) {
    	
		case R.id.btnGravar:
			edicao = !edicao;
			atualizaTela();
			break;

		default:
			break;
		}

    }

    private void atualizaTela() {
    	String textoBotaoEdicao = ( edicao ? "Gravar" : "Editar");
   		btnGravar.setText(textoBotaoEdicao);
    	txtFuncao.setEnabled(edicao);    		
    }
}
