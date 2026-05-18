package com.jogotop.quizpremiado;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.app.Activity;
import android.content.Intent;

public class ResultActivity extends Activity {

    private TextView tvPontos, tvNivel;
    private Button btnJogar, btnCompartilhar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvPontos = findViewById(R.id.tvPontos);
        tvNivel = findViewById(R.id.tvNivel);
        btnJogar = findViewById(R.id.btnJogar);
        btnCompartilhar = findViewById(R.id.btnCompartilhar);

        int pontos = getIntent().getIntExtra("pontos", 0);
        int nivel = getIntent().getIntExtra("nivel", 1);

        tvPontos.setText(String.valueOf(pontos));
        tvNivel.setText("Nível atingido: " + nivel);

        btnJogar.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        btnCompartilhar.setOnClickListener(v -> {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, "Fiz " + pontos + " pontos no Quiz Premiado TOP!");
            startActivity(Intent.createChooser(share, "Compartilhar"));
        });
    }
}