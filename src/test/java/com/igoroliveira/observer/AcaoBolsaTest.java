package com.igoroliveira.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AcaoBolsaTest {

    private AcaoBolsa acao;
    private AlertaInvestidor investidor;

    @BeforeEach
    void setUp() {
        acao = new AcaoBolsa("PETR4", 38.50);
        investidor = new AlertaInvestidor("Teste");
        acao.adicionarObservador(investidor);
    }

    @Test
    void precoInicialECorreto() {
        assertEquals(38.50, acao.getPreco(), 0.001);
    }

    @Test
    void observadorENotificadoQuandoPrecoMuda() {
        acao.setPreco(40.00);
        assertNotNull(investidor.getUltimaNotificacao());
        assertEquals(1, investidor.getHistorico().size());
    }

    @Test
    void observadorNaoENotificadoQuandoPrecoNaoMuda() {
        acao.setPreco(38.50);
        assertEquals(0, investidor.getHistorico().size());
    }

    @Test
    void multiplosObservadoresReceberamNotificacao() {
        AlertaInvestidor segundo = new AlertaInvestidor("Segundo");
        acao.adicionarObservador(segundo);

        acao.setPreco(40.00);

        assertEquals(1, investidor.getHistorico().size());
        assertEquals(1, segundo.getHistorico().size());
    }

    @Test
    void observadorRemovidoNaoRecebeNotificacao() {
        acao.removerObservador(investidor);
        acao.setPreco(45.00);
        assertEquals(0, investidor.getHistorico().size());
    }

    @Test
    void historicoRegistraTodasAsNotificacoes() {
        acao.setPreco(40.00);
        acao.setPreco(35.00);
        acao.setPreco(42.00);
        assertEquals(3, investidor.getHistorico().size());
    }

    @Test
    void painelDisparaAlertaApenasParaVariacaoAcimaDeLimite() {
        PainelCotacoes painel = new PainelCotacoes(5.0);
        acao.adicionarObservador(painel);

        acao.setPreco(39.00); // variacao de ~1.3%, nao dispara
        assertEquals(0, painel.getAlertasDisparados());

        acao.setPreco(41.50); // variacao de ~6.4%, dispara
        assertEquals(1, painel.getAlertasDisparados());
    }

    @Test
    void contadorDeObservadoresECorreto() {
        assertEquals(1, acao.getNumeroDeObservadores());
        acao.adicionarObservador(new AlertaInvestidor("Extra"));
        assertEquals(2, acao.getNumeroDeObservadores());
        acao.removerObservador(investidor);
        assertEquals(1, acao.getNumeroDeObservadores());
    }

    @Test
    void notificacaoContemTickerCorreto() {
        acao.setPreco(40.00);
        assertTrue(investidor.getUltimaNotificacao().contains("PETR4"));
    }
}
