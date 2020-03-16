package in.bit_by_bit.navio.ui.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.bit_by_bit.navio.R;
import in.bit_by_bit.navio.ui.gettersetter.ItemGS;
import in.bit_by_bit.navio.ui.gettersetter.PathConfig;
import in.bit_by_bit.navio.ui.ui.support.ItemListAdaptor;
import in.bit_by_bit.navio.ui.workers.FetchItemWorker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ChooseItem extends AppCompatActivity implements FetchItemWorker.ItemWorkerFinished {

    private Button listokay;
    private RecyclerView itemListRecyclerview;
    private ArrayList<ItemGS> itemList;
    private ItemListAdaptor itemListAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_item);
        listokay = findViewById(R.id.listokay);
        itemListRecyclerview = findViewById(R.id.itemlist);

        setrecycleview();
        listokay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setrecycleview() {
        itemList = new ArrayList<>();
        itemListRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        itemListRecyclerview.setHasFixedSize(true);
        itemListAdaptor = new ItemListAdaptor(itemList, this);
        itemListRecyclerview.setAdapter(itemListAdaptor);
        fetchItemList();
    }

    private void fetchItemList(){
        FetchItemWorker.getInstance(this, this).
                fetchItemDetails(PathConfig.getInstance().getFloorid());
    }

    @Override
    public void ItemWorkerFinishedMethod(ArrayList<ItemGS> list) {
        //setrecycleview();
        itemList.clear();
        PathConfig.getInstance().invalidateItemSelected();
        for(int i=0;i<list.size();i++){
            itemList.add(list.get(i));
        }
        Log.d("ABHI", "Length of returned list " + itemList.size());
        itemListAdaptor = new ItemListAdaptor(itemList, this);
        itemListRecyclerview.setAdapter(itemListAdaptor);
        itemListAdaptor.notifyItemRangeChanged(0, itemList.size());
    }
}
