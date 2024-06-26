package Entity;

public class Entidade {

    private int x;
    private int y;

    private String nome;
    private boolean bloqueado;

    public Entidade(boolean bloqueado, String nome, int x, int y) {
        this.bloqueado = bloqueado;
        this.nome = nome;
        this.x = x;
        this.y = y;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}
