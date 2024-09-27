package player;

import javafx.scene.shape.Circle;
import projectile.Projectile;

/**
 * A classe Player representa o jogador no mini-game.
 * O jogador é um objeto que pode se mover em diferentes direções
 * e disparar projéteis.
 */
public class Player {

    // O círculo que representa o jogador.
    private Circle playerCircle;

    // Velocidade de movimento do jogador.
    private double moveSpeed = 1.0;

    // Última direção que o jogador se moveu, em graus.
    private double lastDirection = 0;

    /**
     * Construtor da classe Player.
     *
     * @param x Posição inicial X do jogador.
     * @param y Posição inicial Y do jogador.
     * @param radius Raio do círculo do jogador.
     */
    public Player(double x, double y, double radius) {
        // Inicializa o círculo do jogador.
        playerCircle = new Circle(radius);
        playerCircle.setCenterX(x);
        playerCircle.setCenterY(y);
        playerCircle.setFill(javafx.scene.paint.Color.GREEN); // Define a cor do círculo.
    }

    /**
     * Move o jogador para cima.
     */
    public void moveUp() {
        playerCircle.setCenterY(playerCircle.getCenterY() - moveSpeed);
        lastDirection = 270;
    }

    /**
     * Move o jogador para baixo.
     */
    public void moveDown() {
        playerCircle.setCenterY(playerCircle.getCenterY() + moveSpeed);
        lastDirection = 90;
    }

    /**
     * Move o jogador para a esquerda.
     */
    public void moveLeft() {
        playerCircle.setCenterX(playerCircle.getCenterX() - moveSpeed);
        lastDirection = 180;
    }

    /**
     * Move o jogador para a direita.
     */
    public void moveRight() {
        playerCircle.setCenterX(playerCircle.getCenterX() + moveSpeed);
        lastDirection = 0;
    }

    /**
     * Dispara um projétil na direção atual do jogador.
     *
     * @return Um objeto Projectile que representa o projétil disparado.
     */
    public Projectile shoot() {
        // Cria um novo projétil na posição do jogador.
        return new Projectile(playerCircle.getCenterX(), playerCircle.getCenterY(), lastDirection);
    }

    /**
     * Para o movimento do jogador (não faz nada no caso do círculo).
     */
    public void stop() {
        // Aqui você pode adicionar lógica se quiser implementar animações de parada.
    }

    /**
     * Retorna o círculo do jogador para que ele possa ser adicionado à cena do jogo.
     *
     * @return O objeto Circle que representa o jogador.
     */
    public Circle getPlayerCircle() {
        return playerCircle;
    }
}
