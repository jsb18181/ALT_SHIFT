package com.assignment.alt_shift_cs991;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class Pending_Swaps_EMP extends AppCompatActivity {

    private ArrayList<Pending_Swap_Item> ShiftArray;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_swaps_emp);
        ShiftArray = new ArrayList<Pending_Swap_Item>();
        recyclerView = (RecyclerView) findViewById(R.id.rcview);
        mAdapter = new Pending_Swap_Adapter(this.ShiftArray);
        recyclerView.setAdapter(mAdapter);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        ShiftArray.add(new Pending_Swap_Item("new shift"));
        ShiftArray.add(new Pending_Swap_Item("two shift"));

    }

    public void backToMain(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}