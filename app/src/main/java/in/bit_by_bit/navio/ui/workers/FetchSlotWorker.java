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

import in.bit_by_bit.navio.ui.gettersetter.QrGS;
import in.bit_by_bit.navio.ui.gettersetter.SlotGS;
import in.bit_by_bit.navio.ui.model.MySingleton;

public class FetchSlotWorker {

    private static FetchSlotWorker mWorker;
    private static Context context;
    public  AllSlotWorkerFinished delegate = null;

    private FetchSlotWorker(Context c, AllSlotWorkerFinished delegate){
        context = c;
        this.delegate = delegate;
    }

    public interface AllSlotWorkerFinished {
        void AllSlotWorkerFinishedMethod(ArrayList<SlotGS> slotList);
    }

    public static synchronized FetchSlotWorker getInstance(Context context, AllSlotWorkerFinished delegate) {

        if (mWorker == null) {
            mWorker = new FetchSlotWorker(context, delegate);
        }
        return mWorker;
    }

    public void fetchallslotDetails(final long id){

        String url ="http://192.168.137.1:5000/slots?id=" + id;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ABHI", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<SlotGS> arrayList = new ArrayList<>();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONArray array = jsonArray.getJSONArray(i);
                        SlotGS slotGS = new SlotGS(array.getInt(0),
                                array.getInt(2), array.getInt(3));
                        arrayList.add(slotGS);
                        //Log.d("ABHI", array.getString(0));
                    }

                    delegate.AllSlotWorkerFinishedMethod(arrayList);

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

        MySingleton.getInstance(context).addtorequestque(stringRequest, "fetch_allqr_list");
    }
}
