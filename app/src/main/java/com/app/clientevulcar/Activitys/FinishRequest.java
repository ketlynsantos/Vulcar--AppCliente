package com.app.clientevulcar.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.clientevulcar.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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

    public String idClient;
    public String formaPgto = "";
    public Boolean bThing = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_request);

        getSupportActionBar().hide();
        getIds();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itI = new Intent(FinishRequest.this, Business.class);
                itI.putExtra("id", idClient);
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
                //Toast.makeText(FinishRequest.this, "Dinheiro", Toast.LENGTH_SHORT).show();

                //Radio group para escolher se vai precisar de troco ou não
                rgThingMoney.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.rb_thing_yes:
                                bThing = true;
                                //Deixa o TextInputEditText visivel para digitar o troco
                                edtThingLayout.setVisibility(View.VISIBLE);
                                Toast.makeText(FinishRequest.this, "Dinheiro com troco", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.rb_thing_no:
                                bThing = false;
                                edtThingLayout.setVisibility(View.GONE);
                                Toast.makeText(FinishRequest.this, "Dinheiro sem troco", Toast.LENGTH_SHORT).show();
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

    private void montaObj() {
        String thing = edtThing.getText().toString();

        if(formaPgto == "DINHEIRO") {
            //Se a forma de pagamento for dinheiro e precisar de troco
            if(bThing == true) {
                if(thing.length() > 0) {
                    //Se for digitado o troco
                    Toast.makeText(FinishRequest.this, "AAA" + thing, Toast.LENGTH_SHORT).show();
                } else {
                    //se não digitar o troco
                    edtThing.requestFocus();
                    edtThing.setError("");
                }
            } else if (bThing == false) {
                //Se a forma de pagamento for dinheiro, mas não precisar de troco
                //Toast.makeText(FinishRequest.this, "Dinheiro sem troco", Toast.LENGTH_SHORT).show();
            }
        } else if (formaPgto == "DEBITO") {
            //Toast.makeText(FinishRequest.this, "DEBITO", Toast.LENGTH_SHORT).show();
        } else if (formaPgto == "CREDITO") {
            //Toast.makeText(FinishRequest.this, "credito", Toast.LENGTH_SHORT).show();
        }

    }

    private void getIds() {
        idClient = getIntent().getStringExtra("id");

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