package in.bit_by_bit.navio.ui.model;

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {

    private static MySingleton mysingleton;
    private static Context context;
    private RequestQueue requestQueue;


    private MySingleton(Context c) {
        context = c;
        requestQueue = requestQueue();
    }

    public static synchronized MySingleton getInstance(Context context) {

        if (mysingleton == null) {
            mysingleton = new MySingleton(context);
        }
        return mysingleton;
    }

    public RequestQueue requestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }

    public <T> void addtorequestque(Request<T> request) {
        requestQueue.add(request);
    }

    public <T> void addtorequestque(Request<T> request, String tag){
        request.setTag(TextUtils.isEmpty(tag) ? tag : "tag");
        requestQueue.add(request);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }
}
