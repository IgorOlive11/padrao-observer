package com.igoroliveira.observer;

/**
 * Interface Observer.
 * Todo interessado em variacoes de preco de acoes implementa esta interface.
 */
public interface ObservadorAcao {
    void notificar(String ticker, double precoAnterior, double precoAtual);
}
