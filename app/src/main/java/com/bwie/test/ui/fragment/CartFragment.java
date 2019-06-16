package com.bwie.test.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.test.R;
import com.bwie.test.adapter.CartViewAdapter;
import com.bwie.test.contract.ICartContract;
import com.bwie.test.entity.ProductEntity;
import com.bwie.test.presenter.CartPresenterImpl;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Author:杨帅
 * Date:2019/6/15 9:37
 * Description：
 */
public class CartFragment extends Fragment implements ICartContract.ICartView {

    @BindView(R.id.cartview)
    XRecyclerView xRecyclerView;
    @BindView(R.id.choose_all)
    CheckBox allChoose;
    @BindView(R.id.sum)
    TextView sumTv;
    @BindView(R.id.settlement)
    TextView setTv;
    private Unbinder bind;
    private CartPresenterImpl presenter;
    private CartViewAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        //绑定Butterknife
        bind = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    /**
     * 初始化数据
     */
    private void initData() {
        presenter = new CartPresenterImpl(this);
        Map<String, String> params = new HashMap<>();
        params.put("userId", "6366");
        params.put("sessionId", "15605649738176366");
        presenter.requestModelData(params, getActivity());
    }

    @OnClick(R.id.choose_all)
    public void setAllChoose() {
        adapter.changeFlag(allChoose.isChecked());
        for (ProductEntity.Product product : adapter.getResult()) {
            for (ProductEntity.Goods goods : product.shoppingCartList) {
                goods.itemFlag = allChoose.isChecked();
            }
        }
        getPriceSum();
    }

    private void getPriceSum() {
        double priceSum = 0;
        for (ProductEntity.Product product : adapter.getResult()) {
            for (ProductEntity.Goods goods : product.shoppingCartList) {
                if(goods.itemFlag){
                    priceSum += Double.parseDouble(goods.price) * (goods.proNum == null ? 1 : Integer.parseInt(goods.proNum));
                }
            }
        }
        sumTv.setText("￥" + priceSum);
    }

    @Subscribe(sticky = true)
    public void getCount(String count){
        getPriceSum();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解绑Butterknife
        if (bind != null) {
            bind.unbind();
            bind = null;
        }
    }

    @Override
    public void showPreData(ProductEntity productEntity) {
        if (productEntity != null) {
            List<ProductEntity.Product> result = productEntity.result;
            Toast.makeText(getActivity(), productEntity.message, Toast.LENGTH_SHORT).show();
            adapter = new CartViewAdapter(getActivity(), result);
            xRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach();
    }
}
