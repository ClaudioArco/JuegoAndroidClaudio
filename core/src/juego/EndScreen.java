package juego;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class EndScreen extends ScreenAdapter {

    Main game;
    Sprite skin;
    Texture texture;
    int score;
    OrthographicCamera camera;
    TextureRegion backgroundTexture;

    // Constructor de la clase
    public EndScreen(Main game, int score, OrthographicCamera camera) {
        this.game = game;
        this.score = score;
        this.camera = camera;
    }

    // Metodo que se ejecuta cuando se inicia la Screen
    @Override
    public void show() {
        // Se asigna la textura del boton de volver a empezar y se indica su posicion y size
        texture = new Texture(Gdx.files.internal("Refresh.png"));
        skin = new Sprite(texture);
        skin.setPosition(Gdx.graphics.getWidth() * .60f, Gdx.graphics.getHeight() * .15f);
        skin.setSize(80, 80);
        // Se asigna la imagen de fondo
        backgroundTexture = new TextureRegion(new Texture("GameOverBackground.jpg"), 55, 0, 800, 480);
    }

    // Metodo que se ejecuta tantas veces por segundo como FPS
    @Override
    public void render(float delta) {
        // Se dibuja el fondo de la Screen, el color de la fuente, el mensaje de puntuacion final y el boton de recarga
        game.batch.begin();
        game.batch.draw(backgroundTexture, 0, 0);

        game.font.setColor(0, 0, 0, 1);

        game.font.draw(game.batch, "Tu puntuacion ha sido: " + score, Gdx.graphics.getWidth() * .16f, Gdx.graphics.getHeight() * .16f);
        skin.draw(game.batch);
        game.batch.end();
        if(Gdx.input.justTouched()){
            game.setScreen(new GameScreen(game));

        }


        // Si se pulsa la pantalla, se recogen las coordenadas y, en caso de pulsar el boton, se llama de nuevo a la Screen del juego
        /*if (Gdx.input.isTouched()) {
            Vector3 tmp = new Vector3(Gdx.input.getX(),Gdx.input.getY(), 0);
            camera.unproject(tmp);
            Rectangle textureBounds= new Rectangle(skin.getX(),skin.getY(),skin.getWidth(),skin.getHeight());
            if(textureBounds.contains(tmp.x,tmp.y)) {
                game.setScreen(new GameScreen(game));
            }
        }*/
    }

    // Metodo que detiene el proceso al esconderse la Screen
    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
