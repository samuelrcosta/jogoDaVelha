/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author samue
 */
public class Controlador {
    // tabuleiro
    private int tabuleiro[][];
    // Número que indica o próximo jogador (1 ou 2)
    private int jogador;
    // Número que indica quantos campos foram marcados
    private int marcados;
    //Instancia da classe que controla a tela
    private MainWindow screen;
    
    public Controlador(MainWindow tela){
        this.screen = tela;
        this.tabuleiro = new int[3][3];
        this.jogador = 1;
        this.marcados = 0;
        // Zera o tabuleiro
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                this.tabuleiro[i][j] = 0;
            }
        }
    }

    public int[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(int i, int j, int valor) {
        this.tabuleiro[i][j] = valor;
    }

    public int getJogador() {
        return jogador;
    }

    public void setJogador(int jogador) {
        this.jogador = jogador;
    }

    public int getMarcados() {
        return marcados;
    }

    public void setMarcados(int marcados) {
        this.marcados = marcados;
    }
    
    public void novoJogo(){
        // Zera o tabuleiro
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                this.setTabuleiro(i, j, 0);
            }
        }
        // Zera as demais variáveis
        this.setJogador(1);
        this.setMarcados(0);
    }
    
    public int marcar(int i, int j){
        // Declara a variável de retorno
        int retorno;
        // Pega o tabuleiro
        int tab[][] = this.getTabuleiro();
        // Verifica se o campo já não está marcado (preenchido com 1 ou 2)
        if(tab[i][j] != 0){
            // retorna zero
            retorno = 0;
        }else{
            // Pega o jogador e a quantidade de marcados
            int jog = this.getJogador();
            int cont = this.getMarcados();
            // Marca o tabuleiro
            this.setTabuleiro(i, j, jog);
            // Aumenta o marcador
            this.setMarcados(cont + 1);
            // Verifica o ganhador
            this.verificar();
            // Define o retorno para ser o jogador
            retorno = jog;
            // Muda para o próximo jogador
            if(jog == 1){
                this.setJogador(2);
            }else{
                this.setJogador(1);
            }
            // Mostra na tela qual o próximo jogador
            this.screen.mostraProximoJogador(this.getJogador());
        }
        
        return retorno;
    }
    
    public void verificar(){
        int cont = this.getMarcados();
        // Primeiro verifica se existe algum ganhador
        /*
        Tabuleiro:
        [0,0][0,1][0,2]
        [1,0][1,1][1,2]
        [2,0][2,1][2,2]
        */
        // Busca se existe algum ganhador
        int ganhador = this.checarGanhador();
        // Caso retorna zero não existem ganhadores
        if(ganhador != 0){
            this.screen.alerta("O jogador " + ganhador + " ganhou!");
        }
        // Verifica se não deu velha
        else if(cont == 9){
            this.screen.alerta("Deu velha");
        }
    }
    
    public int checarGanhador(){
        // Define o retorno como 0
        int retorno = 0;
        int tab[][] = this.getTabuleiro();
        // Printa no console o tabuleiro
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.print(tab[i][j] + " ");
            }
            System.out.print("\n");
        }
        // Checa as possibilidades
        if(tab[0][0] != 0){
            if(tab[0][1] == tab[0][0]){
                if(tab[0][2] == tab[0][0]){
                    return tab[0][0];
                }
            }
            if(tab[1][1] == tab[0][0]){
                if(tab[2][2] == tab[0][0]){
                    return tab[0][0];
                }
            }
            if(tab[1][0] == tab[0][0]){
                if(tab[2][0] == tab[0][0]){
                    return tab[0][0];
                }
            }
        }
        if(tab[0][1] != 0){
            if(tab[1][1] == tab[0][1]){
                if(tab[2][1] == tab[0][1]){
                    return tab[0][1];
                }
            }
        }
        if(tab[0][2] != 0){
            if(tab[1][2] == tab[0][2]){
                if(tab[2][2] == tab[0][2]){
                    return tab[0][2];
                }
            }
        }
        if(tab[1][0] != 0){
            if(tab[1][1] == tab[1][0]){
                if(tab[1][2] == tab[1][0]){
                    return tab[1][0];
                }
            }
        }
        if(tab[2][0] != 0){
            if(tab[1][1] == tab[2][0]){
                if(tab[0][2] == tab[2][0]){
                    return tab[2][0];
                }
            }
            if(tab[2][1] == tab[2][0]){
                if(tab[2][2] == tab[2][0]){
                    return tab[2][0];
                }
            }
        }
        // Caso chegue até aqui não foi encotrado nenhum ganhador, retorna 0
        return retorno;
    }
}
