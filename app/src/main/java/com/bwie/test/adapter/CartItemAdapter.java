package com.bwie.test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.test.R;
import com.bwie.test.entity.ProductEntity;
import com.bwie.test.view.AddMinus;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Author:杨帅
 * Date:2019/6/15 10:28
 * Description：商品条目适配器
 */
public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartItemHolder> {
    private Context context;
    private List<ProductEntity.Goods> shoppingCartList;

    public CartItemAdapter(Context context, List<ProductEntity.Goods> shoppingCartList) {
        this.context = context;
        this.shoppingCartList = shoppingCartList;
    }

    public List<ProductEntity.Goods> getShoppingCartList() {
        return shoppingCartList;
    }

    @Override
    public CartItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new CartItemHolder(inflater.inflate(R.layout.holder_cart_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemHolder cartItemHolder, int i) {
        ProductEntity.Goods goods = shoppingCartList.get(i);
        cartItemHolder.nameTv.setText(goods.commodityName);
        cartItemHolder.priceTv.setText("￥" + goods.price);
        cartItemHolder.itemCheck.setChecked(goods.itemFlag);//给选中状态设置属性
        cartItemHolder.itemCheck.setOnClickListener(new View.OnClickListener() {//点击改变选中状态属性监听
            @Override
            public void onClick(View v) {
                if (cartItemHolder.itemCheck.isChecked()) {
                    goods.itemFlag = true;
                } else {
                    goods.itemFlag = false;
                }
                EventBus.getDefault().postSticky("count");//点击复选框发送事件
            }
        });
        if (goods.proNum != null) { //设置初始数目
            cartItemHolder.addMinus.setNumber(goods.proNum);
        }
        //自定义控件回调数据
        cartItemHolder.addMinus.setDataCallBack(new AddMinus.DataCallBack() {
            @Override
            public void getData(String trim) {
                goods.proNum = trim;
                EventBus.getDefault().postSticky("count");
            }
        });
        Glide.with(context).load(goods.pic).into(cartItemHolder.imgItem);
    }

    @Override
    public int getItemCount() {
        return shoppingCartList.size();

    }

    public class CartItemHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_img)
        ImageView imgItem;
        @BindView(R.id.item_name)
        TextView nameTv;
        @BindView(R.id.item_price)
        TextView priceTv;
        @BindView(R.id.check)
        CheckBox itemCheck;
        @BindView(R.id.addminus)
        AddMinus addMinus;

        public CartItemHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);//绑定Butterknife
        }
        /*public void unBind(){
            if(bind!=null){
                bind.unbind();
                bind = null;
            }
        }*/
    }
}
