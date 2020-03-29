package chapter.android.aweme.ss.com.homework;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;
import chapter.android.aweme.ss.com.homework.R;

/**
 * @author 王兴宇
 */
public class ChatRoomActivity extends AppCompatActivity {

    private TextView tvWithName;
    private TextView tvInfo;
    private TextView tvContentInfo;
    private EditText edSay;
    private ImageView btnSendInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        tvWithName=findViewById(R.id.tv_with_name);
        //tvInfo=findViewById(R.id.tv_info);
        tvContentInfo=findViewById(R.id.tv_content_info);
        edSay=findViewById(R.id.ed_say);
        btnSendInfo=findViewById(R.id.btn_send_info);

        //获取数据
        Bundle bundle=getIntent().getBundleExtra("data");

        int id=bundle.getInt("id");
        final String title=bundle.getString("title");
        final String description=bundle.getString("description");
        final String time=bundle.getString("time");
        final Boolean isOfficial =bundle.getBoolean("isOfficial");
        Log.d("WXY","Item ID:"+id+",Received:"+"Message{" +
                "isOfficial=" + isOfficial +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +
                '}');
        tvWithName.setText("我和"+title+"的对话");
        //聊天室序号从1开始
        id++;
        tvInfo.setText("基本信息：\n"+"这是列表中的第"+id+"个聊天\n"+"描述："+description+"\n时间："+time);
        if (isOfficial){
            tvInfo.append("\n是否为官方：是");
        }
        else {
            tvInfo.append("\n是否为官方：否");
        }
        tvInfo.append("\n---------------------------");
        //设置发送点击事件
        btnSendInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvContentInfo.append("我："+edSay.getText()+"\n");
                Random random = new Random();
                switch (random.nextInt(6)){
                    case 0:
                        tvContentInfo.append(title+"："+edSay.getText()+"\n");
                        break;
                    case 1:
                        tvContentInfo.append(title+"：李时珍的皮\n");
                        break;
                    case 2:
                        tvContentInfo.append(title+"：你这神魔恋？\n");
                        break;
                    case 3:
                        tvContentInfo.append(title+"：这波是肉蛋葱鸡\n");
                        break;
                    case 4:
                        tvContentInfo.append(title+"：起飞！！！\n");
                        break;
                    case 5:
                        tvContentInfo.append(title+"：捞的躺口水啊\n");
                        break;
                    default:
                        break;
                }
                edSay.getText().clear();
            }
        });
    }
}