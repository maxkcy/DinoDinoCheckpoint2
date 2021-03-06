package com.max.myfirstmpdemo.GameAssetsAndStuff;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.max.myfirstmpdemo.MyFirstMpDemoMain;

public class AsteroidBall {
    MyFirstMpDemoMain game;
    public static Animation<TextureRegion> asteroidAnimation;
    public Sprite keyframe;
    private Sprite unInitSpriteTest;


    public Vector2 position = new Vector2(); //null position is (0,0) by default
    Vector2 previousPosition = new Vector2();
    public void setPosition(float x, float y) {
        position.x = x;
        position.y = y;
    }

    public AsteroidBall(MyFirstMpDemoMain game) {
        this.game = game;
        //asteroidAnimation = new Animation<TextureRegion>(1/5f, game.splashScreen.gameAssets.asteroidTextureAtlas.findRegions("asteroid"));
        asteroidAnimation = new Animation<TextureRegion>(1/25f, game.splashScreen.gameAssets.asteroidNewAtlas.findRegions("a10"));
        asteroidAnimation.setPlayMode(Animation.PlayMode.LOOP);
        keyframe = new Sprite(game.splashScreen.gameAssets.asteroidTextureAtlas.createSprites().get(0));
        keyframe.setSize(42, 42);
        keyframe.setFlip(true, false);
        unInitSpriteTest = keyframe;
    }

    public float stateTime = 0;

    float angle = 0;
    boolean isMoving = false;
    boolean flipflip = false;

    public void update(float delta){
        // -->this to be done later along with rotation asteroidAnimation.setFrameDuration();
        keyframe.setRegion(asteroidAnimation.getKeyFrame(stateTime));
        keyframe.setPosition(position.x - 5, position.y - 5);
        if(isMoving){ //true will be false when state on server side is static
        stateTime += delta;}
        //unInitSpriteTest.setBounds(10, 10, 32, 32);
        //unInitSpriteTest.draw(game.getBatch());

         if(previousPosition.x != position.x || previousPosition.y != position.y){
         angle = MathUtils.atan2(position.y - previousPosition.y, position.x - previousPosition.x) * MathUtils.radiansToDegrees;
         angle = (((360 % angle) + 360) % 360);
         keyframe.setRotation(angle);
         isMoving = true;
         if (position.x  < previousPosition.x){
             flipflip = true;
         } else {flipflip = false;}
        }else {isMoving = false;}
        keyframe.setFlip(flipflip, false);
        keyframe.draw(game.getBatch());

        previousPosition.x = position.x;
        previousPosition.y = position.y;
    }

}
