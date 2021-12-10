package com.example.chapter13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Detail_listitem extends AppCompatActivity {
    EditText tvID, tvNamaItem, tvHarga;
    Button Tombol_Delete;
    private String ID;
    private static final String DELETE_URL = "https://api.donstreetking.online/delete.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_listitem);
        tvID = findViewById(R.id.tvID);
        tvNamaItem = findViewById(R.id.tvNamaItem);
        tvHarga = findViewById(R.id.tvHarga);
        Tombol_Delete = findViewById(R.id.Tombol_Delete);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        String ID = data.getString("ID");
        String NamaItem = data.getString("NamaItem");
        String Harga = data.getString("Harga");

        tvID.setText(ID);
        tvNamaItem.setText(NamaItem);
        tvHarga.setText(Harga);
        final String kode_ID = data.getString("ID");

        Tombol_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Detail_listitem.this);
                alertDialogBuilder.setMessage("Mau di hapus kah?");
                alertDialogBuilder.setPositiveButton("Si Senor", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        ID = Kode_ID;
                        DeleteProduct(ID);
                        startActivity(new Intent(Detail_listitem.this, MainActivity.class));
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("NYET!!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    private void DeleteProduct(String ID) {
        class DeleteProduct extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RequestHandler requestHandler = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Detail_listitem.this, "Deleteing...", "Please Wait...", false, false);

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Detail_listitem.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("ID", params[0]);
                String result = requestHandler.sendPostRequest(DELETE_URL,data);
                return result;
            }
        }
        DeleteProduct dp = new DeleteProduct();
        dp.execute(ID);
    }
}