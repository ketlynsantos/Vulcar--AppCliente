package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.clientevulcar.Adapter.AdapterSearchBusiness;
import com.app.clientevulcar.Model.Budget;
import com.app.clientevulcar.Model.Client;
import com.app.clientevulcar.Model.ItemsBudget;
import com.app.clientevulcar.Model.Vehicle;
import com.app.clientevulcar.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class FinishRequest extends AppCompatActivity {

    public ImageView imgBack;
    public TextView txtNameBusiness;
    public TextView txtNameService;
    public TextView txtPrice, txtPriceTotal;
    public TextView txtAddress;
    public TextView txtNeigh;
    public AppCompatButton btnMoney;
    public AppCompatButton btnCreditCard;
    public AppCompatButton btnDebitCard;
    public AppCompatButton btnFinishRequest;
    public RadioGroup rgThingMoney;
    public RadioButton rbThingYes;
    public RadioButton rbThingNo;
    public RelativeLayout rlThing;
    public TextInputEditText edtThing;
    public TextInputLayout edtThingLayout;

    public String id, idBusiness, idServ, idVehicle, idCate, idOrc, idFunc = "1", nomeLoja, nomeServ, nomeAuto, descServ, nomeCate,
            valor, idPag, thing = "", date;

    public String formaPgto = "";
    public Boolean bThing;
    public Activity context;

    //Connection MySQL
    String HOST = "http://192.168.15.112/vulcar_database/";
    //String HOST = "http://192.168.0.106/vulcar_database/";
    //String HOST = "http://192.168.0.13/Vulcar--Syncmysql/";

    RequestParams params = new RequestParams();
    RequestParams params2 = new RequestParams();
    AsyncHttpClient cliente;

    com.app.clientevulcar.Model.Business business = new com.app.clientevulcar.Model.Business();
    Budget budget = new Budget();
    ItemsBudget items = new ItemsBudget();
    Client client = new Client();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_request);

        getSupportActionBar().hide();
        getIds();
        cliente = new AsyncHttpClient();
        context = FinishRequest.this;

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        date = sdf.format(c.getTime());

        carregarEnd();

        txtNameBusiness.setText(nomeLoja);
        txtNameService.setText(nomeServ);
        txtPrice.setText("R$"+valor);
        txtPriceTotal.setText("R$"+valor);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itI = new Intent(FinishRequest.this, Services.class);
                itI.putExtra("id", id);
                itI.putExtra("idBusiness", idBusiness);
                itI.putExtra("idServ", idServ);
                itI.putExtra("nomeServ", nomeServ);
                itI.putExtra("nomeLoja", nomeLoja);
                itI.putExtra("descServ", descServ);
                itI.putExtra("idCate", idCate);
                itI.putExtra("nomeCate", nomeCate);
                itI.putExtra("valor", valor);
                startActivity(itI);
                finish();
            }
        });

        btnMoney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Deixa a borda azul quando clica no botão
                btnMoney.setBackgroundResource(R.drawable.stroke_color_10dp_blue);
                //Tira a borda azul do dos outros botões
                btnCreditCard.setBackgroundResource(R.drawable.stroke_border_color_10dp);
                btnDebitCard.setBackgroundResource(R.drawable.stroke_border_color_10dp);

                formaPgto = "DINHEIRO";
                //Deixa o Radio group visivel
                rlThing.setVisibility(View.VISIBLE);
                bThing = null;
                //Radio group para escolher se vai precisar de troco ou não
                rgThingMoney.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_thing_yes:
                                bThing = true;
                                //Deixa o TextInputEditText visivel para digitar o troco
                                edtThingLayout.setVisibility(View.VISIBLE);
                                break;
                            case R.id.rb_thing_no:
                                bThing = false;
                                edtThingLayout.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        btnCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Deixa a borda azul quando clica no botão
                btnMoney.setBackgroundResource(R.drawable.stroke_border_color_10dp);
                //Tira a borda azul do dos outros botões
                btnCreditCard.setBackgroundResource(R.drawable.stroke_color_10dp_blue);
                btnDebitCard.setBackgroundResource(R.drawable.stroke_border_color_10dp);

                formaPgto = "CREDITO";
                rlThing.setVisibility(View.GONE);
                edtThingLayout.setVisibility(View.GONE);
                //Toast.makeText(FinishRequest.this, "Credito", Toast.LENGTH_SHORT).show();
            }
        });

        btnDebitCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Deixa a borda azul quando clica no botão
                btnMoney.setBackgroundResource(R.drawable.stroke_border_color_10dp);
                //Tira a borda azul do dos outros botões
                btnCreditCard.setBackgroundResource(R.drawable.stroke_border_color_10dp);
                btnDebitCard.setBackgroundResource(R.drawable.stroke_color_10dp_blue);

                formaPgto = "DEBITO";
                rlThing.setVisibility(View.GONE);
                edtThingLayout.setVisibility(View.GONE);
                //Toast.makeText(FinishRequest.this, "Debito", Toast.LENGTH_SHORT).show();
            }
        });

        btnFinishRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                montaObj();
            }
        });

    }

    private void carregarEnd() {
        String url = HOST + "Business/Select/select_business.php";

        business.setId(idBusiness);
        params.put("id", business.getId());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONObject jsonarray = new JSONObject(new String (responseBody));
                    String address = jsonarray.getString("LOJA_ENDERECO");
                    String num = jsonarray.getString("LOJA_NUM");
                    String neigh = jsonarray.getString("LOJA_BAIRRO");

                    txtAddress.setText(address + ", " + num);
                    txtNeigh.setText(neigh);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void montaObj() {


        if(formaPgto == "DINHEIRO") {
            //Se a forma de pagamento for dinheiro e precisar de troco
            if(bThing == null){
                Toast.makeText(FinishRequest.this, "Selecione uma opção!", Toast.LENGTH_SHORT).show();

            } else if(bThing == true) {
                thing = edtThing.getText().toString();
                if(thing.length() > 0) {
                    //Se for digitado o troco
                    Toast.makeText(FinishRequest.this, "AAA" + thing, Toast.LENGTH_SHORT).show();
                    idPag = "3";
                    cadOrca();
                } else {
                    //se não digitar o troco
                    edtThing.requestFocus();
                    edtThing.setError("");
                }
            } else if (bThing == false) {
                //Se a forma de pagamento for dinheiro, mas não precisar de troco
                idPag = "3";
                cadOrca();
            }
        } else if (formaPgto == "DEBITO") {
            //Toast.makeText(FinishRequest.this, "DEBITO", Toast.LENGTH_SHORT).show();
            idPag = "2";
            cadOrca();
        } else if (formaPgto == "CREDITO") {
            //Toast.makeText(FinishRequest.this, "credito", Toast.LENGTH_SHORT).show();
            idPag = "1";
            cadOrca();
        } else {
            Toast.makeText(context, "Selecione uma forma de pagamento!", Toast.LENGTH_SHORT).show();
        }

    }

    private void cadOrca() {

        int sts = 8;
        budget.setIdCliente(id);
        budget.setIdLoja(idBusiness);
        budget.setSts(sts);
        budget.setDtOrcamento(date);
        budget.setIdPag(idPag);
        budget.setThing(thing);
        cadastrarOrc(budget);
    }

    private void cadastrarOrc(Budget budget) {
        String url = HOST+"Client/insert_quote.php";

        params.put("cli_id", budget.getIdCliente());
        params.put("loja_id", budget.getIdLoja());
        params.put("status_id", budget.getSts());
        params.put("date", budget.getDtOrcamento());
        params.put("pag_id", budget.getIdPag());
        params.put("troco", budget.getThing());

        cliente.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    listarBudgets(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void listarBudgets(String resposta) {
        try {
            JSONArray jsonarray = new JSONArray(resposta);

            for (int i = 0; i < jsonarray.length(); i++){
                Budget b = new Budget();

                b.setId(jsonarray.getJSONObject(i).getString("TB_ORCAMENTO_ID"));
                b.setIdCliente(jsonarray.getJSONObject(i).getString("TB_CLIENTE_ID"));
                b.setIdLoja(jsonarray.getJSONObject(i).getString("TB_LOJA_ID"));
                b.setSts(jsonarray.getJSONObject(i).getInt("TB_STATUS_ID"));
                b.setDtOrcamento(jsonarray.getJSONObject(i).getString("TB_ORCAMENTO_DT"));
                b.setIdPag(jsonarray.getJSONObject(i).getString("TB_PAGAMENTO_ID"));
                b.setThing(jsonarray.getJSONObject(i).getString("TB_TROCO"));

                idOrc = b.getId();
                cadItens();
            }
        } catch(Exception erro) {
            Log.d("erro", "erro"+erro);
        }
    }

    private void cadItens() {
        items.setIdOrcamento(idOrc);
        items.setIdServico(idServ);
        items.setIdAutomovel(idVehicle);
        items.setIdFuncionario(idFunc);

        cadastrarItens(items);
    }

    private void cadastrarItens(ItemsBudget items) {
        String url = HOST+"Client/insert_itens_quote.php";

        params2.put("orca_id", items.getIdOrcamento());
        params2.put("serv_id", items.getIdServico());
        params2.put("auto_id", items.getIdAutomovel());
        params2.put("func_id", idFunc);

        cliente.post(url, params2, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    listarItems(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void listarItems(String resposta) {
        try {
            JSONArray jsonarray = new JSONArray(resposta);

            for (int i = 0; i < jsonarray.length(); i++){
                ItemsBudget b = new ItemsBudget();

                b.setId(jsonarray.getJSONObject(i).getString("TB_ITENS_ORC_ID"));
                b.setIdOrcamento(jsonarray.getJSONObject(i).getString("TB_ORCAMENTO_ID"));
                b.setIdServico(jsonarray.getJSONObject(i).getString("TB_SERVICO_ID"));
                b.setIdAutomovel(jsonarray.getJSONObject(i).getString("TB_AUTOMOVEL_ID"));

                String itensId = b.getId();

                Intent it = new Intent(FinishRequest.this, FollowUpService.class);

                it.putExtra("idBusiness", idBusiness);
                it.putExtra("id", id);


                startActivity(it);
            }
        } catch(Exception erro) {
            Log.d("erro", "erro"+erro);
        }
    }

    private void getIds() {
        id = getIntent().getStringExtra("id");
        idBusiness = getIntent().getStringExtra("idBusiness");
        idServ = getIntent().getStringExtra("idServ");
        idVehicle = getIntent().getStringExtra("idVehicle");
        idCate = getIntent().getStringExtra("idCate");

        nomeServ = getIntent().getStringExtra("nomeServ");
        nomeLoja = getIntent().getStringExtra("nomeLoja");
        nomeAuto = getIntent().getStringExtra("nomeAuto");
        descServ = getIntent().getStringExtra("descServ");
        nomeCate = getIntent().getStringExtra("nomeCate");
        valor = getIntent().getStringExtra("valor");

        imgBack = findViewById(R.id.img_back);
        txtNameBusiness = findViewById(R.id.txt_name_business);
        txtNameService = findViewById(R.id.txt_name_services);
        txtPrice = findViewById(R.id.txt_price);
        txtPriceTotal = findViewById(R.id.txt_price_total);
        txtAddress = findViewById(R.id.txt_address);
        txtNeigh = findViewById(R.id.txt_neigh);
        btnMoney = findViewById(R.id.btn_money);
        btnCreditCard = findViewById(R.id.btn_credit_card);
        btnDebitCard = findViewById(R.id.btn_debit_card);
        btnFinishRequest = findViewById(R.id.btn_finish_request);
        rgThingMoney = findViewById(R.id.rg_thing);
        rbThingYes = findViewById(R.id.rb_thing_yes);
        rbThingNo = findViewById(R.id.rb_thing_no);
        txtNameBusiness = findViewById(R.id.txt_name_business);
        rlThing = findViewById(R.id.relative_layout_5);
        edtThing = findViewById(R.id.edt_thing);
        edtThingLayout = findViewById(R.id.edt_thing_layout);
    }
}