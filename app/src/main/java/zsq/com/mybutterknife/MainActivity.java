package zsq.com.mybutterknife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import zsq.com.mybutterknife.have_a_test.ButterKnife;
import zsq.com.mybutterknife.have_a_test.FindViewByID;
import zsq.com.mybutterknife.have_a_test.OnClick;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.test_btn)
    void test(){
        test_tv.setText("恭喜您，绑定成功了！");
    }

    @FindViewByID(R.id.test_tv)
    TextView test_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bindView(this);
    }
}
