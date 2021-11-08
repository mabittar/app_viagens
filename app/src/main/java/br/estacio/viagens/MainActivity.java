package br.estacio.viagens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViagemDAO dao;
    private ListView listViewdeViagens;
    private List<Viagem> listaDeViagem;
    private ArrayAdapter<Viagem> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewdeViagens = (ListView) findViewById(R.id.listviewViagens);
        registerForContextMenu(listViewdeViagens);

        listViewdeViagens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Viagem viagem = (Viagem) listViewdeViagens.getItemAtPosition(i);
                Intent novaViagem = new Intent(MainActivity.this, NovaViagemActivity.class);
                novaViagem.putExtra("viagem", viagem);
                startActivity(novaViagem);
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        atualizaLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuNovo:
                Intent novaViagem = new Intent(MainActivity.this, NovaViagemActivity.class);
                startActivity(novaViagem);
                break;

            case R.id.menuFechar:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem apagar = menu.add("Apagar");

        apagar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Viagem viagem = (Viagem) listViewdeViagens.getItemAtPosition(info.position);
                dao.apagaViagem(viagem);
                atualizaLista();
                Toast.makeText(MainActivity.this,
                        "Viagem apagada com sucesso!",
                        Toast.LENGTH_SHORT).show();

                return false;
            }
        });
    }

    private void atualizaLista(){
        dao = new ViagemDAO(MainActivity.this);
        listaDeViagem = dao.buscaViagem();

        adapter = new ArrayAdapter<Viagem>(MainActivity.this, android.R.layout.simple_list_item_1, listaDeViagem);
        listViewdeViagens.setAdapter(adapter);

    }

}