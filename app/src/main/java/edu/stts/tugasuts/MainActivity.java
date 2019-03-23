package edu.stts.tugasuts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edName;
    private RadioGroup rgType;
    private RadioButton rbTea,rbCoffee,rbSmoothies;
    private CheckBox cbPearl,cbPudding,cbRedBean,cbCoconut;
    private Button btnMinus,btnPlus,btnAdd,btnDelete,btnReset;
    private TextView txtQty,txtTotal,txtName;
    private RecyclerView rvOrder;
    private OrderAdapter adapter;
    private ArrayList<Order> arrOrder=new ArrayList<>();
    private long total=0;
    private int index=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName = (EditText) findViewById(R.id.editText_name);
        rgType = (RadioGroup) findViewById(R.id.radioGroup_type);
        rbTea = (RadioButton) findViewById(R.id.radioButton_tea);
        rbCoffee = (RadioButton) findViewById(R.id.radioButton_coffee);
        rbSmoothies= (RadioButton) findViewById(R.id.radioButton_smoothies);
        cbPearl = (CheckBox) findViewById(R.id.checkBox_pearl);
        cbPudding = (CheckBox) findViewById(R.id.checkBox_pudding);
        cbRedBean = (CheckBox) findViewById(R.id.checkBox_red_bean);
        cbCoconut = (CheckBox) findViewById(R.id.checkBox_coconut);
        btnMinus = (Button) findViewById(R.id.button_minus);
        btnPlus = (Button) findViewById(R.id.button_plus);
        txtQty = (TextView) findViewById(R.id.textView_qty);
        btnAdd = (Button) findViewById(R.id.button_add);
        btnDelete = (Button) findViewById(R.id.button_delete);
        btnReset = (Button) findViewById(R.id.button_reset);
        rvOrder = (RecyclerView) findViewById(R.id.recyclerview_order);
        txtName = (TextView) findViewById(R.id.textView_name);
        txtTotal = (TextView) findViewById(R.id.textView_total);
        adapter=new OrderAdapter(arrOrder,new RVClickListener(){
            @Override
            public void recyclerViewListClicked(View v, int posisi) {
                cbPearl.setChecked(false);cbPudding.setChecked(false);
                cbRedBean.setChecked(false);cbCoconut.setChecked(false);
                index = posisi;
                if (rbTea.getText().toString().equalsIgnoreCase(arrOrder.get(index).getType())) {
                    rbTea.setChecked(true);
                } else if (rbCoffee.getText().toString().equalsIgnoreCase(arrOrder.get(index).getType())) {
                    rbCoffee.setChecked(true);
                } else if (rbSmoothies.getText().toString().equalsIgnoreCase(arrOrder.get(index).getType())) {
                    rbSmoothies.setChecked(true);
                }
                for(int i = 0; i < arrOrder.get(index).getToppings().size(); i++) {
                    if (arrOrder.get(index).getToppings().get(i).equalsIgnoreCase(cbPearl.getText().toString())){
                        cbPearl.setChecked(true);
                    }
                    if (arrOrder.get(index).getToppings().get(i).equalsIgnoreCase(cbPudding.getText().toString())){
                        cbPudding.setChecked(true);
                    }
                    if (arrOrder.get(index).getToppings().get(i).equalsIgnoreCase(cbRedBean.getText().toString())){
                        cbRedBean.setChecked(true);
                    }
                    if (arrOrder.get(index).getToppings().get(i).equalsIgnoreCase(cbCoconut.getText().toString())){
                        cbCoconut.setChecked(true);
                    }
                }
                txtQty.setText(arrOrder.get(index).getQty()+"");
            }
        });
        rvOrder.setAdapter(adapter);
        total=0;
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtQty.setText(Integer.parseInt(txtQty.getText().toString())+1+"");
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(txtQty.getText().toString())>1){
                    txtQty.setText(Integer.parseInt(txtQty.getText().toString())-1+"");
                }

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edName.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(getBaseContext(),
                            "Field Name Cannot Be Null",
                            Toast.LENGTH_SHORT).show();
                }else {
                    ArrayList<String>arrToping= new ArrayList<>();
                    int subtotal=0;
                    if(cbPearl.isChecked()){
                        arrToping.add(cbPearl.getText()+"");
                        subtotal+=3000;
                    }
                    if(cbPudding.isChecked()){
                        arrToping.add(cbPudding.getText()+"");
                        subtotal+=3000;
                    }
                    if(cbRedBean.isChecked()){
                        arrToping.add(cbRedBean.getText()+"");
                        subtotal+=4000;
                    }
                    if(cbCoconut.isChecked()){
                        arrToping.add(cbCoconut.getText()+"");
                        subtotal+=4000;
                    }
                    if (rbTea.isChecked()) {
                        subtotal+=23000;
                        subtotal=subtotal*Integer.parseInt(txtQty.getText().toString());

                        arrOrder.add(new Order(
                                rbTea.getText().toString(),
                                arrToping,
                                Integer.parseInt(txtQty.getText().toString()),
                                subtotal
                        ));
                    }
                    else if (rbCoffee.isChecked()) {
                        subtotal+=25000;
                        subtotal=subtotal*Integer.parseInt(txtQty.getText().toString());
                        arrOrder.add(new Order(
                                rbCoffee.getText().toString(),
                                arrToping,
                                Integer.parseInt(txtQty.getText().toString()),
                                subtotal
                        ));
                    }
                    else if (rbSmoothies.isChecked()) {
                        subtotal+=30000;
                        subtotal=subtotal*Integer.parseInt(txtQty.getText().toString());
                        arrOrder.add(new Order(
                                rbSmoothies.getText().toString(),
                                arrToping,
                                Integer.parseInt(txtQty.getText().toString()),
                                subtotal
                        ));
                    }
                    Toast.makeText(getBaseContext(),
                            "Subtotal " + subtotal,
                            Toast.LENGTH_SHORT).show();
                    txtName.setText("Hi,"+edName.getText()+"!");
                    RecyclerView.LayoutManager lm =new LinearLayoutManager(MainActivity.this);
                    rvOrder.setLayoutManager(lm);
                    rvOrder.setAdapter(adapter);
                    total=0;
                    for(int i = 0; i < arrOrder.size(); i++) {
                        total+=arrOrder.get(i).getSubtotal();
                    }
                    txtTotal.setText(""+total);
                    reset();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total=0;
                txtTotal.setText(""+total);
                txtName.setText("Hi,Cust!");
                edName.setText("");
                txtQty.setText("1");
                rbTea.setChecked(true);
                cbPearl.setChecked(false);cbPudding.setChecked(false);
                cbRedBean.setChecked(false);cbCoconut.setChecked(false);
                arrOrder.clear();
                RecyclerView.LayoutManager lm =new LinearLayoutManager(MainActivity.this);
                rvOrder.setLayoutManager(lm);
                rvOrder.setAdapter(adapter);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index!=-1){
                    total=Integer.parseInt(txtTotal.getText().toString());
                    total-=arrOrder.get(index).getSubtotal();
                    txtTotal.setText(total+"");
                    arrOrder.remove(index);
                    RecyclerView.LayoutManager lm =new LinearLayoutManager(MainActivity.this);
                    rvOrder.setLayoutManager(lm);
                    rvOrder.setAdapter(adapter);
                    index=-1;
                    reset();
                }else{
                    Toast.makeText(getBaseContext(),
                            "Belum Memilih",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void reset(){
        txtQty.setText("1");
        rbTea.setChecked(true);
        cbPearl.setChecked(false);cbPudding.setChecked(false);
        cbRedBean.setChecked(false);cbCoconut.setChecked(false);
    }

}
