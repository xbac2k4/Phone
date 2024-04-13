package com.example.dienthoai.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dienthoai.Model.Manufacturer;
import com.example.dienthoai.R;
import com.example.dienthoai.Service.OnClickListen;

import java.util.ArrayList;

public class AdapterManufacturer extends RecyclerView.Adapter<AdapterManufacturer.ViewHolder>{
    private final Context context;
    private final ArrayList<Manufacturer> list;
    private OnClickListen onClickListen;
    private int selectedItemPosition = RecyclerView.NO_POSITION;

    public OnClickListen getOnClickListen() {
        return onClickListen;
    }

    public void setOnClickListen(OnClickListen onClickListen) {
        this.onClickListen = onClickListen;
    }

    public AdapterManufacturer(Context context, ArrayList<Manufacturer> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_manufacturer, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Manufacturer manufacturer = list.get(position);
        if (manufacturer.getImage() != "") {
            Glide.with(context)
                    .load(manufacturer.getImage())
                    .thumbnail(Glide.with(context).load(R.drawable.noimageicon))
                    .into(holder.img_ImageLogo);
        }
        holder.tvName.setText(manufacturer.getName());
        // Đặt màu sắc hoặc thay đổi giao diện người dùng dựa trên trạng thái được chọn của mục
        if (position == selectedItemPosition) {
            holder.tvName.setTextColor(Color.parseColor("#52DF13"));
        } else {
            holder.tvName.setTextColor(Color.BLACK);
        }

        // Xử lý sự kiện khi người dùng nhấp vào mục để chọn nó
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int previousSelected = selectedItemPosition;
                selectedItemPosition = holder.getAdapterPosition(); // Cập nhật đối tượng được chọn mới
                notifyItemChanged(previousSelected); // Huỷ chọn đối tượng trước
                notifyItemChanged(selectedItemPosition); // Chọn đối tượng mới
                onClickListen.selectItem(manufacturer);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        LinearLayout layout;
        ImageView img_ImageLogo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            layout = itemView.findViewById(R.id.layout);
            img_ImageLogo = itemView.findViewById(R.id.img_ImageLogo);
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View view) {
//            int position = getAdapterPosition();
//            if (position != RecyclerView.NO_POSITION) {
//                notifyItemChanged(selectedItemPosition); // Huỷ chọn đối tượng trước
//                selectedItemPosition = position; // Cập nhật đối tượng được chọn mới
//                notifyItemChanged(selectedItemPosition); // Chọn đối tượng mới
//            }
//        }
    }
}
