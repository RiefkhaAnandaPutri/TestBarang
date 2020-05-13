package com.example.testbarang;

import android.app.Activity;
import android.content.Content;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReferences;
import com.google.firebase.database.FirebaseDatabse;

import androidx.appcompat.app.AppCompatActivity;

public class TambahData extends AppCompatActivity {
    private DatabaseReference database;

    //variable fields edittext dan button
    private Button btSubmit;
    private EditText etKode;
    private EditText etNama;

    @Override
    protected void OnCreate(Bundle savedInstanceState) {
        super.OnCreate(savedInstanceState);
        setContentView(R.layuot.activity_tambah_data);

        etKode = (EditText) findViewById(R.id.editNo);
        etNama = (EditText) findViewById(R.id.editNama);
        btSubmit = (Button) findViewById(R.id.btnOk);

        //mengambil referensi ke Firebase database
        database = FirebaseDatabase.getInstance().getReference();

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etKode.getText().toString().isEmpty()) && !(etNama.getText().toString(isEmpty()) )
                        submitBrg(new Barang(etKode.getText().toString(),etNama.getText().toString()));
                else
                    Toast.makeText(getApplicationContext() "Data tidak boleh kosong", Toast.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Content.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etKode.getWindowToken(), 0);
            }
        });
    }
    public void submit(Barang brg){
        database.child("Barang").push().setValue(brg).addOnSuccessListener(this, new onSuccessListener<void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etKode.setText("");
                etNama.setText("");
                Toast.makeText(getApplicatonContext(),"Data Berhasil ditambahkan", Toats.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity actvity) {
        return new Intent(activity, TambahData.class);
    }
}