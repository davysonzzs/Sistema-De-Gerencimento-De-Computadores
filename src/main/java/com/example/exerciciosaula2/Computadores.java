package com.example.exerciciosaula2;


public class Computadores {
    public boolean computador1 = false;
    public boolean computador2 = false;
    public boolean computador3 = false;
    int computadorSelecionado = 0;

    public void Computadores(int computadorSelecionado){
        this.computadorSelecionado = computadorSelecionado;
    };

    public boolean[] mostrarComputadores(){
        boolean[] comps = {computador1, computador2, computador3};
        return comps;
    };

    public boolean ocupar(){
        if (computadorSelecionado == 1){
            if (!computador1) {
                computador1 = true;
                return computador1;
            } else {
                return false;
            }
        } else if (computadorSelecionado == 2){
            if (!computador2) {
                computador2 = true;
                return computador2;
            } else {
                return false;
            }
        } else if (computadorSelecionado == 3) {
            if (!computador3){
                computador3 = true;
                return computador3;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean desocupar(){
        if (computadorSelecionado == 1){
            if (computador1) {
                computador1 = false;
                return computador1;
            } else {
                return true;
            }
        } else if (computadorSelecionado == 2){
            if (computador2) {
                computador2 = false;
                return computador2;
            } else {
                return true;
            }
        } else if (computadorSelecionado == 3) {
            if (computador3){
                computador3 = false;
                return computador3;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
}

