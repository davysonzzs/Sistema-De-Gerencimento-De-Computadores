package com.example.exerciciosaula2;

public class Computador {
        private String nome;
        private String ip;
        private boolean emUso;

        public Computador(String nome, String ip){
            this.nome = nome;
            this.ip = ip;
            this.emUso = false;
        }

        public String getNome() {
            return nome;
        }

        public String getIp() {
            return ip;
        }

        public boolean isEmUso() {
            return emUso;
        }

        public void setEmUso(boolean emUso) {
            this.emUso = emUso;
        }
}
