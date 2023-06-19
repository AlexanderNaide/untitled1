package org.satal;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.sun.prism.PhongMaterial;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MarioAnimationApp extends GameApplication {
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(15 * 70);
        gameSettings.setHeight(10 * 70);
        gameSettings.setTitle("MarioHertz");
        gameSettings.setVersion("0.1");
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initGame() {
        FXGL.getGameWorld().addEntityFactory(new MarioFactory());
        FXGL.setLevelFromMap("mario.tmx");
    }
}
