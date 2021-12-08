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
import com.google.android.material.textfield.TextInputEditText;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class EditAddress extends AppCompatActivity {

    ImageView imgBack;
    TextInputEditText edtNewAddress;
    TextInputEditText edtNewNeighborhood;
    TextInputEditText edtNewComplement;
    TextInputEditText edtNewNumber;
    TextInputEditText edtNewCity;
    TextInputEditText edtNewUF;
    TextInputEditText edtNewCep;
    AppCompatButton btnEditar;

    String id;
    //Connection MySQL
    String HOST = "http://192.168.15.112/vulcar_database/Client/";
    //String HOST = "http://192.168.0.106/vulcar_database/Client/";
    //String HOST = "http://192.168.0.13/Vulcar--Syncmysql/Client/";

    RequestParams params = new RequestParams();
    AsyncHttpClient cliente;
    Client client = new Client();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);

        getSupportActionBar().hide();
        getIds();
        cliente = new AsyncHttpClient();
        maskFormat();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditAddress.this, MyAddress.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                montaObj();
            }
        });
    }

    private void montaObj() {
        String address = edtNewAddress.getText().toString();
        String number = edtNewNumber.getText().toString();
        String neighborhood = edtNewNeighborhood.getText().toString();
        String complement = edtNewComplement.getText().toString();
        String city = edtNewCity.getText().toString();
        String uf = edtNewUF.getText().toString();
        String cep = edtNewCep.getText().toString();

        boolean checkValidations = validationEdit(address, number, neighborhood, city, uf, cep);
        if(checkValidations == true){
            client.setId(id);
            client.setAddress(address);
            client.setNumber(number);
            client.setNeighborhood(neighborhood);
            client.setComplement(complement);
            client.setCity(city);
            client.setUf(uf);
            client.setCep(cep);
            updateAddres(client);
        }
    }

    private void updateAddres(Client client) {
        String url = HOST+"update_address.php";

        params.put("id", client.getId());
        params.put("address", client.getAddress());
        params.put("number", client.getNumber());
        params.put("neighborhood", client.getNeighborhood());
        params.put("complement", client.getComplement());
        params.put("city", client.getCity());
        params.put("uf", client.getUf());
        params.put("cep", client.getCep());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    Toast.makeText(EditAddress.this, "Endereço atualizado!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditAddress.this, MyAddress.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void getIds() {
        id = getIntent().getStringExtra("id");

        imgBack = findViewById(R.id.img_back);
        edtNewAddress = findViewById(R.id.edt_new_endereco);
        edtNewNumber = findViewById(R.id.edt_new_num);
        edtNewNeighborhood = findViewById(R.id.edt_new_bairro);
        edtNewComplement = findViewById(R.id.edt_new_complemento);
        edtNewCity = findViewById(R.id.edt_new_cidade);
        edtNewUF = findViewById(R.id.edt_new_uf);
        edtNewCep = findViewById(R.id.edt_new_cep);
        btnEditar = findViewById(R.id.btn_edit_address);
    }

    public void maskFormat(){
        SimpleMaskFormatter mask_uf = new SimpleMaskFormatter("LL");
        MaskTextWatcher mtw_uf = new MaskTextWatcher(edtNewUF, mask_uf);
        edtNewUF.addTextChangedListener(mtw_uf);

        SimpleMaskFormatter mask_cep = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtw_cep = new MaskTextWatcher(edtNewCep, mask_cep);
        edtNewCep.addTextChangedListener(mtw_cep);
    }

    public Boolean validationEdit(String address, String number, String neighborhood,
                                  String city, String uf, String cep){
        if(address.length() == 0){
            edtNewAddress.requestFocus();
            edtNewAddress.setError("Campo vazio.");
            return false;
        } else if (number.length() == 0) {
            edtNewNumber.requestFocus();
            edtNewNumber.setError("Campo vazio.");
            return false;
        } else if (neighborhood.length() == 0){
            edtNewNeighborhood.requestFocus();
            edtNewNeighborhood.setError("Campo vazio.");
            return false;
        } else if (city.length() == 0){
            edtNewCity.requestFocus();
            edtNewCity.setError("Campo vazio");
            return false;
        } else if (uf.length() == 0){
            edtNewUF.requestFocus();
            edtNewUF.setError("Campo vazio.");
            return false;
        } else if (uf.length() != 2){
            edtNewUF.requestFocus();
            edtNewUF.setError("UF inválido!");
            return false;
        } else if (cep.length() == 0){
            edtNewCep.requestFocus();
            edtNewCep.setError("Campo vazio.");
            return false;
        } else if (cep.length() != 9){
            edtNewCep.requestFocus();
            edtNewCep.setError("CEP inválido!");
            return false;
        } else {
            return true;
        }
    }
}