package br.fatec.evento;

import br.fatec.evento.classes.TipoEventoSOAP;
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


public class CadastroTipoEventoActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */
	//public String[] items = new String[] { "Tipo Evento 1","Tipo Evento 2","Tipo Evento 3"};
	public final int INVISIBILITY = 4;
	//private boolean edicao = true;
	
	Button btnGravar;
	EditText txtNomeTpEvento;
	int idTipoEvento;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	String nomeItemSelecionado = "";
    	//int pos = -1;
    	
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_tipo_evento);
        
		Bundle params = getIntent().getExtras();
        
		if ( params != null ) {
			idTipoEvento = params.getInt("id");
        	nomeItemSelecionado = params.getString("nome");
        }
		
		TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
		txtTitulo.setText(R.string.txtCadastroTipoEvento);
		
        ImageView btnInsert = (ImageView) findViewById(R.id.btnInsert);
        btnInsert.setClickable(true);
        btnInsert.setVisibility(0);
        
        txtNomeTpEvento = (EditText) findViewById(R.id.txtTipoEvento);
        txtNomeTpEvento.setText(nomeItemSelecionado);
        //txtNomeTpEvento.setEnabled(edicao);
        
        btnGravar = (Button) findViewById(R.id.btnGravar);
        btnGravar.setOnClickListener(this);
        //atualizaTela();
    }
    
    public void onClick(View v) {
    	boolean success = false;
    	AlertDialog dialog = new AlertDialog.Builder(this).create();
    	switch (v.getId()) {
		case R.id.btnGravar:
			//edicao = !edicao;
			SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
	    	String url = pref.getString("url", "");
	    	
	    	try {
				TipoEventoSOAP tes = new TipoEventoSOAP(url);
				if (idTipoEvento > 0) {
					boolean updated = tes.Save(idTipoEvento, txtNomeTpEvento.getText().toString());
					if (updated) {
	    				dialog.setTitle("Cadastro alterado com sucesso");
	    				dialog.setMessage(getString(R.string.msgOK));
	    				dialog.setIcon(android.R.drawable.ic_dialog_info);
	    				success = true;
	    			} else {
	    				dialog.setMessage("Erro na Alteração do Tipo de Evento.");
	    				dialog.setTitle(R.string.msgError);
	    				dialog.setIcon(android.R.drawable.ic_dialog_alert);
	    			}
				}
				else {
					idTipoEvento = tes.Add(txtNomeTpEvento.getText().toString());
					if (idTipoEvento != 0) {
	    				dialog.setTitle("Cadastro efetuado com sucesso");
	    				dialog.setMessage(getString(R.string.msgOK));
	    				dialog.setIcon(android.R.drawable.ic_dialog_info);
	    				success = true;
	    			} else {
	    				dialog.setMessage("Erro no Cadastro do Tipo de Evento.");
	    				dialog.setTitle(R.string.msgError);
	    				dialog.setIcon(android.R.drawable.ic_dialog_alert);
	    			}
				}
	    	} catch (Exception ex) {
	    		dialog.setMessage("Erro no Cadastro do Tipo de Evento.");
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
    	//atualizaTela();
    }
    
    /*private void atualizaTela() {
    	String textoBotaoEdicao = ( edicao ? "Gravar" : "Editar");
   		btnGravar.setText(textoBotaoEdicao);
    	txtNomeTpEvento.setEnabled(edicao);    		
    }*/
}
