package org.satal;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MarioFactory implements EntityFactory {

    @Spawns("platform")
    public Entity newPlatform(SpawnData data){
        return FXGL.entityBuilder(data) //вот тут не так, как в инструкции
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("coin")
    public Entity newCoin(SpawnData data){
        return FXGL.entityBuilder(data) //вот тут не так, как в инструкции
                .viewWithBBox(new Circle((data.<Integer>get("width")) / 2, Color.GOLD))
                .build();
    }

//    @Override
//    public Entity apply(SpawnData data) {
//        return FXGL.entityBuilder(data) //вот тут не так, как в инструкции
//                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
//                .with(new PhysicsComponent())
//                .build();
//    }
}
