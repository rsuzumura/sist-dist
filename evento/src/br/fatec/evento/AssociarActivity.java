package br.fatec.evento;

import java.util.List;

import br.fatec.evento.classes.Convidado;
import br.fatec.evento.classes.ConvidadoSOAP;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


public class AssociarActivity extends ListActivity implements OnClickListener {
	/** Called when the activity is first created. */
	List<Convidado> convidados;
	public String[] items;  //= new String[] { "Pessoa 1","Pessoa 2","Pessoa 3",
							//			   "Pessoa 4","Pessoa 5","Pessoa 6"};
	int idEvento;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.associar);
        
        // ------------------------------------------------
        //  Receber parâmetro para ajustar o título do evento
        // e filtrar os registro que podem ser exibidos
        // ------------------------------------------------
        
        String nomeEvento = "";
        
		Bundle params = getIntent().getExtras();
		if ( params != null ) {
			nomeEvento = params.getString("nome");
			idEvento = params.getInt("id");
        }
        
		SharedPreferences pref = getSharedPreferences( "EVENTO", MODE_PRIVATE);
    	String url = pref.getString("url", "");
    	ConvidadoSOAP cs = new ConvidadoSOAP(url);
        convidados = cs.list();
        
        if (convidados != null) {
    		items = new String[convidados.size()];
    		int i = 0;
    		for (Convidado c : convidados) {
				items[i] = c.nome;
				i++;
			}
    	}
        if (items != null)
        	this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked, items));
    	else
    		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked, new String[]{ }));
        
        TextView lblNomeEvento = (TextView) findViewById(R.id.lblNomeEvento);
        
        if (nomeEvento.equals("")) 
        	lblNomeEvento.setText(R.string.btnAssociar);
        else
        	lblNomeEvento.setText(nomeEvento);
        
        ImageView btnConfirmar = (ImageView) findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(this);

    }
    
    public void onClick( View v ){
    	
    	switch (v.getId()) {
		case R.id.btnConfirmar:
			
	        // ------------------------------------------------
	        //  Botão confirmar com função de retornar o conteúdo
	        // selecionado para a janela anterior
	        // proporcionando a gravação como um todo do Evento
	        // ------------------------------------------------ 
			finish();
			break;

		default:
			break;
		}
    }
    
	protected void onListItemClick(ListView l, View v, int position, long id) 
    {
		CheckedTextView check = (CheckedTextView) v;
		check.setChecked(!check.isChecked());
		// ------------------------------------------------------------
		//  Inserir tratamento para inclusão em variáveis auxiliares
		// ------------------------------------------------------------
    }
}
