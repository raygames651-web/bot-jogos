package com.jogotop.quizpremiado;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;

public class MainActivity extends Activity {

    private TextView tvPergunta, tvPontos, tvNivel;
    private Button btnA, btnB, btnC, btnD;
    private int pontos = 0;
    private int nivel = 1;
    private int perguntaAtual = 0;
    private AdManager adManager;
    private int perguntasMostradas = 0;

    private String[] perguntas = {
        "Qual a capital do Brasil?",
        "Quanto é 7 x 8?",
        "Quem descobriu o Brasil?",
        "Qual o maior planeta?",
        "Água fervendo tem quantos graus?",
        "Qual cor do céu?",
        "Quantos dias tem Fevereiro?",
        "Animal que late?",
        "Fruta amarelo?",
        "5 + 5 = ?"
    };

    private String[][] opcoes = {
        {"Rio", "SP", "Brasília", "BH"},
        {"54", "56", "58", "62"},
        {"Colombo", "Cabral", "Vasco", "Américo"},
        {"Terra", "Júpiter", "Marte", "Saturno"},
        {"90°C", "80°C", "100°C", "70°C"},
        {"Verde", "Azul", "Vermelho", "Amarelo"},
        {"28", "29", "30", "31"},
        {"Gato", "Cachorro", "Vaca", "Pato"},
        {"Maçã", "Banana", "Uva", "Laranja"},
        {"8", "9", "10", "11"}
    };

    private int[] corretas = {2, 1, 1, 1, 2, 1, 1, 1, 1, 2};

    private String[] feedback = {
        "CORRETO! +10 pontos",
        "ERRADO! Tente novamente"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adManager = new AdManager(this);
        
        LinearLayout adTop = findViewById(R.id.adContainerTop);
        LinearLayout adBottom = findViewById(R.id.adContainerBottom);
        
        adManager.loadAdTop(adTop);
        adManager.loadAdBottom(adBottom);

        tvPergunta = findViewById(R.id.tvPergunta);
        tvPontos = findViewById(R.id.tvPontos);
        tvNivel = findViewById(R.id.tvNivel);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);

        carregaPergunta();

        btnA.setOnClickListener(v -> verificar(0));
        btnB.setOnClickListener(v -> verificar(1));
        btnC.setOnClickListener(v -> verificar(2));
        btnD.setOnClickListener(v -> verificar(3));
    }

    private void carregaPergunta() {
        if (perguntaAtual >= perguntas.length) {
            finishGame();
            return;
        }

        tvPergunta.setText(perguntas[perguntaAtual]);
        btnA.setText("A) " + opcoes[perguntaAtual][0]);
        btnB.setText("B) " + opcoes[perguntaAtual][1]);
        btnC.setText("C) " + opcoes[perguntaAtual][2]);
        btnD.setText("D) " + opcoes[perguntaAtual][3]);

        resetButtons();
    }

    private void resetButtons() {
        btnA.setBackgroundColor(Color.parseColor("#16213e"));
        btnB.setBackgroundColor(Color.parseColor("#16213e"));
        btnC.setBackgroundColor(Color.parseColor("#16213e"));
        btnD.setBackgroundColor(Color.parseColor("#16213e"));
    }

    private void verificar(int resposta) {
        boolean correto = (resposta == corretas[perguntaAtual]);

        if (correto) {
            pontos += 10;
            tvPontos.setText("⭐ Pontos: " + pontos);

            Button[] btns = {btnA, btnB, btnC, btnD};
            btns[resposta].setBackgroundColor(Color.parseColor("#00ff88"));

            new Handler().postDelayed(() -> {
                perguntaAtual++;
                perguntasMostradas++;
                
                if (perguntaAtual % 3 == 0) {
                    nivel++;
                    tvNivel.setText("Nível: " + nivel);
                    adManager.showInterstitial();
                }
                carregaPergunta();
            }, 500);
        } else {
            Button[] btns = {btnA, btnB, btnC, btnD};
            btns[resposta].setBackgroundColor(Color.parseColor("#ff4444"));
            btns[corretas[perguntaAtual]].setBackgroundColor(Color.parseColor("#00ff88"));

            new Handler().postDelayed(() -> {
                perguntaAtual++;
                perguntasMostradas++;
                if (perguntasMostradas % 3 == 0) {
                    adManager.showInterstitial();
                }
                carregaPergunta();
            }, 800);
        }
    }

    private void finishGame() {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("pontos", pontos);
        intent.putExtra("nivel", nivel);
        startActivity(intent);
        finish();
    }
}