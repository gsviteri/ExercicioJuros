package br.com.fiap.exerciciojuros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultadoActivity extends AppCompatActivity {

    private TextView tvResultado;
    private Button btnFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tvResultado = (TextView)findViewById(R.id.tvResultado);
        btnFechar = (Button)findViewById(R.id.btnFechar);

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //if(i.hasExtra("jurosPagos")){
         //   tvResultado.setText(i.getStringExtra("jurosPagos"));
        //}

        Intent in = getIntent();

        Bundle b = in.getExtras();

        double jurosPagos = calculaJuros(b);

        tvResultado.setText(String.valueOf(jurosPagos));

    }

    private double calculaJuros(Bundle b){
        double jurosPagos = 0;

        double valorFinanciamentoAsDouble = Double.valueOf(b.getString("valorFinanciamento"));
        int numeroMesesAsDouble = Integer.valueOf(b.getString("numeroMeses"));
        double jurosAsDouble = Double.valueOf(b.getString("juros"));

        // valor * taxa * total Meses
        jurosPagos = valorFinanciamentoAsDouble * (jurosAsDouble / 100) * numeroMesesAsDouble;

        return jurosPagos;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
