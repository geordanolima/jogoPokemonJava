package com.example.a20161cmqads0220.jogo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button botaoEntrar;
    private Button botaoVoltar;
    private Button botaoAvancar;
    private Button botaosair;
    private Button botaoCriatura1;
    private Button botaoCriatura2;
    private Button botaoCriatura3;
    private Button botaoCriaturaVoltar;
    private Button botaoCadastrar;
    private Button botaoCadastrarFinal;
    private Button botaoFinalizaCadastro;
    private TextView nome;
    private TextView senha;
    private TextView apelido;
    private TextView email;
    private TextView visaogeralNome;
    private TextView visaogeralApelido;
    private TextView cadastroParte2Nome;
    private TextView cadastroParte2Apelido;
    Jogador jogador = new Jogador();
    private ArrayList<criatura> startCriatura;
    private ArrayList<Itens> startItens;
    private TextView cadastroCriatura1;
    private TextView cadastroCriatura2;
    private TextView cadastroCriatura3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        botaoEntrar = findViewById(R.id.botaoEntrarId);
        botaoEntrar.setOnClickListener(chamadaEntrar);
        botaoCadastrar=findViewById(R.id.botaoCadastrarId);
        botaoCadastrar.setOnClickListener(chamadaCadastro);
        jogador.setNome("usuario");
        jogador.setSenha("123");
        jogador.setApelido("teste");
        jogador.setEmail("email@teste.com");
        startCriatura =new ArrayList(); // INICIALIZA
        criatura c1=new criatura();
        criatura c2=new criatura();
        criatura c3=new criatura();
        c1.setNomeCriatura("monstro 1");
        c2.setNomeCriatura("monstro 2");
        c3.setNomeCriatura("monstro 3");
        c1.setAtaque(Integer.toString(50));
        startCriatura.add(c1);
        startCriatura.add(c2);
        startCriatura.add(c3);

        startItens = new ArrayList();
        Itens i1 = new Itens();
        Itens i2 = new Itens();
        Itens i3 = new Itens();
        Itens i4 = new Itens();
        Itens i5 = new Itens();


        startItens.add(i1);
        startItens.add(i2);
        startItens.add(i3);
        startItens.add(i4);
        startItens.add(i5);
        jogador.setItens(startItens);

    }

    public View.OnClickListener chamadaEntrar = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setContentView(R.layout.login);
            Log.i("avançar: ", "avançou para login");
            botaoVoltar = findViewById(R.id.loginVoltarId);
            botaoAvancar=findViewById(R.id.botaoAvancarId);
            botaoVoltar.setOnClickListener(chamadaVoltarInicio);
            botaoAvancar.setOnClickListener(chamadaAvancar);
        }
    };

    public View.OnClickListener chamadaVoltarInicio=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setContentView(R.layout.index);
            Log.i("voltar", "voltou para tela inicial");
            botaoEntrar = findViewById(R.id.botaoEntrarId);
            botaoEntrar.setOnClickListener(chamadaEntrar);
            botaoCadastrar=findViewById(R.id.botaoCadastrarId);
            botaoCadastrar.setOnClickListener(chamadaCadastro);
        }
    };

    public View.OnClickListener chamadaAvancar=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nome=findViewById(R.id.apelidoId);
            senha=findViewById(R.id.nomeId);
            if (jogador.getNome().equals(nome.getText().toString())
                    && jogador.getSenha().equals(senha.getText().toString())){
                setContentView(R.layout.perfiljogador);
                Log.i("avancar:","avançou para tela visao geral");
                botaosair=findViewById(R.id.sairToEntrarId);
                botaosair.setOnClickListener(chamadaVoltarInicio);
                botaoCriatura1=findViewById(R.id.botaoCriatura1Id);
                botaoCriatura2=findViewById(R.id.botaoCriatura2Id);
                botaoCriatura1.setOnClickListener(chamadaCriatura);
                botaoCriatura2.setOnClickListener(chamadaCriatura);
                botaoCriatura3.setOnClickListener(chamadaCriatura);
                visaogeralNome=findViewById(R.id.visaogeralNomeId);
                visaogeralApelido=findViewById(R.id.visaogeralApelidoId);
                visaogeralNome.setText(jogador.getNome().toString());
                visaogeralApelido.setText(jogador.getApelido().toString());

            }else {
                Toast.makeText(getApplicationContext(),"Erro, tente novamente!",Toast.LENGTH_SHORT).show();
            }


        }
    };

    public View.OnClickListener chamadaCriatura=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setContentView(R.layout.visualizacriatura);
            Log.i("ver: ","avançou para tela da visualizacriatura");
            botaoCriaturaVoltar=findViewById(R.id.botaoCriaturaVoltarId);
            botaoCriaturaVoltar.setOnClickListener(chamadaCriaturaVoltar);
        }
    };

    public View.OnClickListener chamadaCriaturaVoltar=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setContentView(R.layout.perfiljogador);
            Log.i("voltar: ","voltou para visualizacriatura");
            botaoCriatura1=findViewById(R.id.botaoCriatura1Id);
            botaoCriatura2=findViewById(R.id.botaoCriatura2Id);

            botaoCriatura1.setOnClickListener(chamadaCriatura);
            botaoCriatura2.setOnClickListener(chamadaCriatura);
            botaoCriatura3.setOnClickListener(chamadaCriatura);
            botaosair=findViewById(R.id.sairToEntrarId);
            botaosair.setOnClickListener(chamadaVoltarInicio);
            visaogeralNome=findViewById(R.id.visaogeralNomeId);
            visaogeralApelido=findViewById(R.id.visaogeralApelidoId);
            visaogeralNome.setText(jogador.getNome().toString());
            visaogeralApelido.setText(jogador.getApelido().toString());
        }
    };

    public View.OnClickListener chamadaCadastro=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setContentView(R.layout.cadastrojogador);
            Log.i("avança: ","foi para cadastrojogador");
            botaoCadastrarFinal=findViewById(R.id.botaoCadastrarFinalId);
            botaoCadastrarFinal.setOnClickListener(chamadaCadastroFinal);

        }
    };

    public View.OnClickListener chamadaCadastroFinal=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            nome=findViewById(R.id.nomeId);
            senha=findViewById(R.id.senhaId);
            apelido=findViewById(R.id.apelidoId);
            email=findViewById(R.id.emailId);
            jogador.setNome(nome.getText().toString());
            jogador.setSenha(senha.getText().toString());
            jogador.setApelido(apelido.getText().toString());
            jogador.setEmail(email.getText().toString());
            setContentView(R.layout.cadastrojogador2);
            Log.i("avança: ", "foi para parte 2 do cadastrojogador: "+jogador.getNome());
            botaoFinalizaCadastro=findViewById(R.id.botaoFinalizaCadastroId);
            botaoFinalizaCadastro.setOnClickListener(chamadaFimCadastro);
            cadastroParte2Nome=findViewById(R.id.cadastroParte2NomeId);
            cadastroParte2Apelido=findViewById(R.id.cadastroParte2ApelidoId);
            cadastroParte2Nome.setText(jogador.getNome().toString());
            cadastroParte2Apelido.setText(jogador.getApelido().toString());
            cadastroCriatura1=findViewById(R.id.cadastroCriatura1Id);
            cadastroCriatura2=findViewById(R.id.cadastroCriatura2Id);
            cadastroCriatura3=findViewById(R.id.cadastroCriatura3Id);
            cadastroCriatura1.setText(startCriatura.get(0).getNomeCriatura());
            cadastroCriatura2.setText(startCriatura.get(1).getNomeCriatura());
            cadastroCriatura3.setText(startCriatura.get(2).getNomeCriatura());
            jogador.getCriaturas().add(startCriatura.get(0));

        }
    };

    public View.OnClickListener chamadaFimCadastro=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setContentView(R.layout.perfiljogador);
            Log.i("fim: ","cadastrojogador concluido");
            botaoCriatura1=findViewById(R.id.botaoCriatura1Id);
            botaoCriatura2=findViewById(R.id.botaoCriatura2Id);

            botaoCriatura1.setOnClickListener(chamadaCriatura);
            botaoCriatura2.setOnClickListener(chamadaCriatura);
            botaosair=findViewById(R.id.sairToEntrarId);
            botaosair.setOnClickListener(chamadaVoltarInicio);
            visaogeralNome=findViewById(R.id.visaogeralNomeId);
            visaogeralApelido=findViewById(R.id.visaogeralApelidoId);
            visaogeralNome.setText(jogador.getNome().toString());
            visaogeralApelido.setText(jogador.getApelido().toString());

            int qtd;

            Log.i("xxx","valor " + jogador.getItens().get(0).getQuantItem());
            if ( jogador.getItens().get(0).getQuantItem() > 0 ){
                findViewById(R.id.idBolinhaVermelha).setVisibility(View.GONE);
            }
            qtd = jogador.getItens().get(1).getQuantItem();
            if (qtd > 0 ){
                findViewById(R.id.idBolinhaAmarela).setVisibility(View.GONE);
            }
            qtd = jogador.getItens().get(2).getQuantItem();
            if (qtd > 0 ){
                findViewById(R.id.idBolinhaAzul).setVisibility(View.GONE);
            }
            qtd = jogador.getItens().get(3).getQuantItem();
            if (qtd > 0 ){
                findViewById(R.id.idMelancia).setVisibility(View.GONE);
            }
            qtd = jogador.getItens().get(4).getQuantItem();
            if (qtd > 0 ){
                findViewById(R.id.idOvo).setVisibility(View.GONE);
            }

        }
    };


} // FIM MainActivity

