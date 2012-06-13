package br.fatec.evento;

import br.fatec.evento.classes.ConvidadoSOAP;
import android.app.Activity; 
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CadastroConvidadoActivity extends Activity implements OnClickListener {
	int idConvidado;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_convidado);
        
        TextView txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtTitulo.setText(R.string.txtCadastroConvidado);
        
        Button btnGravar = (Button)findViewById(R.id.btnGravar);
        btnGravar.setOnClickListener(this);
        
        Bundle params = getIntent().getExtras();
        
		if (params != null) {
			idConvidado = params.getInt("idConvidado");
			EditText editNome = (EditText)findViewById(R.id.editNome);
	    	EditText editMail = (EditText)findViewById(R.id.editMail);
	    	EditText editPhone = (EditText)findViewById(R.id.editPhone);
	    	EditText editOrigem = (EditText)findViewById(R.id.editOrigem);
	    	editNome.setText(params.getString("nome"));
	    	editMail.setText(params.getString("email"));
	    	editPhone.setText(params.getString("telefone"));
	    	editOrigem.setText(params.getString("origem"));
        }
    }
    
    public void onClick(View v) {
    	AlertDialog dialog = new AlertDialog.Builder(this).create();
    	boolean success = false;
    	SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
    	String url = pref.getString("url", "");
    	ConvidadoSOAP cs = new ConvidadoSOAP(url);
    	EditText editNome = (EditText)findViewById(R.id.editNome);
    	EditText editMail = (EditText)findViewById(R.id.editMail);
    	EditText editPhone = (EditText)findViewById(R.id.editPhone);
    	EditText editOrigem = (EditText)findViewById(R.id.editOrigem);
    	try {
    		if (idConvidado == 0) { 
    			int id = cs.Add(
					editNome.getText().toString(), 
					editMail.getText().toString(), 
					editPhone.getText().toString(), 
					editOrigem.getText().toString());
    			if (id != 0) {
    				dialog.setTitle("Cadastro efetuado com sucesso");
    				dialog.setMessage(getString(R.string.msgOK));
    				dialog.setIcon(android.R.drawable.ic_dialog_info);
    				success = true;
    			} else {
    				dialog.setMessage("Erro no Cadastro do Convidado.");
    				dialog.setTitle(R.string.msgError);
    				dialog.setIcon(android.R.drawable.ic_dialog_alert);
    			}
    		} else {
    			boolean updated = cs.Save(
					idConvidado, 
					editNome.getText().toString(), 
					editMail.getText().toString(), 
					editPhone.getText().toString(), 
					editOrigem.getText().toString());
    			if (updated) {
    				dialog.setTitle("Cadastro alterado com sucesso");
    				dialog.setMessage(getString(R.string.msgOK));
    				dialog.setIcon(android.R.drawable.ic_dialog_info);
    				success = true;
    			} else {
    				dialog.setMessage("Erro na Alteração do Convidado.");
    				dialog.setTitle(R.string.msgError);
    				dialog.setIcon(android.R.drawable.ic_dialog_alert);
    			}
    		}
    			
    	} catch(Exception ex) {
    		dialog.setMessage("Erro no Cadastro do Convidado.");
			dialog.setTitle(R.string.msgError);
			dialog.setIcon(android.R.drawable.ic_dialog_alert);
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
}
