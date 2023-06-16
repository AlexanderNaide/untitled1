package org.satal;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.handlers.CollectibleHandler;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class BasicGameApp extends GameApplication {

    public enum EntityType{
        PLAYER, COIN
    }

    private Entity player;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings gameSettings) {  //создание окна
        gameSettings.setWidth(600);
        gameSettings.setHeight(600);
        gameSettings.setTitle("Beeee");
        gameSettings.setVersion("0.1");
    }

    @Override
    protected void initGame() {  // создание игровых сущностей
        player = FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at(300, 300)
//                .view(new Rectangle(25, 25, Color.BLUE)) // можно взять любой FX объект
//                .view("brick.png") // Файлы текстуры по умолчанию лежат в .../resources/assets/textures/
                .viewWithBBox("brick.png")  // так мы создаем ограничивающую рамку в игровом поле, которая рисуется вокруг переданного обьекта
                .with(new CollidableComponent(true))  // сообщаем, что объект учавствует в обнаружении и обработки событий
                .buildAndAttach();

        FXGL.entityBuilder()
                .type(EntityType.COIN)
                .at(500, 200)
                .viewWithBBox(new Circle(15, 15, 15, Color.YELLOW))
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(new CollisionHandler(EntityType.PLAYER, EntityType.COIN) {
            @Override
            protected void onCollisionBegin(@NotNull Entity player, @NotNull Entity coin) {
                coin.removeFromWorld();
            }
        });
    }

    @Override
    protected void initInput() {  //создание элементов управления

        //Развернутое создание
        Input input = FXGL.getInput();
        input.addAction(new UserAction("Move right") {
            @Override
            protected void onAction() {
                player.translateX(5);
                FXGL.inc("pixelsMoved", 5);
            }
        }, KeyCode.D);

        //Сокращенное создание
        FXGL.onKey(KeyCode.W, () -> {
            player.translateY(-5);
            FXGL.inc("pixelsMoved", 5);
        });

        FXGL.onKey(KeyCode.A, () -> {
            player.translateX(-5);
            FXGL.inc("pixelsMoved", 5);
        });

        FXGL.onKey(KeyCode.S, () -> {
            player.translateY(5);
            FXGL.inc("pixelsMoved", 5);
        });

//        FXGL.onKeyDown(KeyCode.F, () -> {  // событие при нажатии F воспроизводится звук (поместить в .../resources/assets/textures/...wav
//            FXGL.play("drop.wav");
//        });

    }


    @Override
    protected void initUI() {  // создание элементов пользовательского интерфейса
        Text textPixels = new Text();
        textPixels.setTranslateX(50);
        textPixels.setTranslateY(100);
        FXGL.getGameScene().addUINode(textPixels);
        textPixels.textProperty().bind(FXGL.getWorldProperties().intProperty("pixelsMoved").asString());
        var brickTexture = FXGL.getAssetLoader().loadTexture("brick.png");
        brickTexture.setTranslateX(50);
        brickTexture.setTranslateY(450);
        FXGL.getGameScene().addUINode(brickTexture);

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("pixelsMoved", 0);
    }
}