package com.example.chapter13;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class Add_Product extends AppCompatActivity {
    Button Tombol_Tambah;
    EditText etID, etNamaItem, etHarga;
    String ID, NamaItem, Harga;
    private static final String REGISTER_URL = "https://api.donstreetking.online/post.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Tombol_Tambah = (Button) findViewById(R.id.button_tambah);
        etID = (EditText) findViewById(R.id.editTextID);
        etNamaItem = (EditText) findViewById(R.id.editTextNamaItem);
        etHarga = (EditText) findViewById(R.id.editTextHarga);

        Tombol_Tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ID = etID.getText().toString();
                NamaItem = etNamaItem.getText().toString();
                Harga = etHarga.getText().toString();
                product(ID, NamaItem, Harga);
            }
        });
    }
    private void product(String ID, String NamaItem, String Harga) {
        class Addproduct extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RequestHandler requestHandler = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Add_Product.this, "Please Wait", "Loading...");
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute();
                if (etID.getText().toString().trim().length() == 0 || etNamaItem.getText().toString().trim().length() == 0 || etHarga.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Add_Product.this, "Sukses!", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(Add_Product.this, MainActivity.class);
                    startActivity(intent);
                }
            }
            @Override
            protected String doInBackground(String... params) {
                HashMap<String, String> data = new HashMap<String, String>();
                data.put("ID",params[0]);
                data.put("NamaItem",params[1]);
                data.put("Harga",params[2]);
                String result = requestHandler.sendPostRequest(REGISTER_URL,data);

                return result;
            }
        }
        Addproduct ru = new Addproduct();
        ru.execute(ID, NamaItem, Harga);
    }
}