package com.example.a20161cmqads0220.jogo;

public class Itens {

    private String nomeItem ;
    private int quantItem;
    private int valor;



    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public Itens(String nomeItem, int quantItem, int valor) {
        this.nomeItem = nomeItem;
        this.quantItem = quantItem;
        this.valor = valor;
    }

    public int getQuantItem() {

        return quantItem;
    }

    public void setQuantItem(int quantItem) {
        this.quantItem = quantItem;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
