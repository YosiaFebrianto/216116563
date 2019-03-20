package edu.stts.tugasuts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>  {
    private static RVClickListener mylistener;
    //menentukan data yang akan ditampilkan pada Recycler View
    private ArrayList<Order> arrOrder;
    //Membuat constructor dari MenuAdapter yang menerima data ArrayList
    public OrderAdapter (ArrayList<Order>arrOrder,RVClickListener rvcl)
    {
        this.arrOrder=arrOrder;
        mylistener=rvcl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Menentukan layout untuk 1 row dari RecyclerView
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v= inflater.inflate(R.layout.row_item_order,viewGroup,false);

        return new ViewHolder(v);//generate ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //mengisi widget dengan data dari ArrayList
        viewHolder.textView_qty_type.setText(""+arrOrder.get(i).getQty()+" "+arrOrder.get(i).getType());
        viewHolder.textView_toppings.setText(arrOrder.get(i).getToppings()+" ");
        viewHolder.textView_subtotal.setText(""+arrOrder.get(i).getSubtotal());
    }

    @Override
    public int getItemCount() {
        return (arrOrder!=null)?arrOrder.size():0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_qty_type,textView_toppings,textView_subtotal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //find view by id
            textView_qty_type=itemView.findViewById(R.id.textView_qty_type);
            textView_toppings=itemView.findViewById(R.id.textView_toppings);
            textView_subtotal=itemView.findViewById(R.id.textView_subtotal);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mylistener.recyclerViewListClicked(v,ViewHolder.this.getLayoutPosition());
                    //getLayoutPosition() mendapatkan posisi ViewHolder yg di click
                }
            });
        }
    }
}
