package sut.game01.core.character;


import static playn.core.PlayN.*;
import playn.core.Image;
import playn.core.*;
import playn.core.Keyboard;
import playn.core.ImageLayer;
import playn.core.ImageLayer;
import playn.core.Mouse;
import playn.core.PlayN;
import sut.game01.core.gauge.Gauge;
import tripleplay.game.Screen;
import react.UnitSlot;
import tripleplay.game.UIScreen;
import tripleplay.game.ScreenStack;
import tripleplay.ui.*;
import tripleplay.ui.layout.AxisLayout;
import static playn.core.PlayN.graphics;
import sut.game01.core.sprite.*;
import playn.core.util.*;




public class Mike   {
	private Sprite sprite;
	private int spriteIndex = 0;
	private boolean hasLoaded = false;
	private float x= 60;
	private int action = 0; 
	private int a =0;
    Gauge gauge;
	public enum State {
		IDLE, WALK, THROW, BACK
	};

	private State state = State.IDLE;

	private int e = 0;
	private int offset = 0;

	public Mike(final float x, final float y){
		PlayN.keyboard().setListener(new Keyboard.Adapter(){	
			@Override
			public void onKeyDown(Keyboard.Event event){
			if (event.key() == Key.RIGHT) {
				action = 1;
					switch (state){
						case IDLE: if(action ==1){state = State.WALK;} break;
						//case WALK: state = State.THROW; break;
						case THROW: state = State.WALK; break;
					}
				}
			else if (event.key() == Key.LEFT) {
				action = 1;
					switch (state){
						case IDLE: if(action ==1){state = State.BACK;} break;
						//case WALK: state = State.THROW; break;
						case THROW: state = State.WALK; break;
					}
				}	
			else if (event.key() == Key.SPACE) {
					switch (state){
						//case IDLE: state = State.WALK; break;
						case WALK: state = State.IDLE; break;
						case THROW: state = State.IDLE; break;
                        //Gauge g = new Gauge(10f, 10f);
					}
				}

			else if (event.key() == Key.ENTER) {
					switch (state){
						case IDLE: state = State.THROW; break;
						case WALK: state = State.THROW; break;
						//case THROW: state = State.IDLE; break;

					}

                Gauge.power(-99);
				}

				
			}

			public void onKeyUp(Keyboard.Event event){
			if (event.key() == Key.RIGHT) {
					action = 0;
					if(action ==0&& state == State.WALK){
						state = State.IDLE;
					}
					
				}
			if (event.key() == Key.LEFT) {
					action = 0;
					if(action ==0&& state == State.BACK){
						state = State.IDLE;
					}
					
				}	
			}
		});

		sprite = SpriteLoader.getSprite("images/mike2.json");
		sprite.addCallback(new Callback<Sprite>(){
			@Override
			public void onSuccess(Sprite result){
				sprite.setSprite(spriteIndex);
				sprite.layer().setOrigin(sprite.width() /2f,
										 sprite.height() /2f);
				//sprite.layer().setTranslation(300, 225);
				hasLoaded = true;
			}
			@Override
			public void onFailure(Throwable cause){
				PlayN.log().error("Error loading image!", cause);
			}

		});
		/*sprite.layer().addListener(new Pointer.Adapter(){
			@Override
			public void onPointerEnd(Pointer.Event event) {
				state = State.ATTK;
				spriteIndex =
			}
		});*/
	}
	public Layer layer() {
		return sprite.layer();
	}
	
	public void update(int delta) {
		if (hasLoaded == false) return;
		//System.out.println(action);
		e = e +delta;
		if (e > 150) {
			switch(state){
				case IDLE: offset =0; break;
				case WALK: offset =4; break;
				case BACK: offset =9; break;

				case THROW: offset =10;
							if (spriteIndex ==12) {
								state = State.IDLE;
							} break;
				
			}
			if(state == State.IDLE){
				spriteIndex = offset + ((spriteIndex +1 ) %4);
			}
			else if(state == State.WALK){
				spriteIndex = offset + ((spriteIndex +1 ) %6);
			}
			else if(state == State.BACK){
				spriteIndex = offset - (a++ %6);
			}
			else if(state == State.THROW){
				spriteIndex = offset + ((spriteIndex +1 ) %3);
			}
			sprite.setSprite(spriteIndex);
			e = 0;
		}
	//sprite.layer().setTranslation(60 , 400);
	if(state == State.WALK){
		x += 0.5f * delta /16;
		
	}
	else if
		(state == State.BACK){
		x -= 0.5f * delta /16;
		
	}
	sprite.layer().setTranslation(x , 400);
	}
	
  
  }


 
