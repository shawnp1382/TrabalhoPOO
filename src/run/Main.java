package run;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import player.Player;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;
import projectile.Projectile;

/**
 * A classe Main é a entrada principal do aplicativo JavaFX.
 * Ela inicializa a cena do jogo, cria o jogador e gerencia a lógica do jogo,
 * incluindo a movimentação do jogador e o disparo de projéteis.
 */
public class Main extends Application {

    // Instância do jogador.
    private Player player;

    // Lista de projéteis ativos no jogo.
    private ArrayList<Projectile> projectiles = new ArrayList<>();

    // Conjunto para rastrear teclas pressionadas.
    private HashSet<KeyCode> activeKeys = new HashSet<>();

    // Controle de disparo para impedir disparos contínuos.
    private boolean shooting = false;

    @Override
    public void start(Stage primaryStage) {
        // Cria o contêiner principal para os elementos do jogo.
        Pane root = new Pane();

        // Inicializa o jogador na posição (100, 100) com raio de 15.
        player = new Player(100, 100, 15); // O raio é 15 para um círculo maior.

        // Adiciona o jogador ao contêiner root.
        root.getChildren().add(player.getPlayerCircle());

        // Cria a cena do jogo com dimensões 800x600.
        Scene scene = new Scene(root, 800, 600);

        // Configura eventos de teclado para rastrear teclas pressionadas e liberadas.
        scene.setOnKeyPressed(event -> activeKeys.add(event.getCode()));
        scene.setOnKeyReleased(event -> {
            activeKeys.remove(event.getCode());
            // Redefine o controle de disparo quando a tecla T é solta.
            if (event.getCode() == KeyCode.T) {
                shooting = false;
            }
        });

        // Cria um Timer para atualizar o estado do jogo a cada frame.
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Movimentação do jogador com base nas teclas pressionadas.
                if (activeKeys.contains(KeyCode.W)) {
                    player.moveUp();
                } else if (activeKeys.contains(KeyCode.S)) {
                    player.moveDown();
                } else if (activeKeys.contains(KeyCode.A)) {
                    player.moveLeft();
                } else if (activeKeys.contains(KeyCode.D)) {
                    player.moveRight();
                } else {
                    player.stop();  // Retorna à imagem de idle se nenhuma tecla de movimento for pressionada.
                }

                // Verifica se a tecla de disparo (T) foi pressionada.
                if (activeKeys.contains(KeyCode.T) && !shooting) {
                    // Dispara um projétil na direção atual do jogador.
                    Projectile projectile = player.shoot();
                    projectiles.add(projectile);  // Adiciona o projétil à lista de projéteis.
                    root.getChildren().add(projectile.getProjectileShape());  // Adiciona o projétil à cena.
                    shooting = true;  // Impede disparos contínuos enquanto a tecla está pressionada.
                }

                // Atualiza todos os projéteis.
                Iterator<Projectile> iterator = projectiles.iterator();
                while (iterator.hasNext()) {
                    Projectile projectile = iterator.next();
                    projectile.update();  // Atualiza a posição do projétil.

                    // Remove o projétil se sair da tela.
                    if (projectile.isOutOfBounds(scene.getWidth(), scene.getHeight())) {
                        root.getChildren().remove(projectile.getProjectileShape());
                        iterator.remove();  // Remove o projétil da lista.
                    }
                }
            }
        };

        // Inicia o loop do jogo.
        gameLoop.start();

        // Configura o título da janela e exibe a cena.
        primaryStage.setTitle("Mini Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);  // Inicia a aplicação JavaFX.
    }
}
