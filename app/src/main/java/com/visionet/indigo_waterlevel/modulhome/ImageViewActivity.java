package com.visionet.indigo_waterlevel.modulhome;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.visionet.indigo_waterlevel.R;

import uk.co.senab.photoview.PhotoViewAttacher;
import static android.R.attr.uiOptions;

public class ImageViewActivity extends AppCompatActivity {

    ImageView image;
    TextView tv_description, tv_location;
    ScrollView scrollView;
    PhotoViewAttacher photoView;
    String imageUrl;
    String reservoirName = "";
    String reservoirLocation = "";
    String reservoirDescription = "";

    ActionBar ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }

        ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000000")));


        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        scrollView = (ScrollView) findViewById(R.id.scrollView);
        tv_location = (TextView) findViewById(R.id.tv_location);
        tv_description = (TextView) findViewById(R.id.tv_description);
        image = (ImageView) findViewById(R.id.image);
        imageUrl = extras.getString("imageUrl");
        if (!imageUrl.isEmpty()) {

            Glide.with(ImageViewActivity.this)
                    .load(imageUrl)
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(image);

            photoView = new PhotoViewAttacher(image);
            photoView.update();

        }else{
            Glide.with(ImageViewActivity.this)
                    .load(ContextCompat.getDrawable(ImageViewActivity.this, R.drawable.ic_reservoir))
                    .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(image);

            photoView = new PhotoViewAttacher(image);
            photoView.update();
        }

        if(!extras.getString("reservoirName").isEmpty()){
            reservoirName = extras.getString("reservoirName");
            ab.setTitle(reservoirName);
        }

        if(!extras.getString("reservoirLocation").isEmpty()){
            reservoirLocation = extras.getString("reservoirLocation");
            //ab.setSubtitle(reservoirLocation);
            tv_location.setText(reservoirLocation);
        }

        if(!extras.getString("reservoirDescription").isEmpty()){
            reservoirDescription = extras.getString("reservoirDescription");
            tv_description.setText(reservoirDescription);
        }

        photoView.setOnDoubleTapListener(new GestureDetector.OnDoubleTapListener() {
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if(scrollView.getVisibility() == View.GONE){
                    scrollView.setVisibility(View.VISIBLE);
                }else{
                    scrollView.setVisibility(View.GONE);
                }

                return false;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onDoubleTapEvent(MotionEvent e) {
                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_image_view, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {

            this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));
            this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fullScreen() {

        // BEGIN_INCLUDE (get_current_ui_flags)
        // The UI options currently enabled are represented by a bitfield.
        // getSystemUiVisibility() gives us that bitfield.
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        int newUiOptions = uiOptions;
        // END_INCLUDE (get_current_ui_flags)
        // BEGIN_INCLUDE (toggle_ui_flags)
        boolean isImmersiveModeEnabled = isImmersiveModeEnabled();
        if (isImmersiveModeEnabled) {
            Log.i("TEST", "Turning immersive mode mode off. ");
            ab.show();
        } else {
            Log.i("TEST", "Turning immersive mode mode on.");
            ab.hide();
        }

        // Navigation bar hiding:  Backwards compatible to ICS.
        if (Build.VERSION.SDK_INT >= 14) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        }

        // Status bar hiding: Backwards compatible to Jellybean
        if (Build.VERSION.SDK_INT >= 16) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_FULLSCREEN;
        }

        // Immersive mode: Backward compatible to KitKat.
        // Note that this flag doesn't do anything by itself, it only augments the behavior
        // of HIDE_NAVIGATION and FLAG_FULLSCREEN.  For the purposes of this sample
        // all three flags are being toggled together.
        // Note that there are two immersive mode UI flags, one of which is referred to as "sticky".
        // Sticky immersive mode differs in that it makes the navigation and status bars
        // semi-transparent, and the UI flag does not get cleared when the user interacts with
        // the screen.
        if (Build.VERSION.SDK_INT >= 18) {
            newUiOptions ^= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        }

        getWindow().getDecorView().setSystemUiVisibility(newUiOptions);
        //END_INCLUDE (set_ui_flags)
    }

    private boolean isImmersiveModeEnabled() {
        return ((uiOptions | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY) == uiOptions);
    }
}
