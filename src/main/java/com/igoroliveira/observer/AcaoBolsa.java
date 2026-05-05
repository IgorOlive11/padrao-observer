package com.igoroliveira.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject (publicador) do padrao Observer.
 * Representa uma acao na bolsa de valores.
 * Notifica todos os observadores registrados quando o preco muda.
 */
public class AcaoBolsa {

    private final String ticker;
    private double preco;
    private final List<ObservadorAcao> observadores = new ArrayList<>();

    public AcaoBolsa(String ticker, double precoInicial) {
        this.ticker = ticker;
        this.preco = precoInicial;
    }

    public void adicionarObservador(ObservadorAcao observador) {
        observadores.add(observador);
    }

    public void removerObservador(ObservadorAcao observador) {
        observadores.remove(observador);
    }

    public void setPreco(double novoPreco) {
        double precoAnterior = this.preco;
        this.preco = novoPreco;
        if (Double.compare(precoAnterior, novoPreco) != 0) {
            notificarObservadores(precoAnterior, novoPreco);
        }
    }

    private void notificarObservadores(double precoAnterior, double precoAtual) {
        for (ObservadorAcao obs : observadores) {
            obs.notificar(ticker, precoAnterior, precoAtual);
        }
    }

    public double getPreco() {
        return preco;
    }

    public String getTicker() {
        return ticker;
    }

    public int getNumeroDeObservadores() {
        return observadores.size();
    }
}
