package com.wft.sky_gym.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wft.sky_gym.R;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.MyViewHolder> {
        Context context;

public PurchaseAdapter(Context context) {
        this.context = context;
        }

@NonNull
@Override
public PurchaseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchaserecycler, parent, false);
        PurchaseAdapter.MyViewHolder vh = new PurchaseAdapter.MyViewHolder(mView);
        return vh;

        }


@Override
public void onBindViewHolder(@NonNull PurchaseAdapter.MyViewHolder holder, int position) {

        }

@Override
public int getItemCount() {
        return 7;
        }

class MyViewHolder extends RecyclerView.ViewHolder{

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);



    }
}
}
