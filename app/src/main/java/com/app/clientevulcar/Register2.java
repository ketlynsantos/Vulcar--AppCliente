package com.app.clientevulcar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class Register2 extends AppCompatActivity {
    public EditText edtEndereco;
    public EditText edtNum;
    public EditText edtComp;
    public EditText edtBairro;
    public EditText edtCidade;
    public EditText edtUF;
    public EditText edtCep;
    public AppCompatButton btnRegister;
    public ImageView imgBack;

    // HOST Database
    private String HOST ="http://172.20.10.5/vulcar_database";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        getSupportActionBar().hide();
        getIds();
        maskFormat();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register2.this, Register.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUsers();
            }
        });
    }

    private void getIds(){
        edtEndereco = this.findViewById(R.id.edt_endereco);
        edtNum = this.findViewById(R.id.edt_num);
        edtComp = this.findViewById(R.id.edt_complemento);
        edtBairro = this.findViewById(R.id.edt_bairro);
        edtCidade = this.findViewById(R.id.edt_cidade);
        edtUF = this.findViewById(R.id.edt_uf);
        edtCep = this.findViewById(R.id.edt_cep);
        btnRegister = this.findViewById(R.id.btn_register2);
    }

    private void maskFormat(){
        SimpleMaskFormatter mask_cep = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtw_cep = new MaskTextWatcher(edtCep, mask_cep);
        edtCep.addTextChangedListener(mtw_cep);

        SimpleMaskFormatter mask_uf = new SimpleMaskFormatter("LL");
        MaskTextWatcher mtw_uf = new MaskTextWatcher(edtUF, mask_uf);
        edtUF.addTextChangedListener(mtw_uf);
    }

    private Boolean validationRegister(String endereco, String num, String bairro, String cidade, String uf, String cep) {
        if(endereco.length() == 0){
            edtEndereco.requestFocus();
            edtEndereco.setError("Campo vazio!");
            return false;
        } else if(num.length() == 0){
            edtNum.requestFocus();
            edtNum.setError("Número vazio");
            return false;
        } else if (bairro.length() == 0){
            edtNum.requestFocus();
            edtNum.setError("Bairro vazio");
            return false;
        } else if (cidade.length() == 0){
            edtCidade.requestFocus();
            edtCidade.setError("Cidade vazio");
            return false;
        } else if (uf.length() == 0){
            edtUF.requestFocus();
            edtUF.setError("UF vazio");
            return false;
        } else if (uf.length() != 2){
            edtUF.requestFocus();
            edtUF.setError("UF inválido");
            return false;
        } else if (cep.length() == 0){
            edtCep.requestFocus();
            edtCep.setError("CEP vazio");
            return false;
        } else if (cep.length() != 9){
            edtCep.requestFocus();
            edtCep.setError("CEP inválido");
            return false;
        } else {
            return true;
        }
    }

    private void registerUsers() {
        //Puxando variáveis da Register
        String name = getIntent().getStringExtra("name");
        String cpf = getIntent().getStringExtra("cpf");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String password = getIntent().getStringExtra("password");


        String endereco = edtEndereco.getText().toString();
        String num = edtNum.getText().toString();
        String bairro = edtBairro.getText().toString();
        String comp = edtComp.getText().toString();
        String cidade = edtCidade.getText().toString();
        String uf = edtUF.getText().toString();
        String cep = edtCep.getText().toString();
        String sts = "6";

        boolean checkValidations = validationRegister(endereco, num, bairro, cidade, uf, cep);
        if(checkValidations == true){
            String url = HOST + "/create.php";

            Ion.with(Register2.this)
                    .load(url)
                    .setBodyParameter("nome", name)
                    .setBodyParameter("cpf", cpf)
                    .setBodyParameter("email", email)
                    .setBodyParameter("tel", phone)
                    .setBodyParameter("senha", password)
                    .setBodyParameter("endereco", endereco)
                    .setBodyParameter("num", num)
                    .setBodyParameter("comp", comp)
                    .setBodyParameter("bairro", bairro)
                    .setBodyParameter("cidade", cidade)
                    .setBodyParameter("uf", uf)
                    .setBodyParameter("cep", cep)
                    .setBodyParameter("sts", sts)

                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            // do stuff with the result or error
                            if(result.get("CREATE").getAsString().equals("OK")){
                                Toast.makeText(Register2.this, "Registrado com sucesso!", Toast.LENGTH_SHORT).show();
                            } else if(result.get("CREATE").getAsString().equals("ERRO")) {
                                Toast.makeText(Register2.this, "Erro no registro!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        } else {
            Toast.makeText(Register2.this,"Ocorreu um erro!", Toast.LENGTH_SHORT).show();
        }
    }
}