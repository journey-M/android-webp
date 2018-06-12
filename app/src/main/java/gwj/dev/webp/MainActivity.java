package gwj.dev.webp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.rastermill.FrameSequence;
import android.support.rastermill.FrameSequenceDrawable;
import android.support.rastermill.samples.FrameSequenceTest;
import android.support.rastermill.samples.SamplesList;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageView drawableView;
    private Button btnGoogle;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btn_start);
        btnGoogle = findViewById(R.id.btn_google_demo);
        drawableView = findViewById(R.id.text_img);

        btnStart.setOnClickListener(this);
        btnGoogle.setOnClickListener(this);

    }


    FrameSequenceDrawable mDrawable ;
    FrameSequenceDrawable.BitmapProvider mProvider = new FrameSequenceDrawable.BitmapProvider() {
        private List<Bitmap> mBitmaps = new ArrayList<>();
        @Override
        public Bitmap acquireBitmap(int minWidth, int minHeight) {
            Bitmap bitmap = Bitmap.createBitmap(minWidth, minHeight, Bitmap.Config.ARGB_8888);
            mBitmaps.add(bitmap);
            return bitmap;
        }

        @Override
        public void releaseBitmap(Bitmap bitmap) {
            if (!mBitmaps.contains(bitmap)) throw new IllegalStateException();
            mBitmaps.remove(bitmap);
            bitmap.recycle();
        }
    };


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_google_demo:
                Intent intent = new Intent(MainActivity.this, SamplesList.class);
                startActivity(intent);
                break;
            case R.id.btn_start:
                startWebpAnimation();
                break;
        }
    }


    private void startWebpAnimation(){
        int mResourceId = R.raw.zan;
        InputStream is = getResources().openRawResource(mResourceId);
        FrameSequence fs = FrameSequence.decodeStream(is);
        mDrawable = new FrameSequenceDrawable(fs, mProvider);
        mDrawable.setOnFinishedListener(new FrameSequenceDrawable.OnFinishedListener() {
            @Override
            public void onFinished(FrameSequenceDrawable drawable) {
                Toast.makeText(getApplicationContext(),
                        "The animation has finished", Toast.LENGTH_SHORT).show();
            }
        });
        drawableView.setBackgroundDrawable(mDrawable);
        mDrawable.setLoopCount(3);
        mDrawable.setLoopBehavior(FrameSequenceDrawable.LOOP_FINITE);
        mDrawable.start();
    }

}
