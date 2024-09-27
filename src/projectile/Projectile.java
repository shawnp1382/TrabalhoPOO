package projectile;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A classe Projectile representa um projétil que é disparado pelo jogador
 * no mini-game. Cada projétil possui uma direção e uma velocidade, e
 * seu movimento é atualizado a cada frame.
 */
public class Projectile {

    // A forma visual do projétil, representada por um círculo.
    private Circle projectileShape;

    // Velocidade de movimento do projétil.
    private double speed = 7.0;

    // Direção do projétil em graus. Exemplo:
    // 0 = direita, 90 = baixo, 180 = esquerda, 270 = cima.
    private double direction;

    /**
     * Construtor da classe Projectile.
     *
     * @param x Posição inicial X do projétil.
     * @param y Posição inicial Y do projétil.
     * @param direction A direção do projétil em graus (0 = direita, 90 = baixo, etc.).
     */
    public Projectile(double x, double y, double direction) {
        this.direction = direction;

        // Cria a forma do projétil como um círculo com raio 5.
        projectileShape = new Circle(x, y, 5);

        // Define a cor do projétil como azul.
        projectileShape.setFill(Color.BLUE);
    }

    /**
     * Atualiza a posição do projétil com base na direção e na velocidade.
     * O movimento é calculado utilizando funções trigonométricas (cos e sin)
     * para mover o projétil na direção correta.
     */
    public void update() {
        // Converte a direção em radianos (necessário para usar cos e sin).
        double angleRad = Math.toRadians(direction);

        // Atualiza a posição X e Y do projétil com base na direção e velocidade.
        projectileShape.setCenterX(projectileShape.getCenterX() + Math.cos(angleRad) * speed);
        projectileShape.setCenterY(projectileShape.getCenterY() + Math.sin(angleRad) * speed);
    }

    /**
     * Verifica se o projétil saiu dos limites da tela.
     *
     * @param sceneWidth Largura da cena/janela do jogo.
     * @param sceneHeight Altura da cena/janela do jogo.
     * @return true se o projétil estiver fora da tela, false caso contrário.
     */
    public boolean isOutOfBounds(double sceneWidth, double sceneHeight) {
        double x = projectileShape.getCenterX();
        double y = projectileShape.getCenterY();

        // Retorna true se o projétil estiver fora da tela, caso contrário, false.
        return x < 0 || x > sceneWidth || y < 0 || y > sceneHeight;
    }

    /**
     * Retorna a forma visual do projétil para ser adicionada à cena do jogo.
     *
     * @return O objeto Circle que representa o projétil.
     */
    public Circle getProjectileShape() {
        return projectileShape;
    }
}
