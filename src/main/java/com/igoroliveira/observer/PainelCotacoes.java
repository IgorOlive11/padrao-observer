package com.igoroliveira.observer;

/**
 * Observer concreto: painel de cotacoes que exibe alertas visuais
 * quando a variacao supera um limite configurado.
 */
public class PainelCotacoes implements ObservadorAcao {

    private final double limiteAlertaPercent;
    private int alertasDisparados = 0;
    private String ultimoAlerta;

    public PainelCotacoes(double limiteAlertaPercent) {
        this.limiteAlertaPercent = limiteAlertaPercent;
    }

    @Override
    public void notificar(String ticker, double precoAnterior, double precoAtual) {
        double variacao = Math.abs(((precoAtual - precoAnterior) / precoAnterior) * 100);
        if (variacao >= limiteAlertaPercent) {
            ultimoAlerta = String.format(
                    "[PAINEL] ALERTA: %s variou %.2f%% (limite: %.0f%%)",
                    ticker, variacao, limiteAlertaPercent);
            System.out.println(ultimoAlerta);
            alertasDisparados++;
        }
    }

    public int getAlertasDisparados() {
        return alertasDisparados;
    }

    public String getUltimoAlerta() {
        return ultimoAlerta;
    }
}
