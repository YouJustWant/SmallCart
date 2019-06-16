package com.bwie.test.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.bwie.test.R;
import com.bwie.test.entity.ProductEntity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.support.constraint.Constraints.TAG;

/**
 * Author:杨帅
 * Date:2019/6/15 10:18
 * Description：
 */
public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.CartHolder> {
    private Context context;
    private List<ProductEntity.Product> result;
    private boolean flag;
    private CartItemAdapter cartItemAdapter;

    public CartViewAdapter(Context context, List<ProductEntity.Product> result) {
        this.context = context;
        this.result = result;
    }
    public void changeFlag(boolean flag) {
        this.flag = flag;
        notifyDataSetChanged();
    }
    public List<ProductEntity.Product> getResult(){
        return result;
    }

    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new CartHolder(inflater.inflate(R.layout.holder_cart,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder cartHolder, int i) {
        ProductEntity.Product product = result.get(i);
        List<ProductEntity.Goods> shoppingCartList = product.shoppingCartList;
        cartHolder.tvTitle.setText(product.categoryName);
        cartHolder.cartView.setLayoutManager(new LinearLayoutManager(context));
        cartHolder.categoryCheck.setChecked(flag);
        cartItemAdapter = new CartItemAdapter(context, shoppingCartList);
        cartHolder.categoryCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ProductEntity.Goods goods : cartItemAdapter.getShoppingCartList()) {
                    boolean checked = cartHolder.categoryCheck.isChecked();
                    goods.itemFlag = checked;
                }
                cartItemAdapter.notifyDataSetChanged();
                EventBus.getDefault().postSticky("count");
            }
        });
        cartHolder.cartView.setAdapter(cartItemAdapter);
        Log.d(TAG, "onBindViewHolder: "+ "设置适配器");
}

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cart_title)
        TextView tvTitle;
        @BindView(R.id.cart_view)
        XRecyclerView cartView;
        @BindView(R.id.check_category)
        CheckBox categoryCheck;
        private Unbinder bind;
        public CartHolder(@NonNull View itemView) {
            super(itemView);
            bind = ButterKnife.bind(this,itemView);
        }
    }
}
