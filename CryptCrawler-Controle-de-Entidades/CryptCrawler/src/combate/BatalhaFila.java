package combate;

import java.util.Comparator;
import java.util.List;

/**
 * Classe BatalhaFila
 * Esta classe representa uma fila de personagens em uma batalha
 *
 * @param <E> o tipo de elementos nesta fila, que deve estender a classe Personagem
 */
public class BatalhaFila<E extends PersonagemCombate> {

    /**
     * Lista de personagens na batalha
     */
    private List<E> personagens;
    /**
     * O personagem atual na batalha
     */
    private E personagemAtual;

    /**
     * Constrói uma nova fila de batalha com uma lista de personagens
     *
     * @param personagens a lista de personagens para inicializar
     * @throws RuntimeException se a lista de personagens estiver fora dos limites
     */
    public BatalhaFila(List<E> personagens) {
        if(personagens.size() < 2) {
            throw new RuntimeException("A batalha precisa de pelo menos dois personagens");
        }
        this.personagens = personagens;
        this.personagemAtual = personagens.get(0);
        ordenarFila();
    }

    /**
     * Retorna os elementos da classe
     * @return a lista de personagens
     * @return o personagem atual
     */
    public List<E> getPersonagens() { return personagens; }
    public E getPersonagemAtual() { return personagemAtual; }

    /**
     * Ordena a fila de personagens por agilidade
     */
    private void ordenarFila() {
        personagens.sort(Comparator.comparingInt(PersonagemCombate::getAgilidade).reversed());
    }

    /**
     * Retorna o próximo personagem na fila
     *
     * @return o próximo personagem na fila
     */
    public E proximoPersonagem() {
        int index = personagens.indexOf(personagemAtual);
        index += 1;
        index %= personagens.size();
        personagemAtual = personagens.get(index);
        return personagemAtual;
    }

    /**
     * Remove um personagem da lista
     *
     * @param personagem o personagem a ser removido
     * @throws RuntimeException se a lista estiver vazia
     * @throws RuntimeException se o personagem não existir na lista
     */
    public void removerPersonage(E personagem) {
        if(personagens.isEmpty()) {
            throw new RuntimeException("A lista está vazia");
        }
        else if(!personagens.contains(personagem)) {
            throw new RuntimeException("Personagem não existe na lista");
        }
        else {
            personagens.remove(personagem);
        }
    }

    /**
     * Adiciona um personagem à lista
     *
     * @param personagem o personagem a ser adicionado
     * @throws RuntimeException se o personagem já estiver na lista
     */
    public void adicionarPersonagem(E personagem) {
        if(personagens.contains(personagem)) {
            throw new RuntimeException("Personagem já está na lista");
        }
        else {
            personagens.add(personagem);
        }
    }
}
