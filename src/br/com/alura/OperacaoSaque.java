package br.com.alura;

import java.math.BigDecimal;

public class OperacaoSaque implements Runnable {

    private Conta conta;
    private BigDecimal valor;

    public OperacaoSaque(Conta conta, BigDecimal valor) {
        this.conta = conta;
        this.valor = valor;
    }

    /**
     * Método que realiza a operação de saque.
     * É necessário sincronizar o método para que a operação seja realizada de forma correta.
     */
    public synchronized void executa() {
        System.out.println("Iniciando operação de saque.");
        var saldoAtual = conta.getSaldo();

        if (saldoAtual.compareTo(valor) >= 0) {
            System.out.println("Debitando valor da conta");
            conta.debitaSaldo(valor);
            System.out.println("Saldo atual: " +conta.getSaldo());
        }
        System.out.println("Finalizando operação de saque.");
    }

    /**
     * Método run() da interface Runnable.
     * É necessárion informar para o método qual método será executado
     */
    @Override
    public void run() {
        executa();
        System.out.println(Thread.currentThread().getName());
    }
}
