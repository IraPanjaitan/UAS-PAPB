package rmp.mobile.uaspapb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private RecyclerView toDoRV;
    private ArrayList<ToDoModel> toDoModelArrayList;
    private ToDoAdapter toDoAdapter;
    Thread t;
    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_main);

        title = findViewById(R.id.tvTitle);
        toDoRV = findViewById(R.id.idRVToDo);

        toDoModelArrayList = new ArrayList<>();
        toDoAdapter = new ToDoAdapter(this,toDoModelArrayList);
        toDoRV.setAdapter(toDoAdapter);

        getToDoInfo();
    }

    private void getToDoInfo(){
        String url = "https://mgm.ub.ac.id/todo.php";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                toDoModelArrayList.clear();

                h = new Handler(Looper.getMainLooper());
                t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for (int i=0; i<response.length(); i++){
                                JSONObject jsonObject = response.getJSONObject(i);

                                String activity = jsonObject.getString("what");
                                String time = jsonObject.getString("time");

                                toDoModelArrayList.add(new ToDoModel(activity,time));
                            }
                            h.post(new Runnable() {
                                @Override
                                public void run() {
                                    toDoAdapter.notifyDataSetChanged();
                                }
                            });
                            Thread.sleep(100);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
                t.start();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Gagal mengambil data!", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);

    }

}