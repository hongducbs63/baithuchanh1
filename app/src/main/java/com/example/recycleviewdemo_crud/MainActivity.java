package com.example.recycleviewdemo_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.recycleviewdemo_crud.model.Cat;
import com.example.recycleviewdemo_crud.model.CatAdapter;
import com.example.recycleviewdemo_crud.model.ListCatAdapter;
import com.example.recycleviewdemo_crud.model.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CatAdapter.CatItemListener, SearchView.OnQueryTextListener{
    private Spinner sp;
    private ListView listView;
    private RecyclerView recyclerView;
    private CatAdapter adapter;
    private EditText eName, eDescribe, ePrice;
    private Button btAdd, btUpdate;
    private Button btAll;
    private SearchView searchView;
    private int pcurr;
//    private ListView list;
    private ArrayList<Cat> listCat;
    private ListCatAdapter listCatAdapter;
    private int[] imgs = {R.drawable.anh_1, R.drawable.anh_2, R.drawable.anh_3,
            R.drawable.anh_4, R.drawable.anh_5, R.drawable.anh_6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        adapter = new CatAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cat cat = new Cat();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String desc = eDescribe.getText().toString();
                String p = ePrice.getText().toString();
                int img = R.drawable.anh_1;
                double price = 0;
                try {
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    cat.setImg(img);
                    cat.setName(name);
                    cat.setDescribe(desc);
                    cat.setPrice(price);
                    adapter.add(cat);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Giá chỉ được nhập số! Vui lòng nhap lai!!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cat cat = new Cat();
                String i = sp.getSelectedItem().toString();
                String name = eName.getText().toString();
                String desc = eDescribe.getText().toString();
                String p = ePrice.getText().toString();
                int img = R.drawable.anh_1;
                double price = 0;
                try {
                    img = imgs[Integer.parseInt(i)];
                    price = Double.parseDouble(p);
                    cat.setImg(img);
                    cat.setName(name);
                    cat.setDescribe(desc);
                    cat.setPrice(price);
                    adapter.update(pcurr, cat);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Giá chỉ được nhập ký tự số! Vui lòng nhap lai!!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        listCat = new ArrayList<>();
////        listCat.add(R.drawable.anh_1, "1", "d", 1+"");
//        listCatAdapter = new ListCatAdapter(listCat);
//        listView = findViewById(R.id.listCat);
//        listView.setAdapter(listCatAdapter);

//        btAll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                List<Cat> list = adapter.getBackup();
////                List<Cat> filtelist = new ArrayList<>();
////                for (Cat i: adapter.getBackup()){
////                    filtelist.add(i);
////                }
////                if (filtelist.isEmpty()){
////                    Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();
////                }else {
////                    adapter.filterList(filtelist);
////                }
////                adapter = new CatAdapter(list);
//                listCat = new ArrayList<>();
////        listCat.add(R.drawable.anh_1, "1", "d", 1+"");
//                listCatAdapter = new ListCatAdapter(listCat);
//                listView = findViewById(R.id.listCat);
//                listView.setAdapter(listCatAdapter);
//            }
//        });
    }

    private void initView() {
        sp = findViewById(R.id.img);
        SpinnerAdapter adapter = new SpinnerAdapter(this);
        sp.setAdapter(adapter);
//        listView = findViewById(R.id.listCat);
//        ListCatAdapter adapter1 = new ListCatAdapter(this);
//        listView.setAdapter(adapter1);
        recyclerView = findViewById(R.id.recycleview);
        eName = findViewById(R.id.name);
        eDescribe = findViewById(R.id.describe);
        ePrice = findViewById(R.id.price);
        btAdd = findViewById(R.id.btAdd);
        btUpdate = findViewById(R.id.btUpdate);
        btUpdate.setEnabled(false);
        searchView = findViewById(R.id.search);
//        btAll = findViewById(R.id.btAll);
    }

    @Override
    public void onItemClick(View view, int position) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        pcurr = position;
        Cat cat = adapter.getItem(position);
        int img = cat.getImg();
        int p = 0;
        for (int i=0; i<imgs.length; i++){
            if (img == imgs[i]){
                p = i;
                break;
            }
        }
        sp.setSelection(p);
        eName.setText(cat.getName());
        eDescribe.setText(cat.getDescribe());
        ePrice.setText(cat.getPrice()+"");
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }

    private void filter(String s) {
        List<Cat> filtelist = new ArrayList<>();
        for (Cat i: adapter.getBackup()){
            if (i.getName().toLowerCase().contains(s.toLowerCase())){
                filtelist.add(i);
            }
        }
        if (filtelist.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else {
            adapter.filterList(filtelist);
        }
    }
}