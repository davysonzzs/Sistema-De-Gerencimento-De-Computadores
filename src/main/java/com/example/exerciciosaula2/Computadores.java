package com.example.exerciciosaula2;


import java.util.ArrayList;
import java.util.List;

public class Computadores {
    private List<Computador> comps = new ArrayList<Computador>();
    private int computadorSelecionado = -1;

    public Computadores(){
        comps.add(new Computador("comp1", "127.0.0.1"));
        comps.add(new Computador("comp2", "000.000.000"));
        comps.add(new Computador("comp3", "000.000.000"));
    };

    public List<Computador> mostrarComputadores(){
        return comps;
    };

    public void setComputadorSelecionado(int indicie){
        if (indicie >= 0 && indicie < comps.size()){
            this.computadorSelecionado = indicie;
        }
    }

    public int mostrarComputadorSelecionado(){
        return computadorSelecionado;
    }

    public Computador mostrarComputadorSelecionadoObjeto() {
        if (computadorSelecionado >= 0 && computadorSelecionado < comps.size()) {
            return comps.get(computadorSelecionado);
        }
        return null;
    }

    public boolean ocupar() {
        Computador comp = mostrarComputadorSelecionadoObjeto();
        if (comp != null) {
            return false;
        }
        boolean estaBlock = ComandoRemoto.estaBloqueado(comp.getIp());
        if (!estaBlock){
            comp.setEmUso(true);
            return false;
        }

        comp.setEmUso(true);
        return true;
    }

    public boolean desocupar() {
        Computador comp = mostrarComputadorSelecionadoObjeto();
        if (comp != null) {
            return false;
        }
        boolean estaBlock = ComandoRemoto.estaBloqueado(comp.getIp());

        if (estaBlock){
            comp.setEmUso(false);
            return true;
        }

        comp.setEmUso(false);
        return true;
    }


}

