package com.igoroliveira.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer concreto: investidor que acompanha variacoes de preco.
 * Registra as notificacoes recebidas para consulta posterior.
 */
public class AlertaInvestidor implements ObservadorAcao {

    private final String nome;
    private final List<String> historico = new ArrayList<>();
    private String ultimaNotificacao;

    public AlertaInvestidor(String nome) {
        this.nome = nome;
    }

    @Override
    public void notificar(String ticker, double precoAnterior, double precoAtual) {
        double variacao = ((precoAtual - precoAnterior) / precoAnterior) * 100;
        String tendencia = precoAtual > precoAnterior ? "ALTA" : "BAIXA";
        ultimaNotificacao = String.format(
                "[%s] %s: R$%.2f -> R$%.2f (%s %.2f%%)",
                nome, ticker, precoAnterior, precoAtual, tendencia, Math.abs(variacao));
        historico.add(ultimaNotificacao);
        System.out.println(ultimaNotificacao);
    }

    public String getNome() {
        return nome;
    }

    public String getUltimaNotificacao() {
        return ultimaNotificacao;
    }

    public List<String> getHistorico() {
        return historico;
    }
}
