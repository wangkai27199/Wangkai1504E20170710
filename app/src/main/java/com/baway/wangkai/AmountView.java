package com.baway.wangkai;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by DELL on 2017/7/10.
 */

public class AmountView  extends LinearLayout implements View.OnClickListener, TextWatcher {

    private static final String TAG = "AmountView";
    private int amount = 1;//购买数量
    private int goods_storge = 1;//库存

    private OnAmountChangeListener mListener;

    private EditText etAmount;
    private Button btnDecrease;//减号
    private Button btnIncrease;//加号


    public AmountView (Context context) {
        this(context,null);
    }

    public AmountView (Context context, AttributeSet attrs) {
        super(context, attrs);
        //加载布局  拿控件
        LayoutInflater.from(context).inflate(R.layout.view_amount,this);
        etAmount = (EditText) findViewById(R.id.edAmount);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        //设置按钮监听事件 输入框Text改变监听
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs,R.styleable.AmountView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnWidth, LayoutParams.WRAP_CONTENT);
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvWidth, 80);
        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvTextSize, 0);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnTextSize, 0);
        obtainStyledAttributes.recycle();

        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnDecrease.setLayoutParams(btnParams);
        btnIncrease.setLayoutParams(btnParams);
        if (btnTextSize != 0){
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX,btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX,btnTextSize);
        }
        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        etAmount.setLayoutParams(textParams);
        if (tvTextSize != 0){
            etAmount.setTextSize(tvTextSize);
        }
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener){
        this.mListener = onAmountChangeListener;
    }
    public void setGoods_storge(int goods_storge){
        this.goods_storge = goods_storge;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease){
            if (amount > 1){
                amount--;
                etAmount.setText(amount+"");
            }
        } else if (i == R.id.btnIncrease){
            if (amount < goods_storge){
                amount++;
                etAmount.setText(amount+"");
            }
        }
        etAmount.clearFocus();
        if (mListener != null){
            mListener.onAmountChange(this,amount);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty())
            return;
        amount = Integer.valueOf(s.toString());
        if (amount > goods_storge){
            etAmount.setText(goods_storge+"");
            return;
        }

        if (mListener != null){
            mListener.onAmountChange(this,amount);
        }


    }
    //自定义一个接口
    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }
}
