package com.example.chapter13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String JSON_URL = "https://api.donstreetking.online/get.php";
    public listitem ItemAPI;
    ListView listView;
    private List<listitem> ProductItemList;
    public String ID;
    Button Tombol_Tambah, Tombol_Load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        ProductItemList = new ArrayList<>();
        Tombol_Tambah = findViewById(R.id.Tombol_Tambah);
        Tombol_Load = findViewById(R.id.Tombol_Load);

        listView.setOnClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this, Detail_listitem.class);
                ListViewAdapter adapter = new ListViewAdapter(ProductItemList, getApplicationContext());

                listView.setAdapter(adapter);
                listitem posisi = adapter.getItem(position);
                Bundle data = new Bundle();
                data.putString("ID",posisi.getID());
                data.putString("NamaItem",posisi.getNamaItem());
                data.putString("Harga",posisi.getHarga());
                intent.putExtras(data);
                startActivity(intent);
            }
        });

        Tombol_Load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductItemList.clear();
                ListViewAdapter adapter = new ListViewAdapter(ProductItemList, getApplicationContext());
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                loaditem();
            }
        });

        Tombol_Tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add_Product.class);
                startActivity(intent);
            }
        });
        loaditem();
    }

    private void loaditem() {
        final JsonArrayRequest jsonArrayRequest = JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d("JsonArray",response.toString());
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        ItemAPI = new listitem(
                                jsonObject.getString("ID"),
                                jsonObject.getString("NamaItem"),
                                jsonObject.getString("Harga"),
                                ProductItemList.add(ItemAPI);
                        }
                    final ListViewAdapter adapter = new ListViewAdapter(ProductItemList, getApplicationContext());

                    listView.setAdapter(adapter);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                },
                new Response.ErrorListener() {
            @Override
                    public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
            });
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(jsonArrayRequest);
                }

                    }
