package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.clientevulcar.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.android.material.textfield.TextInputEditText;

public class EditPersonData extends AppCompatActivity {

    public ImageView imgBack;
    public TextInputEditText edtName;
    public TextInputEditText edtCPF;
    public AppCompatButton btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person_data);

        getSupportActionBar().hide();
        getIds();
        maskFormat();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itI = new Intent(EditPersonData.this, MyData.class);
                startActivity(itI);
                finish();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String cpf = edtCPF.getText().toString();

                Boolean checkValidations = validationData(name, cpf);
                Intent intent = new Intent(EditPersonData.this, MyData.class);

                if(checkValidations == true){
                }
            }
        });
    }

    private void getIds() {
        imgBack = findViewById(R.id.img_back);
        edtName = findViewById(R.id.edt_name);
        edtCPF = findViewById(R.id.edt_cpf);
        btnEdit = findViewById(R.id.btn_edit_data);
    }

    public void maskFormat() {
        SimpleMaskFormatter mask_cpf = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtw_cpf = new MaskTextWatcher(edtCPF, mask_cpf);
        edtCPF.addTextChangedListener(mtw_cpf);
    }

    private Boolean validationData(String name, String cpf) {
        if (name.length() == 0) {
            edtName.requestFocus();
            edtName.setError("Campo vazio.");
            return false;
        } else if(!name.matches("[a-zA-Z]+")) {
            edtName.requestFocus();
            edtName.setError("Apenas letras, por favor!");
            return false;
        }   else if (cpf.length() == 0) {
            edtCPF.requestFocus();
            edtCPF.setError("Campo vazio");
            return false;
        } else if (cpf.length() != 14) {
            edtCPF.requestFocus();
            edtCPF.setError("CPF inválido!");
            return false;
        } else {
            return true;
        }
    }
}