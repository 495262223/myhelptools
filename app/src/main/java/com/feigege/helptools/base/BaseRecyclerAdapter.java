package com.feigege.helptools.base;

import android.animation.Animator;
import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public abstract class BaseRecyclerAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private final List<T> data;

    public BaseRecyclerAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
        isFirstOnly(false);//判断是否重复动画
//        setPreLoadNumber(2);// 当列表滑动到倒数第N个Item的时候(默认是1)回调onLoadMoreRequested方法
        this.data = data;
    }


    /**
     * 如果需要在点击事件中获取其他子控件可以使用
     * 在条目点击回调时有时如：setOnItemChildClickListener
     */
    @Nullable
    public View getViewByPosition(RecyclerView recyclerView, int position, @IdRes int viewId) {
        if (recyclerView == null) {
            return null;
        }
        BaseViewHolder viewHolder = (BaseViewHolder) recyclerView.findViewHolderForLayoutPosition(position);
        if (viewHolder == null) {
            return null;
        }
        return viewHolder.getView(viewId);
    }

    //第一屏显示动画
    @Override
    protected void startAnim(Animator anim, int index) {
        super.startAnim(anim, index);
        if (index < 8)
            anim.setStartDelay(index * 100);
    }

    @Override
    protected void convert(BaseViewHolder holder, T itemdata) {
        int position = holder.getLayoutPosition();//获取对应的position
        getRecyclerView(holder, itemdata, position);
    }

    protected abstract void getRecyclerView(BaseViewHolder holder, T itemData, int position);
}
