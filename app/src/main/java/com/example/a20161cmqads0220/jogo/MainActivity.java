package com.example.a20161cmqads0220.jogo;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button botaoEntrar;
    private Button botaoVoltar;
    private Button botaoAvancar;
    private Button botaosair;
    private Button botaoCriatura1;
    private Button botaoCriatura2;
    private Button botaoLoja;
    private Button botaoMundo;
    private Button botaoCriaturaVoltar;
    private Button botaoCadastrar;
    private Button botaoCadastrarFinal;
    private Button botaoFinalizaCadastro;

    private Button addBolaVer;
    private Button addBolaAma;
    private Button addBolaAzu;
    private Button addMelLeit;
    private Button addOvoYosh;

    private Button botaoAddBixo;

    private ImageView remBolaVer;
    private ImageView remBolaAma;
    private ImageView remBolaAzu;
    private ImageView remMelLeit;
    private ImageView remOvoYosh;


    private TextView nome;
    private TextView senha;
    private TextView apelido;
    private TextView email;
    private TextView visaogeralNome;
    private TextView visaogeralApelido;
    private TextView cadastroParte2Nome;
    private TextView cadastroParte2Apelido;
    private TextView pilas;
    private TextView valor;
    Jogador jogador = new Jogador();
    private ArrayList<Criatura> startCriatura;
    private ArrayList<Itens> startItens;
    private TextView cadastroCriatura1;
    private TextView cadastroCriatura2;
    private TextView cadastroCriatura3;

    private ListView lista;

    private SharedPreferences arquivo;

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
        Criatura c1=new Criatura();
        Criatura c2=new Criatura();
        Criatura c3=new Criatura();
        c1.setNomeCriatura("monstro 1");
        c2.setNomeCriatura("monstro 2");
        c3.setNomeCriatura("monstro 3");
        c1.setAtaque(Integer.toString(50));
        startCriatura.add(c1);
        startCriatura.add(c2);
        startCriatura.add(c3);

        startItens = new ArrayList();
        Itens i1 = new Itens("Pokebola Vermelha",0,10);
        Itens i2 = new Itens("Pokebola Azul",0,20);
        Itens i3 = new Itens("Pokebola Amarela",0,30);
        Itens i4 = new Itens("Melancia C/ Leite",0,15);
        Itens i5 = new Itens("Ovo do Yoshi",0,50);

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
            arquivo = getSharedPreferences("jogador",Context.MODE_PRIVATE);

            jogador.setNome(arquivo.getString("usuario","PokoUsuario"));
            jogador.setSenha(arquivo.getString("senha","0"));

            if (jogador.getNome().equals(nome.getText().toString())
                    && jogador.getSenha().equals(senha.getText().toString())) {
                IniciaPerfil();
            } else {
                Toast.makeText(getApplicationContext(), "Erro, tente novamente!", Toast.LENGTH_SHORT).show();
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
            IniciaPerfil();
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


            jogador.setCriaturas(lerArquivo(getFileStreamPath("bixinhos")));


            arquivo = getSharedPreferences("jogador",Context.MODE_PRIVATE);
            SharedPreferences.Editor edita = arquivo.edit();

            edita.putString("usuario", jogador.getNome());
            edita.putString("senha", jogador.getSenha());

            edita.commit();

            lista = findViewById(R.id.listCriatura);
            CriaturaAdapter listinhaTretosa;
            listinhaTretosa = new CriaturaAdapter(view.getContext(),lerArquivo(getFileStreamPath("bixinhos")));
            lista.setAdapter(listinhaTretosa);

        }
    };

    public View.OnClickListener AddBixo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Criatura cr = new Criatura();
            cr.setNomeCriatura("criatura " + (int) (Math.random()*11));
            cr.setAtaque(" " + (int) Math.random()*11);
            cr.setDefesa(" " + (int) Math.random()*11);
            cr.setPontos((int) Math.random()*11);
            cr.setVida(" " +Math.random()*11);
            jogador.getCriaturas().add(cr);
            gravaArquivo(getFileStreamPath("bixinhos"), jogador.getCriaturas());
        }
    };

    public View.OnClickListener chamadaTelaLoja=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setContentView(R.layout.lojaitens);
            botaoVoltar=findViewById(R.id.botaoVoltar);
            botaoVoltar.setOnClickListener(chamadaCriaturaVoltar);

            pilas=findViewById(R.id.pilasJogador);
            pilas.setText(Integer.toString(jogador.getPontos()));

            valor=findViewById(R.id.valorBolaVerm);
            valor.setText(Integer.toString(startItens.get(0).getValor()));
            valor=findViewById(R.id.qtdBolaVerm);
            valor.setText(Integer.toString(jogador.getItens().get(0).getQuantItem()));

            valor=findViewById(R.id.qtdBolaAzul);
            valor.setText(Integer.toString(startItens.get(1).getValor()));
            valor=findViewById(R.id.qtdBolaAzul);
            valor.setText(Integer.toString(jogador.getItens().get(1).getQuantItem()));

            valor=findViewById(R.id.valorBolaAmarela);
            valor.setText(Integer.toString(startItens.get(2).getValor()));
            valor=findViewById(R.id.qtdBolaAmarela);
            valor.setText(Integer.toString(jogador.getItens().get(2).getQuantItem()));

            valor=findViewById(R.id.valorMelanciaLeite);
            valor.setText(Integer.toString(startItens.get(3).getValor()));
            valor=findViewById(R.id.qtdMelanciaLeite);
            valor.setText(Integer.toString(jogador.getItens().get(3).getQuantItem()));

            valor=findViewById(R.id.valorOvo);
            valor.setText(Integer.toString(startItens.get(4).getValor()));
            valor=findViewById(R.id.qtdOvo);
            valor.setText(Integer.toString(jogador.getItens().get(4).getQuantItem()));


            addBolaVer=findViewById(R.id.addBolaVerm);
            addBolaVer.setOnClickListener(adicionaBolaVermelha);

            addBolaAzu=findViewById(R.id.addBolaAzul);
            addBolaAzu.setOnClickListener(adicionaBolaAzul);

            addBolaAma=findViewById(R.id.addBolaAma);
            addBolaAma.setOnClickListener(adicionaBolaAmarelo);

            addMelLeit=findViewById(R.id.addMelLeit);
            addMelLeit.setOnClickListener(adicionaMelanciaLeite);

            addOvoYosh=findViewById(R.id.addOvoYoshi);
            addOvoYosh.setOnClickListener(adicionaOvo);

        }
    };

    public View.OnClickListener adicionaBolaVermelha= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            incrementaItem(0);
            valor=findViewById(R.id.qtdBolaVerm);
            Log.i("bolaRed"," ... "+jogador.getItens().get(0).getQuantItem());
            valor.setText(Integer.toString(jogador.getItens().get(0).getQuantItem()));
        }
    };
    public View.OnClickListener adicionaBolaAzul= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            incrementaItem(1);
            valor=findViewById(R.id.qtdBolaAzul);
            valor.setText(Integer.toString(jogador.getItens().get(1).getQuantItem()));
        }
    };
    public View.OnClickListener adicionaBolaAmarelo= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            incrementaItem(2);
            valor=findViewById(R.id.qtdBolaAmarela);
            valor.setText(Integer.toString(jogador.getItens().get(2).getQuantItem()));
        }
    };
    public View.OnClickListener adicionaMelanciaLeite= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            incrementaItem(3);
            valor=findViewById(R.id.qtdMelanciaLeite);
            valor.setText(Integer.toString(jogador.getItens().get(3).getQuantItem()));
        }
    };
    public View.OnClickListener adicionaOvo= new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            incrementaItem(4);
            valor=findViewById(R.id.qtdOvo);
            valor.setText(Integer.toString(jogador.getItens().get(4).getQuantItem()));
        }
    };
    public void incrementaItem(int i) {
        if (jogador.getItens().get(i).getValor() <= jogador.getPontos()) {
            int item = jogador.getItens().get(i).getQuantItem();
            item++;
            jogador.getItens().get(i).setQuantItem(item);
            jogador.setPontos(jogador.getPontos() - jogador.getItens().get(i).getValor());
            pilas.setText(Integer.toString(jogador.getPontos()));
        } else {
            Toast.makeText(getApplicationContext(),"Faça hoje seu* cartão Jogo dos Bixo Gold",Toast.LENGTH_SHORT).show();
        }
    }


    public class CriaturaAdapter extends ArrayAdapter<Criatura> {

        private Context context;
        private ArrayList<Criatura> criaturas = null;
        public  CriaturaAdapter(Context context,ArrayList<Criatura> criaturas) {
            super(context, android.R.layout.simple_list_item_1, criaturas);
            this.context = context;
            this.criaturas = criaturas;
        }
        @Override
        public  View getView(int position, View view, ViewGroup parent) {
            final Criatura criatura = criaturas.get(position);

            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.itemcriatura, null);
            }
            TextView nomeCriatura = (TextView) view.findViewById(R.id.nomeCriatura);
            nomeCriatura.setText(criatura.getNomeCriatura());

            return view;
        }
    }

    public void gravaArquivo(File arq, ArrayList<Criatura> bixos){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try{
            fos = new FileOutputStream(arq);
            oos = new ObjectOutputStream(fos);

            for (Criatura c: bixos) {
                oos.writeObject(c);
            }

            oos.close();
            fos.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<Criatura> lerArquivo(File arq){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        ArrayList<Criatura> bixinos = new ArrayList<Criatura>();
        try{
            fis = new FileInputStream(arq);
            ois = new ObjectInputStream(fis);
            while (true) {
                bixinos.add((Criatura) ois.readObject());
            }
        } catch (EOFException e){}
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                ois.close();
                fis.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return bixinos;
    }

    public View.OnClickListener removeBolaVermelha = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            decrementaItem(0);
            if (jogador.getItens().get(0).getQuantItem() > 0) {
                valor = findViewById(R.id.QuantBolaVermelha);
                valor.setText(Integer.toString(jogador.getItens().get(0).getQuantItem()));
            } else {
                findViewById(R.id.idBolinhaVermelha).setVisibility(View.GONE);
            }
        }
    };
    public View.OnClickListener removeBolaAzul = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            decrementaItem(1);
            if (jogador.getItens().get(1).getQuantItem() > 0) {
                valor=findViewById(R.id.QuantBolaAzul);
                valor.setText(Integer.toString(jogador.getItens().get(1).getQuantItem()));
            } else {
                findViewById(R.id.idBolinhaAzul).setVisibility(View.GONE);
            }
        }
    };
    public View.OnClickListener removeBolaAmarela = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            decrementaItem(2);
            if (jogador.getItens().get(2).getQuantItem() > 0) {
                valor = findViewById(R.id.QuantBolaAmarela);

                valor.setText(Integer.toString(jogador.getItens().get(2).getQuantItem()));
            } else {
                findViewById(R.id.idBolinhaAmarela).setVisibility(View.GONE);
            }
        }
    };
    public View.OnClickListener removeMelancia = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            decrementaItem(3);
            if (jogador.getItens().get(3).getQuantItem() > 0) {
                valor = findViewById(R.id.QuantMelancia);
                valor.setText(Integer.toString(jogador.getItens().get(3).getQuantItem()));
            } else {
                findViewById(R.id.idMelancia).setVisibility(View.GONE);
            }
        }
    };
    public View.OnClickListener removeOvo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            decrementaItem(4);
            if (jogador.getItens().get(4).getQuantItem() > 0) {
                valor = findViewById(R.id.QuantOvo);
                valor.setText(Integer.toString(jogador.getItens().get(4).getQuantItem()));
            } else {
                findViewById(R.id.idOvo).setVisibility(View.GONE);
            }
        }
    };

    public void decrementaItem(int i) {
        int item = jogador.getItens().get(i).getQuantItem();
        item--;
        jogador.getItens().get(i).setQuantItem(item);
        jogador.setPontos(jogador.getPontos() + (jogador.getItens().get(i).getValor())/2);
        pilas.setText(Integer.toString(jogador.getPontos()));

        valor = findViewById(R.id.visaogeralPila);
        valor.setText(Integer.toString(jogador.getPontos()));
    }

    public View.OnClickListener chamadaFimCadastro=new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Log.i("fim: ","cadastrojogador concluido");
            IniciaPerfil();

        }
    };

    public View.OnClickListener chamaGps = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            solicitarPermissao();
        }

    };
    public View.OnClickListener chamaMundo = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IniciaMundo();
        }
    };
    private void IniciaMundo(){
        setContentView(R.layout.uordi);
        botaoAvancar= findViewById(R.id.idpegagps);
        botaoAvancar.setOnClickListener(chamaGps);
    }

    private void IniciaPerfil(){
        setContentView(R.layout.perfiljogador);
        botaoCriatura1=findViewById(R.id.botaoCriatura1Id);
        botaoCriatura2=findViewById(R.id.botaoCriatura2Id);
        botaoLoja=findViewById(R.id.btnLoja);

        botaoCriatura1.setOnClickListener(chamadaCriatura);
        botaoCriatura2.setOnClickListener(chamadaCriatura);
        botaoLoja.setOnClickListener(chamadaTelaLoja);

        botaoAddBixo = findViewById(R.id.AdicionaBixo);
        botaoAddBixo.setOnClickListener(AddBixo);

        botaosair=findViewById(R.id.sairToEntrarId);
        botaosair.setOnClickListener(chamadaVoltarInicio);

        botaoMundo=findViewById(R.id.mundo);
        botaoMundo.setOnClickListener(chamaMundo);

        visaogeralNome=findViewById(R.id.visaogeralNomeId);
        visaogeralApelido=findViewById(R.id.visaogeralApelidoId);

        visaogeralNome.setText(jogador.getNome().toString());
        visaogeralApelido.setText(jogador.getApelido().toString());

        valor = findViewById(R.id.visaogeralPila);
        valor.setText(Integer.toString(jogador.getPontos()));

        if ( jogador.getItens().get(0).getQuantItem() == 0 ){
            findViewById(R.id.idBolinhaVermelha).setVisibility(View.GONE);
        } else {
            TextView qtd = (TextView)findViewById(R.id.QuantBolaVermelha);
            qtd.setText(Integer.toString(jogador.getItens().get(0).getQuantItem()));

            remBolaVer=findViewById(R.id.remBolaVer);
            remBolaVer.setOnClickListener(removeBolaVermelha);
        }
        if (jogador.getItens().get(1).getQuantItem() == 0 ){
            findViewById(R.id.idBolinhaAzul).setVisibility(View.GONE);
        } else {
            TextView qtd = (TextView)findViewById(R.id.QuantBolaAzul);
            qtd.setText(Integer.toString(jogador.getItens().get(1).getQuantItem()));

            remBolaAzu=findViewById(R.id.remBolaAzu);
            remBolaAzu.setOnClickListener(removeBolaAzul);
        }
        if (jogador.getItens().get(2).getQuantItem() == 0 ){
            findViewById(R.id.idBolinhaAmarela).setVisibility(View.GONE);
        } else {
            TextView qtd = (TextView)findViewById(R.id.QuantBolaAmarela);
            qtd.setText(Integer.toString(jogador.getItens().get(2).getQuantItem()));

            remBolaAma=findViewById(R.id.remBolaAma);
            remBolaAma.setOnClickListener(removeBolaAmarela);
        }
        if (jogador.getItens().get(3).getQuantItem() == 0 ){
            findViewById(R.id.idMelancia).setVisibility(View.GONE);
        } else {
            TextView qtd = (TextView)findViewById(R.id.QuantMelancia);
            qtd.setText(Integer.toString(jogador.getItens().get(3).getQuantItem()));

            remMelLeit=findViewById(R.id.remMel);
            remMelLeit.setOnClickListener(removeMelancia);
        }
        if (jogador.getItens().get(4).getQuantItem() == 0 ){
            findViewById(R.id.idOvo).setVisibility(View.GONE);
        } else {
            TextView qtd = (TextView)findViewById(R.id.QuantOvo);
            qtd.setText(Integer.toString(jogador.getItens().get(4).getQuantItem()));

            remOvoYosh=findViewById(R.id.remOvo);
            remOvoYosh.setOnClickListener(removeOvo);
        }
    }

    private void solicitarPermissao() {
        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                && (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            configurarGps();
        }
    }
    private void configurarGps(){
        try{

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        } catch (SecurityException ex){
            Toast.makeText(this,"Deu treta no GPS!", Toast.LENGTH_LONG).show();
        }
    }
    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            ((TextView)findViewById(R.id.idlon)).setText(Double.toString(longitude));
            ((TextView)findViewById(R.id.idLat)).setText(Double.toString(latitude));

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

} // FIM MainActivity

