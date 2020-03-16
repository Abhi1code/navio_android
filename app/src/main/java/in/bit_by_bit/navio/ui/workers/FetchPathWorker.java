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

import in.bit_by_bit.navio.ui.gettersetter.PathGS;
import in.bit_by_bit.navio.ui.model.MySingleton;

public class FetchPathWorker {

    private static FetchPathWorker mWorker;
    private static Context context;
    public PathWorkerFinished delegate = null;

    private FetchPathWorker(Context c, PathWorkerFinished delegate){
        context = c;
        this.delegate = delegate;
    }

    public interface PathWorkerFinished {
        void PathWorkerFinishedMethod(ArrayList<PathGS> pathGSArrayList);
    }

    public static synchronized FetchPathWorker getInstance(Context context, PathWorkerFinished delegate) {

        if (mWorker == null) {
            mWorker = new FetchPathWorker(context, delegate);
        }
        return mWorker;
    }

    public void fetchPathDetails(final long id, final String dst){

        String url ="http://192.168.137.1:5000/algo?id="+ id + "&s=1&d=" + dst;
        Log.e("ABHI", url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("ABHI", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    ArrayList<PathGS> arrayList = new ArrayList<>();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONArray array = jsonArray.getJSONArray(i);
                        for(int j=0;j<(array.length()-1);j++){
                            JSONArray inode = array.getJSONArray(j);
                            JSONArray fnode = array.getJSONArray(j+1);
                            PathGS pathGS = new PathGS(inode.getInt(1), inode.getInt(2),
                                    fnode.getInt(1), fnode.getInt(2),
                                    inode.getInt(0), fnode.getInt(0));
                            arrayList.add(pathGS);
                        }
                        //Log.d("ABHI", array.getString(0));
                    }
                    delegate.PathWorkerFinishedMethod(arrayList);

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

        MySingleton.getInstance(context).addtorequestque(stringRequest, "fetch_path_list");
    }
}
