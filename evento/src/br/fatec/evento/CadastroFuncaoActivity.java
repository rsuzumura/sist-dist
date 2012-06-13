package br.fatec.evento;

import br.fatec.evento.classes.FuncaoSOAP;
import android.app.Activity; 
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class CadastroFuncaoActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	int idFuncao;
	Button btnGravar;
	TextView txtFuncao;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	String nome = "";    	    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_funcao);
        
        TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtTitulo.setText(R.string.txtCadastroFuncao);
        
		Bundle params = getIntent().getExtras();
		
		if ( params != null ) {
			idFuncao = params.getInt("id");
			nome = params.getString("nome");
        }
		
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setClickable(true);
        btnInsert.setVisibility(View.INVISIBLE);
        
        txtFuncao = (EditText) findViewById(R.id.txtNomeFuncao);        
        txtFuncao.setText(nome);
        
        btnGravar = (Button) findViewById(R.id.btnGravar);
        btnGravar.setOnClickListener(this);
    }
    
    public void onClick(View v) {
    	boolean success = false;
    	AlertDialog dialog = new AlertDialog.Builder(this).create();
    	switch (v.getId()) {    	
			case R.id.btnGravar:				
				SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
		    	String url = pref.getString("url", "");
		    	
		    	try {
					FuncaoSOAP fs = new FuncaoSOAP(url);
					if (idFuncao > 0) {
						boolean updated = fs.Save(idFuncao, txtFuncao.getText().toString());
						if (updated) {
		    				dialog.setTitle("Cadastro alterado com sucesso");
		    				dialog.setMessage(getString(R.string.msgOK));
		    				dialog.setIcon(android.R.drawable.ic_dialog_info);
		    				success = true;
		    			} else {
		    				dialog.setMessage("Erro na Alteração de Função.");
		    				dialog.setTitle(R.string.msgError);
		    				dialog.setIcon(android.R.drawable.ic_dialog_alert);
		    			}
					}
					else {
						idFuncao = fs.Add(txtFuncao.getText().toString());
						if (idFuncao != 0) {
		    				dialog.setTitle("Cadastro efetuado com sucesso");
		    				dialog.setMessage(getString(R.string.msgOK));
		    				dialog.setIcon(android.R.drawable.ic_dialog_info);
		    				success = true;
		    			} else {
		    				dialog.setMessage("Erro no Cadastro de Função.");
		    				dialog.setTitle(R.string.msgError);
		    				dialog.setIcon(android.R.drawable.ic_dialog_alert);
		    			}
					}
		    	} catch (Exception ex) {
		    		dialog.setMessage("Erro no Cadastro de Função.");
					dialog.setTitle(R.string.msgError);
					dialog.setIcon(android.R.drawable.ic_dialog_alert);
		    	}
				break;

			default:
				break;
		}
    	dialog.setButton("OK", new DialogInterface.OnClickListener() {
    		public void onClick(DialogInterface dialog, int which) {
    			dialog.cancel();
    		}
 		});
    	dialog.show();
    	if (success)
    		this.finish();
    }

    /*private void atualizaTela() {
    	String textoBotaoEdicao = ( edicao ? "Gravar" : "Editar");
   		btnGravar.setText(textoBotaoEdicao);
    	txtFuncao.setEnabled(edicao);    		
    }*/
}
