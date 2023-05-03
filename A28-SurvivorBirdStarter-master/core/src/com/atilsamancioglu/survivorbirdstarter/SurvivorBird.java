package com.atilsamancioglu.survivorbirdstarter;



import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Paint;

public class SurvivorBird extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture glowCircle;
	Texture circle1;
	Texture circle2;
	Texture circle3;
	Texture circle4;
	Texture circle5;
	Texture circle6;
	Texture circle7;
	Texture circle8;

	float circlex=0;
	float circley=0;
	int gameState=0;
	float velocity=0;
	float circley2=0;
	float velocity2=0;

	float circley3=0;
	float circley4=0;




	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("city.png");
		glowCircle = new Texture("glowCircle.png");

		circley2=Gdx.graphics.getHeight();
		circley3=Gdx.graphics.getHeight();
		circley4=Gdx.graphics.getHeight();

		circle1=new Texture("glowCircle.png");
		circle2=new Texture("glowCircle.png");
		circle3=new Texture("glowCircle.png");
		circle4=new Texture("glowCircle.png");
		circle5=new Texture("glowCircle.png");
		circle6=new Texture("glowCircle.png");
		circle7=new Texture("glowCircle.png");
		circle8=new Texture("glowCircle.png");

		circlex = Gdx.graphics.getWidth()/3;
		circley = Gdx.graphics.getHeight()/3;

	}



	@Override
	public void render () {
		batch.begin();
		batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


		if(gameState==1){

			if(circley2>0 || velocity<0){//gelecek ilk 3 satırın zeminde kalmasını saglıyor,yer cekimi olusturuyor.
				velocity2++;
				circley2 =circley2-velocity2;
			}

			if(circley3>180 || velocity<0){//gelecek ilk 3 satırın üst üste kalmasını saglıyor,yer cekimi olusturuyor.
				velocity2++;
				circley3 =circley3-velocity2;
			}
			if(circley4>360 || velocity<0){//gelecek ilk 3 satırın üst üste kalmasını saglıyor,yer cekimi olusturuyor.
				velocity2++;
				circley4 =circley4-velocity2;
			}


			/*if(circley>0 || velocity<0){
				velocity++;
				circley=circley-velocity;//tek cemberin yere düşmesini sagıyor,yer cekimi olusturuyor.
			}*/
		}
		else{

			if(Gdx.input.justTouched()){
				gameState=1;//oyun basladıgında 1 olur
			}
		}





		//batch.draw(glowCircle,circlex,circley,Gdx.graphics.getWidth()/5,Gdx.graphics.getHeight()/8);

		//ilk satırdaki halkalar için.
		batch.draw(circle1,0,circley2,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle2,135,circley2,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle3,270,circley2,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle4,3*135,circley2,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle5,4*135,circley2,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle6,5*135,circley2,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle7,6*135,circley2,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle8,7*135,circley2,Gdx.graphics.getWidth()/7,Gdx.graphics.getWidth()/6);

		//2.satırı
		batch.draw(circle1,0,circley3,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle2,135,circley3,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle3,270,circley3,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle4,3*135,circley3,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle5,4*135,circley3,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle6,5*135,circley3,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle7,6*135,circley3,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle8,7*135,circley3,Gdx.graphics.getWidth()/7,Gdx.graphics.getWidth()/6);

		//3.satırı
		batch.draw(circle1,0,circley4,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle2,135,circley4,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle3,270,circley4,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle4,3*135,circley4,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle5,4*135,circley4,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle6,5*135,circley4,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle7,6*135,circley4,Gdx.graphics.getWidth()/6,Gdx.graphics.getWidth()/6);
		batch.draw(circle8,7*135,circley4,Gdx.graphics.getWidth()/7,Gdx.graphics.getWidth()/6);


		batch.end();

	}
	
	@Override
	public void dispose () {

	}
}


