package com.bwie.test.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.test.R;

/**
 * Author:杨帅
 * Date:2019/6/15 16:27
 * Description：
 */
public class AddMinus extends LinearLayout {
    /*@BindView(R.id.minus)
    Button minusBtn;
    @BindView(R.id.add)
    Button addBtn;
    @BindView(R.id.num)
    TextView numTv;*/
    private int number = 1;
    private Button addBtn;
    private Button minusBtn;
    private TextView numTv;

    public AddMinus(Context context) {
        this(context, null);
    }

    public AddMinus(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddMinus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.view_mydesign, this);
        addBtn = findViewById(R.id.add);
        minusBtn = findViewById(R.id.minus);
        numTv = findViewById(R.id.num);
        numTv.setText("" + number);
        /*minusBtn.setOnClickListener(this::onClick);
        addBtn.setOnClickListener(this::onClick);*/
        minusBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number > 1) {
                    number--;
                    changeNum(number);
                }
                dataCallBack.getData(numTv.getText().toString().trim());
            }
        });
        addBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                changeNum(number);
                dataCallBack.getData(numTv.getText().toString().trim());
            }
        });
    }
    /**
     * 改变数据
     *
     * @param num
     */
    private void changeNum(int num) {
        numTv.setText(""+num);
    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                break;
            case R.id.minus:
                break;
        }
    }*/
    public void setNumber(String number){
        numTv.setText(number);
    }
    public interface DataCallBack{
        void getData(String trim);
    }
    private DataCallBack dataCallBack;

    public void setDataCallBack(DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }
}
