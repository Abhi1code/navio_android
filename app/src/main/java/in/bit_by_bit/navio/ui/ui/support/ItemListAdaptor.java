package in.bit_by_bit.navio.ui.ui.support;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import in.bit_by_bit.navio.R;
import in.bit_by_bit.navio.ui.gettersetter.ItemGS;
import in.bit_by_bit.navio.ui.gettersetter.PathConfig;

public class ItemListAdaptor extends RecyclerView.Adapter<ItemListAdaptor.Viewholder> {

    private ArrayList<ItemGS> itemList;
    private Context context;

    public ItemListAdaptor(ArrayList<ItemGS> itemList, Context context){
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tile, parent, false);
        Viewholder viewholder = new Viewholder(v);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final Viewholder viewHolder, final int position) {
        viewHolder.item.setText(itemList.get(position).getName());
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    PathConfig.getInstance().setSelectedItem(itemList.get(position));
                } else {
                    PathConfig.getInstance().unsetSelectedItem(itemList.get(position));
                }
                Log.d("ABHI", PathConfig.getInstance().convertSlotListToString());
            }
        });
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewHolder.checkBox.isChecked()){
                    viewHolder.checkBox.setActivated(false);
                } else {
                    viewHolder.checkBox.setActivated(true);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView item;
        CheckBox checkBox;

        public Viewholder(View itemView){
            super(itemView);
            cardView = itemView.findViewById(R.id.card);
            item = itemView.findViewById(R.id.item);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}