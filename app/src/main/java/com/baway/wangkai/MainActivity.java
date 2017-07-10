package com.baway.wangkai;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

/**
 * 操作显示主页面的类
 * 王锴
 * 2017年7月10日08:51:52
 */
public class MainActivity extends Activity {

    private AmountView amountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountView = (AmountView) findViewById(R.id.amount_view);
        //设置库存
        amountView.setGoods_storge(50);
        //设置监听事件
        amountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
            @Override
            public void onAmountChange(View view, int amount) {
                Toast.makeText(MainActivity.this, "Amount=>" + amount, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
