package br.estacio.viagens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NovaViagemActivity extends AppCompatActivity {
    private FormHelper helper;
    private Viagem viagem;
    private ViagemDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_viagem);

        helper = new FormHelper(NovaViagemActivity.this);
        dao = new ViagemDAO(NovaViagemActivity.this);

        recebeViagem();

    }

    private void recebeViagem(){
        Intent intent = getIntent();
        Viagem viagem = (Viagem) intent.getSerializableExtra("viagem");

        if(viagem != null){
            helper.preencheForm(viagem);
        }
    }

    public void btnCancelarClick(View view){
        finish();
    }


    public void btnSalvarClick(View view){
        viagem = helper.selecionaViagem();

        if(viagem.getId() != null){
            dao.alteraViagem(viagem);
            Toast.makeText(NovaViagemActivity.this, "Viagem alterada com sucesso!", Toast.LENGTH_SHORT).show();
        }else{
            dao.gravaViagem(viagem);
            Toast.makeText(NovaViagemActivity.this, "Viagem gravada com sucesso!", Toast.LENGTH_SHORT).show();

        }
        finish();
    }
}
