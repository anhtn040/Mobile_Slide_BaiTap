package com.example.baitap11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainGetUser extends AppCompatActivity {

    Button btnGetUser, btnGetText;
    ListView lvDanhSach;
    ArrayList<User> data_user = new ArrayList<>();
    ArrayAdapter adapter_user;

    RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_get_user);
        setControl();
        setEvent();
    }

    private void setEvent(){
        requestQueue = Volley.newRequestQueue(this);
        adapter_user =  new ArrayAdapter(this, android.R.layout.simple_expandable_list_item_1,data_user);
        lvDanhSach.setAdapter(adapter_user);
        btnGetUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetUser();
            }
        });
        btnGetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetText();
            }
        });

    }
    private void setControl(){
        btnGetUser = findViewById(R.id.btnGetUser);
        btnGetText = findViewById(R.id.btnGetText);
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }

    private void GetUser(){
        String url = "https://api.github.com/users";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        data_user.clear();
                        for(int i =0; i< response.length(); i++){
                            try{
                                User user = new User();
                                JSONObject object = (JSONObject) response.get(i);
                                user.setLogin(object.getString("login"));
                                user.setAvatar_url(object.getString("avatar_url"));
                                user.setSubsriptions_url(object.getString("subscriptions_url"));
                                data_user.add(user);
                            }
                            catch(JSONException e){
                                throw new RuntimeException(e);
                            }

                        }
                        adapter_user.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainGetUser.this, "Loi mang", Toast.LENGTH_SHORT).show();
            }});

            requestQueue.add(request);
        }

        private void GetText(){
            String url = "https://api.github.com/search/users?q=mojombo";
            StringRequest request = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>(){
                        @Override
                        public void onResponse(String response) {
                            data_user.clear();
                            try {
                                JSONObject object = new JSONObject(response.toString());
                                String msg = "Thong tin:\n";
                                msg += "total_count: "+object.getString("total_count");
                                JSONArray jsonArray = object.getJSONArray("items");
                                for(int i=0; i<jsonArray.length(); i++){
                                    JSONObject object1 = jsonArray.getJSONObject(i);
                                    msg +="\nlogin: "+object1.getString("login");
                                }
                                Toast.makeText(MainGetUser.this, msg, Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainGetUser.this, "Loi mang", Toast.LENGTH_SHORT).show();
                }});

            requestQueue.add(request);
            }
}