package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.app.clientevulcar.Model.Client;
import com.app.clientevulcar.R;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

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
    //Connection MySQL
    //String HOST = "http://192.168.15.127/vulcar_database/Client/";
    //String HOST = "http://172.20.10.5/vulcar_database/Client/";
    String HOST = "http://192.168.0.107/Vulcar--Syncmysql/Client/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;
    Client client = new Client();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        getSupportActionBar().hide();
        getIds();
        maskFormat();
        cliente = new AsyncHttpClient();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register2.this, Register.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                montaObj();
            }
        });
    }

    private void montaObj() {
        String name = getIntent().getStringExtra("name");
        String cpf = getIntent().getStringExtra("cpf");
        String email = getIntent().getStringExtra("email");
        String phone = getIntent().getStringExtra("phone");
        String password = getIntent().getStringExtra("password");

        String endereco = edtEndereco.getText().toString();
        String complemento = edtComp.getText().toString();
        String numero = edtNum.getText().toString();
        String bairro = edtBairro.getText().toString();
        String cidade = edtCidade.getText().toString();
        String uf = edtUF.getText().toString();
        String cep = edtCep.getText().toString();
        int sts = 1;

        boolean checkValidations = validationRegister(endereco, numero, bairro,
                cidade, uf, cep);

        if(checkValidations == true){
            client.setNome(name);
            client.setCpf(cpf);
            client.setEmail(email);
            client.setPhone(phone);
            client.setPassword(password);
            client.setAddress(endereco);
            client.setComplement(complemento);
            client.setNumber(numero);
            client.setNeighborhood(bairro);
            client.setCity(cidade);
            client.setUf(uf);
            client.setCep(cep);
            client.setSts(sts);
            cadastrarClient(client);
        } else {
            Toast.makeText(this, "Falha ao cadastrar!", Toast.LENGTH_SHORT).show();
        }
    }

    private void cadastrarClient(Client client) {
        String url = HOST+"create.php";

        params.put("name", client.getNome());
        params.put("cpf", client.getCpf());
        params.put("email", client.getEmail());
        params.put("phone", client.getPhone());
        params.put("pass", client.getPassword());
        params.put("address", client.getAddress());
        params.put("number", client.getNumber());
        params.put("complement", client.getComplement());
        params.put("neighborhood", client.getNeighborhood());
        params.put("city", client.getCity());
        params.put("uf", client.getUf());
        params.put("cep", client.getCep());
        params.put("sts", client.getSts());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    Toast.makeText(Register2.this, "Conta criada com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register2.this, Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Register2.this, "Falha ao criar a conta!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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
        imgBack = this.findViewById(R.id.img_back);
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
            edtBairro.requestFocus();
            edtBairro.setError("Bairro vazio");
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
}