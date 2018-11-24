package com.panda.cvsandroid.mvvm_and_databinding.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.panda.cvsandroid.R;
import com.panda.cvsandroid.databinding.ItemDataBinding;
import com.panda.cvsandroid.mvvm_and_databinding.viewmodel.DataItemViewModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder>
{
    private static final String TAG = "DataAdapter";
    private List<Movie> data;

    public DataAdapter() {
        this.data = new ArrayList<>();
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data,
                new FrameLayout(parent.getContext()), false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, int position)
    {
        Movie dataModel = data.get(position);
        holder.setViewModel(new DataItemViewModel(dataModel));
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    @Override
    public void onViewAttachedToWindow(DataViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.bind();
    }

    @Override
    public void onViewDetachedFromWindow(DataViewHolder holder)
    {
        super.onViewDetachedFromWindow(holder);
        holder.unbind();
    }

    public void updateData(@Nullable List<Movie> data)
    {
        if (data == null || data.isEmpty()) {
            this.data.clear();
        } else {
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }


    /* package */ static class DataViewHolder extends RecyclerView.ViewHolder {
        /* package */ ItemDataBinding binding;

        /* package */ DataViewHolder(View itemView) {
            super(itemView);
            bind();
        }

        /* package */ void bind() {
            if (binding == null) {
                binding = DataBindingUtil.bind(itemView);
            }
        }

        /* package */ void unbind() {
            if (binding != null) {
                binding.unbind(); // Don't forget to unbind
            }
        }

        /* package */ void setViewModel(DataItemViewModel viewModel) {
            if (binding != null) {
                binding.setViewModel(viewModel);
            }
        }
    }
}