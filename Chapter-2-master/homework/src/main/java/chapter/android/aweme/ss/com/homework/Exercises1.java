package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import static android.arch.lifecycle.Lifecycle.Event.ON_DESTROY;
import static android.arch.lifecycle.Lifecycle.Event.ON_STOP;

/**
 * 作业1：
 * Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来
 * 但我们的 mLifecycleDisplay 由于生命周期的原因(Tips:执行#onStop()之后，UI界面我们是看不到的)并没有展示
 * 在原有@see Exercises1 基础上如何补全它，让其跟logcat的展示一样?
 * <p>
 * Tips：思考用比Activity的生命周期要长的来存储？  （比如：application、static变量）
 */

//修改方式:增加一个static变量为字符串ondestroy,平时为空,如果执行了nDestroy(),会变为字符串OnDestroy,并保存下来
public class Exercises1 extends AppCompatActivity {

    private static final String TAG = "hj";
    private static String destroy = "";

    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";
    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";


    TextView tempview;


    private void logAndAppend(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);
        tempview.append(lifecycleEvent + "\n");
    }

    public void resetLifecycleDisplay(View view) {
        tempview.setText("Lifecycle callbacks:\n");
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise1);
        tempview = findViewById(R.id.textView);
        if (savedInstanceState != null) {
            logAndAppend("savedInstanceState != null");
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                String savedContent = (String) savedInstanceState.get(LIFECYCLE_CALLBACKS_TEXT_KEY);
                tempview.setText(savedContent);
            }
        }
        logAndAppend(ON_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        logAndAppend(ON_STOP);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroy = "OnDestroy\n";
        logAndAppend(ON_DESTROY);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String content = tempview.getText().toString();//当前已有的log 提取出来
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY, content+destroy); //把内容存储起来
        //destroy = ""
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logAndAppend(ON_RESTART);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logAndAppend(ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        logAndAppend(ON_RESUME);
    }


    @Override
    protected void onPause() {
        super.onPause();
        logAndAppend(ON_PAUSE);
    }

}
