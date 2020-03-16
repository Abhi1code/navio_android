package in.bit_by_bit.navio.ui.workers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import in.bit_by_bit.navio.ui.gettersetter.ItemGS;
import in.bit_by_bit.navio.ui.model.MySingleton;

public class FetchItemWorker {

    private static FetchItemWorker mWorker;
    private static Context context;
    public ItemWorkerFinished delegate = null;

    private FetchItemWorker(Context c, ItemWorkerFinished delegate){
        context = c;
        this.delegate = delegate;
    }

    public interface ItemWorkerFinished {
        void ItemWorkerFinishedMethod(ArrayList<ItemGS> list);
    }

    public static synchronized FetchItemWorker getInstance(Context context, ItemWorkerFinished delegate) {

        if (mWorker == null) {
            mWorker = new FetchItemWorker(context, delegate);
        }
        return mWorker;
    }

    public void fetchItemDetails(final long id){

        String url ="http://192.168.137.1:5000/item?id=" + id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ABHI", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<ItemGS> arrayList = new ArrayList<>();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONArray array = jsonArray.getJSONArray(i);
                        arrayList.add(new ItemGS(array.getInt(0), array.getString(3), array.getInt(2)));
                        //Log.d("ABHI", array.getString(0));
                    }

                    delegate.ItemWorkerFinishedMethod(arrayList);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d("ABHI", e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(error == null)return;

                Log.d("ABHI", "Error response " + error.getMessage());
            }
        });

        MySingleton.getInstance(context).addtorequestque(stringRequest, "fetch_item_list");
    }
}
