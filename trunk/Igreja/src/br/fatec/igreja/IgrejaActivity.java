package br.fatec.igreja;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IgrejaActivity extends Activity {
	public static String rslt = "";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button btnSet = (Button)findViewById(R.id.btnSet);
        btnSet.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				try {
					// TODO Auto-generated method stub
					Caller c = new Caller();
					EditText idConvidado 	= (EditText)findViewById(R.id.idConvidado);
					TextView resultXML 		= (TextView)findViewById(R.id.resultXML);
					
					c.idConvidado = Integer.parseInt(idConvidado.getText().toString());
					c.join();
					c.start();
					while (rslt == "START") {
						try{
							Thread.sleep(10);
						}catch(Exception ex){
							
						}
						
					}
					resultXML.setText(rslt);
				} catch(Exception ex){
					
				}
			}
		});
    }
}