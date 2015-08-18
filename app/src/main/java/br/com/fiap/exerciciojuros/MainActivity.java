package br.com.fiap.exerciciojuros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private EditText valorFinanciamento;
    private EditText numeroMeses;
    private EditText juros;
    private Button btnCalcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valorFinanciamento = (EditText)findViewById(R.id.valorFinanciamento);
        numeroMeses = (EditText)findViewById(R.id.numeroMeses);
        juros = (EditText)findViewById(R.id.juros);
        btnCalcular = (Button)findViewById(R.id.btnCalcular);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle b = new Bundle();

                //b.putString("jurosPagos", calculaJuros());
                b.putString("valorFinanciamento", valorFinanciamento.getText().toString());
                b.putString("numeroMeses", numeroMeses.getText().toString());
                b.putString("juros", juros.getText().toString());

                Intent i = new Intent(getApplicationContext(), ResultadoActivity.class);
                i.putExtras(b);

                startActivity(i);

            }
        });

        valorFinanciamento.addTextChangedListener(new TextWatcher() {
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
            String current = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(current)){
                    valorFinanciamento.removeTextChangedListener(this);

                    String cleanString = s.toString().replaceAll("[$,.]", "");

                    //double parsed = Double.parseDouble(cleanString);
                    //String formatted = NumberFormat.getCurrencyInstance().format((parsed / 100));

                    BigDecimal parsed = new BigDecimal(cleanString).setScale(2,BigDecimal.ROUND_FLOOR).divide(new BigDecimal(100), BigDecimal.ROUND_FLOOR);
                    String formatted = NumberFormat.getCurrencyInstance().format(parsed);

                    current = formatted;
                    valorFinanciamento.setText(formatted);
                    valorFinanciamento.setSelection(formatted.length());

                    valorFinanciamento.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

/*
  Mascara mantendo o curso fixo
  if(!s.toString().equals(current))
                {
                    valorFinanciamento.removeTextChangedListener(this);

                    int selection = valorFinanciamento.getSelectionStart();


                    // We strip off the currency symbol
                    String replaceable = String.format("[%s,\\s]", NumberFormat.getCurrencyInstance().getCurrency().getSymbol());
                    String cleanString = s.toString().replaceAll(replaceable, "");

                    double price;

                    // Parse the string
                    try
                    {
                        price = Double.parseDouble(cleanString);
                    }
                    catch(java.lang.NumberFormatException e)
                    {
                        price = 0;
                    }

                    // If we don't see a decimal, then the user must have deleted it.
                    // In that case, the number must be divided by 100, otherwise 1
                    int shrink = 1;
                    if(!(s.toString().contains(".")))
                    {
                        shrink = 100;
                    }

                    // Reformat the number
                    String formated = currencyFormat.format((price / shrink));

                    current = formated;
                    valorFinanciamento.setText(formated);
                    valorFinanciamento.setSelection(Math.min(selection, valorFinanciamento.getText().length()));

                    valorFinanciamento.addTextChangedListener(this);
                }

 */