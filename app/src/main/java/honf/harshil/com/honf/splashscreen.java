package honf.harshil.com.honf;

import android.app.ActivityOptions;
import android.content.Intent;
//import android.support.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.WindowManager;

public class splashscreen extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(splashscreen.this);
//        ActionBar actionBar=getSupportActionBar();
//        assert actionBar != null;
//        actionBar.hide();
//        final Transition exitTrans=new Slide();

//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        this.setContentView(R.layout.splash);
        Thread timerThread=new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent=new Intent(splashscreen.this,MainActivity.class);
                    startActivity(intent,options.toBundle());
//                    getWindow().setExitTransition(exitTrans);
                    overridePendingTransition(R.anim.fadein,R.anim.fade);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}
