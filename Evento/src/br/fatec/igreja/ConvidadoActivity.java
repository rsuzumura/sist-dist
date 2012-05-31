package br.fatec.igreja;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ConvidadoActivity extends Activity {
	public static String rslt = "";
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_convidado);
		final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		Button btnInsert = (Button)findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					// TODO Auto-generated method stub
					Caller c = new Caller();
					EditText nome 		= (EditText)findViewById(R.id.editNome);
					EditText email 		= (EditText)findViewById(R.id.editMail);
					EditText telefone 	= (EditText)findViewById(R.id.editPhone);
					EditText origem 	= (EditText)findViewById(R.id.editOrigem);
					EditText funcao 	= (EditText)findViewById(R.id.editFuncao);
					
					//method = 2 [Convidado Insert]
					c.method = 2;
					c.nome 		= nome.getText().toString();
					c.email 	= email.getText().toString();
					c.funcao 	= funcao.getText().toString();
					c.origem 	= origem.getText().toString();
					c.telefone 	= telefone.getText().toString();
					c.join();
					c.start();
					while (rslt == "START") {
						try{
							Thread.sleep(10);
						}catch(Exception ex){
							
						}						
					}
					dialog.setMessage(R.string.messageInsertSuccess);
					dialog.setTitle("Cadastro efetuado com sucesso");
					dialog.setIcon(android.R.drawable.ic_dialog_info);
					//resultXML.setText(rslt);
				} catch(Exception ex){
					dialog.setMessage(R.string.messageInsertFail);
					dialog.setTitle("Erro de Cadastro");
					dialog.setIcon(android.R.drawable.ic_dialog_alert);
				}
				dialog.show();
			}
		});
	}
}
