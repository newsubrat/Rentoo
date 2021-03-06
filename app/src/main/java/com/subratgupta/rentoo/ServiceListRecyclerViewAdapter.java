package com.subratgupta.rentoo;

import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.support.annotation.NonNull;
        import android.support.v4.content.ContextCompat;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;

        import java.net.URL;
        import java.util.List;

public class ServiceListRecyclerViewAdapter extends RecyclerView.Adapter<ServiceListRecyclerViewAdapter.ViewHolder> {

    private List<ServiceDataType> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ServiceListRecyclerViewAdapter(Context context, List<ServiceDataType> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.owner_list_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ServiceDataType owner = mData.get(position);
        holder.myNameView.setText("Name: "+owner.getName());
        holder.myAddressView.setText("Add: "+owner.getAddress());
        holder.myTypeOfSpaceView.setText("Phone: "+owner.getPhone());
        holder.myRentView.setText("Service: "+owner.getType_of_service());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setClickListener(TenantHomeReg tenantHomeReg) {

    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myNameView;
        TextView myAddressView;
        TextView myTypeOfSpaceView;
        TextView myRentView;

        ViewHolder(View itemView) {
            super(itemView);
            myNameView = itemView.findViewById(R.id.owner_name);
            myAddressView = itemView.findViewById(R.id.address);
            myTypeOfSpaceView = itemView.findViewById(R.id.type_of_space);
            myRentView = itemView.findViewById(R.id.rent);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    ServiceDataType getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
