package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    private TextView storeResult;

    public int getAllChildViewCount(View view) {
        //初步考虑采用递归深度搜索的方式实现
        int totalView = 0;

        if (null == view) {
            return 0;
        }

        if (view instanceof ViewGroup) {
            totalView++;
            int nums = ((ViewGroup) view).getChildCount();
            for (int i = 0; i < nums; i++) {
                View viewChild = ((ViewGroup) view).getChildAt(i);
                if (viewChild instanceof ViewGroup) {
                    totalView += getAllChildViewCount(viewChild);
                } else {
                    //不是ViewGroup实例单独的view也算
                    totalView++;
                }
            }
        }

        return totalView;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise2);
        storeResult = findViewById(R.id.textView2);
        int ViewNum = getAllChildViewCount(this.getWindow().getDecorView());
        storeResult.setText("总View数目为:"+ViewNum);

    }


}
