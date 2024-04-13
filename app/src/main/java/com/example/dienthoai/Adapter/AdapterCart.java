package com.example.dienthoai.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dienthoai.Dao.DaoCart;
import com.example.dienthoai.Model.Cart;
import com.example.dienthoai.R;

import java.util.ArrayList;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder>{
    private final Context context;
    private final ArrayList<Cart> list;
    DaoCart daoCart;

    public AdapterCart(Context context, ArrayList<Cart> list) {
        this.context = context;
        this.list = list;
        daoCart = new DaoCart(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_giohang, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = list.get(position);
        Glide.with(context)
                .load(list.get(position).getImage())
                .thumbnail(Glide.with(context).load(R.drawable.noimageicon))
                .into(holder.imgImage);
        holder.tvName.setText(list.get(position).getName());
        holder.tvQuantity.setText(String.valueOf(list.get(position).getQuantity()));
        holder.tvPrice.setText(String.valueOf(list.get(position).getPrice()));
        holder.tvTotalPrice.setText("Tổng tiền: " + list.get(position).getTotalprice());
//        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (daoCart.delete(cart.get_id())) {
//                    list.remove(position);
//                    notifyDataSetChanged();
////                    Toast.makeText(context, "Đã xóa sản phẩm", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        holder.btn_tang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                list.get(position).setQuantity(list.get(position).getQuantity() + 1);
//                holder.tvTotalPrice.setText((list.get(position).getTotalprice() * list.get(position).getQuantity()));
//                holder.tvQuantity.setText(""+list.get(position).getQuantity());
//                notifyDataSetChanged();
//            }
//        });
        holder.btn_giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvQuantity, tvPrice, tvTotalPrice;
        ImageView imgImage;
        ImageButton btn_delete, btn_tang, btn_giam;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgImage = itemView.findViewById(R.id.img_Image);
            tvName = itemView.findViewById(R.id.tv_name);
            tvQuantity =  itemView.findViewById(R.id.tv_quantity);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvTotalPrice = itemView.findViewById(R.id.tv_totalprice);
            btn_delete = itemView.findViewById(R.id.btn_delete);
            btn_tang = itemView.findViewById(R.id.btn_tang);
            btn_giam = itemView.findViewById(R.id.btn_giam);
        }
    }
}
