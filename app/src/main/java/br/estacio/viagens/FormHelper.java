package br.estacio.viagens;

import android.widget.EditText;
import android.widget.RatingBar;

public class FormHelper {

    private EditText txtDestino;
    private EditText txtDiario;
    private RatingBar ratingBar;
    private Viagem viagem;

    public FormHelper(NovaViagemActivity activity){
        txtDestino = (EditText) activity.findViewById(R.id.txtDestino);
        txtDiario = (EditText) activity.findViewById(R.id.txtDiario);
        ratingBar = (RatingBar) activity.findViewById(R.id.ratingBar);

        viagem = new Viagem();
    }

    public Viagem selecionaViagem(){
        viagem.setDestino(txtDestino.getText().toString());
        viagem.setDiario(txtDiario.getText().toString());
        viagem.setNota(Double.valueOf(ratingBar.getProgress()));
        return viagem;
    }

    public void preencheForm(Viagem viagem){
        txtDestino.setText((viagem.getDestino()));
        txtDiario.setText(viagem.getDiario());
        ratingBar.setProgress(viagem.getNota().intValue());

        this.viagem = viagem;
    }

}
