package org.satal;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import javafx.scene.input.KeyCode;

public class SpriteSheetAnimationApp  extends GameApplication {

    private Entity player;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(400);
        gameSettings.setHeight(240);
        gameSettings.setTitle("Beeee");
        gameSettings.setVersion("0.1");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initGame() {
        player = FXGL.entityBuilder()
                .at(200, 200)
                .with(new SpriteSheetAnimationComponent())
                .buildAndAttach();
    }

    @Override
    protected void initInput() {
        FXGL.onKey(KeyCode.RIGHT, () -> {
            player.getComponent(SpriteSheetAnimationComponent.class).moveRight();
        });

        FXGL.onKey(KeyCode.LEFT, () -> {
            player.getComponent(SpriteSheetAnimationComponent.class).moveLeft();
        });
    }
}

