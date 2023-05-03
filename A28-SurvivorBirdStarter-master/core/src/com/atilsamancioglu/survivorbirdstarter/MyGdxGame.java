package com.atilsamancioglu.survivorbirdstarter;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MyGdxGame extends ApplicationAdapter {
    private ImageButton buttoncross;
    Color[] colors = {Color.BLUE, Color.RED, Color.ORANGE, Color.YELLOW, Color.PINK, Color.PURPLE, Color.GREEN, Color.ROYAL, Color.SALMON, Color.NAVY};
    SpriteBatch batch;
    private float elapsedTime = 0;
    ShapeRenderer shapeRenderer;
    List<Circle> circles;
    String kullan = "";
    List<String> letters;
    float GRAVITY = -5f;
    BitmapFont font;

    public int getTotalpuan() {
        return totalpuan;
    }

    public void setTotalpuan(int totalpuan) {
        this.totalpuan = totalpuan;
    }
    int puan;
    String str;
    StringBuilder sb = new StringBuilder();
    Random random;
    Stage stage;
    Circle circle;
    ArrayList<String> al = new ArrayList<String>();
    private Stage stages;
    int totalpuan=0;
    SpriteBatch batchson;
    Texture background;

    Label label2;
    Label label;

    @Override
    public void create() {
        batchson = new SpriteBatch();
        background= new Texture("background.png");
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        label= new Label("hellüü", new Label.LabelStyle(font, Color.BLACK));
        circles = new ArrayList<>();
        letters = Arrays.asList("A", "B", "C", "Ç", "D", "E", "F", "G", "Ğ", "H", "I", "İ", "J", "K", "L", "M", "N", "O", "Ö", "P", "R", "S", "Ş", "T", "U", "Ü", "V", "Y", "Z");
        random = new Random();
        // Calculate the y-coordinate for each row
        float row1Y = Gdx.graphics.getHeight() - 200;
        float row2Y = row1Y - 200;
        float row3Y = row2Y - 200;
        circles = new ArrayList<>();
        float x = 0;
        float y = 0;
        for (int i = 0; i < 24; i++) {
            float radius = 70;
            x = (float) ((i % 8 + 0.5) * 135);
            y = Gdx.graphics.getHeight() - ((i / 8) + 1) * 150 - radius;
            createCircles(x, y, radius);
        }
        createStage();
        stages = new Stage();
        Gdx.input.setInputProcessor(stages);



        Texture buttonTexture = new Texture(Gdx.files.internal("true.png"));
        TextureRegionDrawable buttonDrawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        buttoncross = new ImageButton(buttonDrawable);
        buttoncross.setPosition(900, 1770);
        buttoncross.setSize(190, 400);

        stage.addActor(buttoncross);

        buttoncross.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {


                // Butona tıklandığında yapılacak işlemler burada olacak
                System.out.println(" onayla tıklandı");
                try {
                    String kullan = wordOperations();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    FileHandle fileHandle = Gdx.files.internal("yazlab.txt");
                    BufferedReader reader = new BufferedReader(fileHandle.reader());
                    String line = null;
                    int toplampuan=0;
                    while ((line = reader.readLine()) != null) {
                        toplamhesapla(line, kullan);

                    }

                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        Texture buttonTexture2 = new Texture(Gdx.files.internal("false.png"));
        TextureRegionDrawable buttonDrawable2 = new TextureRegionDrawable(new TextureRegion(buttonTexture2));
        buttoncross = new ImageButton(buttonDrawable2);
        buttoncross.setPosition(10, 1770);
        buttoncross.setSize(190, 400);
        // Butonu stage'e ekle
        stage.addActor(buttoncross);

// Butona click listener ekle
        buttoncross.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                label.setText("");
                kullan = "";
                label2.remove();

                System.out.println("kelime silindi!");
            }
        });
        // InputMultiplexer'ı ayarlayın
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(inputMultiplexer);


    }


    private void createCircles(float x, float y, float radius) {

        Circle circle = new Circle(x, y, radius);
        circles.add(circle);


    }

    private void createStage() {
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }


    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stages.act(Gdx.graphics.getDeltaTime());
        stages.draw();
        draw();
        try {
            wordOperations();
        } catch (IOException e) {
            e.printStackTrace();
        }
        elapsedTime += Gdx.graphics.getDeltaTime();
        int i = 0;
        // 10 saniyede bir çember oluştur
        while (i < 11) {
            if (elapsedTime >= 10) {
                elapsedTime = 0;
                float x = MathUtils.random(Gdx.graphics.getWidth());
                float y = MathUtils.random(Gdx.graphics.getHeight());
                createCircles(x, y, 70);
                draw();
                try {
                    wordOperations();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            i++;
        }

    }


    @Override
    public void resize(int width, int height) {
        if (stage != null) {
            stage.getViewport().update(width, height, true);
        }
    }

    public void draw() {
        batchson.begin();
        batchson.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batchson.end();
        for (int i = 0; i < circles.size(); i++) {
            Circle circle = circles.get(i);
            circle.y += GRAVITY;

            if (circle.y - circle.radius < 0) {
                circle.y = circle.radius;
            }

            float screenWidth = 1080;
            float screenHeight = 2280;
            //CİRCLESS LİST EKRANA BASMA
            for (int j = i + 1; j < circles.size(); j++) {
                Circle circle2 = circles.get(j);
                float distance = (float) Math.sqrt(Math.pow(circle2.x - circle.x, 2) + Math.pow(circle2.y - circle.y, 2));
                float radiusSum = circle.radius + circle2.radius;
                if (distance < radiusSum) {
                    // İki dairenin merkez noktaları arasındaki mesafe, dairelerin yarıçap toplamından küçükse
                    // ikinci daireyi biraz uzaklaştır
                    float overlap = radiusSum - distance;
                    float overlapX = overlap * (circle2.x - circle.x) / distance;
                    float overlapY = overlap * (circle2.y - circle.y) / distance;
                    circle2.x += overlapX / 2;
                    circle2.y += overlapY / 2;
                    circle.x -= overlapX / 2;
                    circle.y -= overlapY / 2;

                    // Ekran sınırlarını kontrol et ve gerektiğinde düzenle
                    if (circle2.x - circle2.radius < 0) {
                        circle2.x = circle2.radius;
                    } else if (circle2.x + circle2.radius > screenWidth) {
                        circle2.x = screenWidth - circle2.radius;
                    }
                    if (circle2.y - circle2.radius < 0) {
                        circle2.y = circle2.radius;
                    } else if (circle2.y + circle2.radius > screenHeight) {
                        circle2.y = screenHeight - circle2.radius;
                    }
                    if (circle.x - circle.radius < 0) {
                        circle.x = circle.radius;
                    } else if (circle.x + circle.radius > screenWidth) {
                        circle.x = screenWidth - circle.radius;
                    }
                    if (circle.y - circle.radius < 0) {
                        circle.y = circle.radius;
                    } else if (circle.y + circle.radius > screenHeight) {
                        circle.y = screenHeight - circle.radius;
                    }
                }
            }

            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(colors[i % 8]); // renkleri sırayla döndürmek için i%8 kullanılıyor
            shapeRenderer.circle(circle.x, circle.y, circle.radius);
            shapeRenderer.end();

            batch.begin();
            font.setColor(Color.WHITE);
            font.getData().setScale(5);
            font.draw(batch, letters.get(i % 28), circle.x - 20, circle.y + 28); // harfleri sırayla döndürmek için i%28 kullanılıyor
            batch.end();


            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.VIOLET);
            shapeRenderer.rect(0, Gdx.graphics.getHeight() - 220, Gdx.graphics.getWidth(), 150);
            shapeRenderer.end();
            // Stage'in çizimi
            stage.act();
            stage.draw();
        }
    }

    public String wordOperations() throws IOException {

        ArrayList<String> kelime = new ArrayList<String>();
        ArrayList<String> kelime2 = new ArrayList<String>();
        //KELİME ALGILAMA KISMI

        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();

            // Dokunulan noktanın hangi daireye ait olduğunu belirleyin ve circles listesinden kaldırın.

            for (int i = 0; i < circles.size(); i++) {
                Circle circle = circles.get(i);

                if (circle.contains(touchX, touchY)) {

                    BitmapFont font = new BitmapFont();
                    font.getData().setScale(5);


                    kullan = kullan + letters.get(i);
                    //label= new Label(kullan, new Label.LabelStyle(font, Color.BLACK));
                    label.setText(kullan);
                    label.setPosition(stage.getWidth() - 900, stage.getHeight() - 220);
                    stage.addActor(label);

                    System.out.println(circles);
                    // circles.remove(kullan);

                    break;

                }


            }
        }
        return kullan;

    }


    public int kackeregeciyor(String a, char aranan) {
        int sayac = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == aranan) {
                sayac++;
            }
        }
        return sayac;
    }

    public void toplamhesapla(String aranan, String letter) {
        int toplam = 0;
        letter = letter.toLowerCase(Locale.ROOT);

        String a = aranan.toLowerCase(Locale.ROOT);
        int sonuc = aranan.compareTo(letter);
        if (sonuc == 0) { // simdi a eslesen ya a daki karaktrlerde charatle kac tane var
            System.out.println("eslesme ok");


            int asonuc = kackeregeciyor(a, 'a');
            int bsonuc = 3 * (kackeregeciyor(a, 'b'));
            int csonuc = 4 * (kackeregeciyor(a, 'c'));
            int çsonuc = 4 * (kackeregeciyor(a, 'ç'));
            int dsonuc = 3 * (kackeregeciyor(a, 'd'));
            int esonuc = (kackeregeciyor(a, 'e'));
            int fsonuc = 7 * (kackeregeciyor(a, 'f'));
            int gsonuc = 5 * (kackeregeciyor(a, 'g'));
            int hsonuc = 8 * (kackeregeciyor(a, 'h'));
            int ğsonuc = 5 * (kackeregeciyor(a, 'ğ'));
            int ısonuc = 2 * kackeregeciyor(a, 'ı');
            int isonuc = kackeregeciyor(a, 'i');
            int jsonuc = 10 * (kackeregeciyor(a, 'j'));
            int ksonuc = kackeregeciyor(a, 'k');
            int lsonuc = kackeregeciyor(a, 'l');
            int msonuc = 2 * (kackeregeciyor(a, 'm'));
            int nsonuc = kackeregeciyor(a, 'n');
            int osonuc = 2 * (kackeregeciyor(a, 'o'));
            int ösonuc = 7 * (kackeregeciyor(a, 'ö'));
            int psonuc = 5 * (kackeregeciyor(a, 'p'));
            int rsonuc = kackeregeciyor(a, 'r');
            int ssonuc = 2 * kackeregeciyor(a, 's');
            int şsonuc = 4 * kackeregeciyor(a, 'ş');
            int tsonuc = kackeregeciyor(a, 't');
            int usonuc = 2 * kackeregeciyor(a, 'u');
            int üsonuc = 3 * kackeregeciyor(a, 'ü');
            int vsonuc = 7 * kackeregeciyor(a, 'v');
            int ysonuc = 3 * kackeregeciyor(a, 'y');
            int zsonuc = 4 * kackeregeciyor(a, 'z');
            toplam = asonuc + bsonuc + csonuc + çsonuc + dsonuc + esonuc + fsonuc + gsonuc + ğsonuc + hsonuc + ısonuc + isonuc + jsonuc +
                    ksonuc + lsonuc + msonuc + nsonuc + osonuc + ösonuc + psonuc + rsonuc + ssonuc + şsonuc + tsonuc + usonuc +
                    üsonuc + vsonuc + ysonuc + zsonuc;

            //puanı ekrana yazdırma

            System.out.println(toplam);
            BitmapFont font = new BitmapFont();
            font.getData().setScale(5);

            int puangoster=toplam;
            str = String.valueOf(puangoster);
            label2 = new Label("PUAN:" + str, new Label.LabelStyle(font, Color.BLACK));
            label2.setPosition(stage.getWidth() - 600, stage.getHeight() - 220);
            stage.addActor(label2);


        }
    }


    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        stage.dispose();
        stages.dispose();

    }
}