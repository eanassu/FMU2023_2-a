package com.example.exemplo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class TipCalculatorActivity extends AppCompatActivity {

    private EditText editTextValor;
    private TextView textViewValor;
    private TextView textViewPct;
    private TextView textViewGorjeta;
    private TextView textViewTotal;
    private SeekBar seekBar;
    private double valor;
    private double gorjeta;
    private double porcentagem;
    private double total;
    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    NumberFormat pctFormat = NumberFormat.getPercentInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        editTextValor = findViewById(R.id.editTextValor);
        textViewValor = findViewById(R.id.textViewValor);
        textViewPct = findViewById(R.id.textViewPct);
        textViewGorjeta = findViewById(R.id.textViewGorjeta);
        textViewTotal = findViewById(R.id.textViewTotal);
        seekBar = findViewById(R.id.seekBar);
        editTextValor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int valorInt;
                try {
                    valorInt = Integer.parseInt(editTextValor.getText().toString());
                } catch (NumberFormatException e) {
                    valorInt = 0;
                }
                valor = valorInt/100.0;
                recalcular();
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentagem = progress/100.0;
                recalcular();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void recalcular() {
        textViewValor.setText(currencyFormat.format(valor));
        textViewPct.setText(pctFormat.format(porcentagem));
        gorjeta = valor * porcentagem;
        total = valor + gorjeta;
        textViewGorjeta.setText(currencyFormat.format(gorjeta));
        textViewTotal.setText(currencyFormat.format(total));
    }
}