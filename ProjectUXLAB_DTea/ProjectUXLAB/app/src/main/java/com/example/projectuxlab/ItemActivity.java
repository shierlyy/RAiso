package com.example.projectuxlab;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ItemActivity extends AppCompatActivity implements RecyclerViewInterface{

    ImageButton prevButton, nextButton;
    int[] imageList = {R.drawable.popuppencilcase, R.drawable.dualhighlighter, R.drawable.catholder,
            com.example.projectuxlab.R.drawable.scissor, R.drawable.oceanmaskingtape};
    int count = imageList.length;
    int currentIndex = 0;
    ViewFlipper viewFlipper;
    Handler handler;
    Runnable runnable;
    private RecyclerView recyclerView;
    private List<Stationery> stationeryList;
    private StationeryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Hot Selling Items");
        tabSpec1.setIndicator("Hot Selling");
        tabSpec1.setContent(R.id.tab1);
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("All Items");
        tabSpec2.setIndicator("All Items");
        tabSpec2.setContent(R.id.tab2);
        tabHost.addTab(tabSpec2);

        prevButton = findViewById(R.id.prevButton);
        nextButton = findViewById(R.id.nextButton);
        viewFlipper = findViewById(R.id.carosel);

        for (int image : imageList) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(image);
            viewFlipper.addView(imageView);
        }

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                currentIndex++;
                if (currentIndex >= count) {
                    currentIndex = 0;
                }
                viewFlipper.setInAnimation(ItemActivity.this, R.anim.slide_in_right);
                viewFlipper.setOutAnimation(ItemActivity.this, R.anim.slide_out_left);
                viewFlipper.showNext();
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(runnable, 3000);

        prevButton.setOnClickListener(v -> {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = count - 1;
            }
            viewFlipper.setInAnimation(ItemActivity.this, R.anim.slide_in_left);
            viewFlipper.setOutAnimation(ItemActivity.this, R.anim.slide_out_right);
            viewFlipper.showPrevious();
        });

        nextButton.setOnClickListener(v -> {
            currentIndex++;
            if (currentIndex >= count) {
                currentIndex = 0;
            }
            viewFlipper.setInAnimation(ItemActivity.this, R.anim.slide_in_right);
            viewFlipper.setOutAnimation(ItemActivity.this, R.anim.slide_out_left);

            viewFlipper.showNext();
        });

        recyclerView = findViewById(R.id.recyclerViewStationeries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        stationeryList = new ArrayList<>();
        stationeryList.add(new Stationery("Corduroy Pop-up Pencil Case", R.drawable.popuppencilcase, "Rp. 170.000"));
        stationeryList.add(new Stationery("Dual-Tip Highlighter", R.drawable.dualhighlighter, "Rp. 27.000"));
        stationeryList.add(new Stationery("Portable Scissors", R.drawable.scissor, "Rp. 20.000"));
        stationeryList.add(new Stationery("Kitty Stationery Holders", R.drawable.catholder, "Rp. 8.000"));
        stationeryList.add(new Stationery("Ocean Masking Tape", R.drawable.oceanmaskingtape, "Rp. 15.000"));
        adapter = new StationeryAdapter(stationeryList, this);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerViewAllItems = findViewById(R.id.recyclerView);
        recyclerViewAllItems.setLayoutManager(new LinearLayoutManager(this));
        List<Stationery> allStationeryList = new ArrayList<>();
        allStationeryList.add(new Stationery("Ocean Masking Tape", R.drawable.oceanmaskingtape, "Rp. 15.000"));
        allStationeryList.add(new Stationery("Tombow Mono Eraser", R.drawable.monoeraser, "Rp. 10.000"));
        allStationeryList.add(new Stationery("Smooth Gel Pen", R.drawable.smoothgelpen, "Rp. 12.000"));
        allStationeryList.add(new Stationery("Cat Pencil Case", R.drawable.catpencilcase, "Rp. 85.000"));
        allStationeryList.add(new Stationery("Notepad", R.drawable.notepad, "Rp. 30.000"));
        allStationeryList.add(new Stationery("Portable Correction Tape", R.drawable.portablecorrectiontape, "Rp. 18.000"));
        allStationeryList.add(new Stationery("Corduroy Pop-up Pencil Case", R.drawable.popuppencilcase, "Rp. 170.000"));
        allStationeryList.add(new Stationery("Dual-Tip Highlighter", R.drawable.dualhighlighter, "Rp. 27.000"));
        allStationeryList.add(new Stationery("Portable Scissors", R.drawable.scissor, "Rp. 20.000"));
        allStationeryList.add(new Stationery("Kitty Stationery Holders", R.drawable.catholder, "Rp. 8.000"));



        StationeryAdapter allItemsAdapter = new StationeryAdapter(allStationeryList, this);
        recyclerViewAllItems.setAdapter(allItemsAdapter);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.bottom_item);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottom_home:
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_item:
                    return true;
                case R.id.bottom_about:
                    startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                case R.id.bottom_logout:
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
            }
            return false;
        });



    }



//    private static class Stationery {
//        private final String name;
//        private final int imageResourceId;
//        private final String price;
//
//        public Stationery(String name, int imageResourceId, String price) {
//            this.name = name;
//            this.imageResourceId = imageResourceId;
//            this.price = price;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public int getImageResourceId() {
//            return imageResourceId;
//        }
//
//        public String getPrice() {
//            return price;
//        }
//    }


    static class StationeryAdapter extends RecyclerView.Adapter<StationeryAdapter.StationeryViewHolder> {
        private final List<Stationery> stationeryList;
        private final RecyclerViewInterface recyclerViewInterface;

        public StationeryAdapter(List<Stationery> stationeryList, RecyclerViewInterface recyclerViewInterface) {
            this.stationeryList = stationeryList;
            this.recyclerViewInterface = recyclerViewInterface;
        }

        @NonNull
        @Override
        public StationeryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stationery, parent, false);
            return new StationeryViewHolder(itemView, recyclerViewInterface);
        }

        @Override
        public void onBindViewHolder(@NonNull StationeryViewHolder holder, int position) {
            Stationery stationery = stationeryList.get(position);
            holder.imageView.setImageResource(stationery.getImageResourceId());
            holder.textViewName.setText(stationery.getName());
            holder.textViewPrice.setText(stationery.getPrice());
        }

        @Override
        public int getItemCount() {
            return stationeryList.size();
        }

        static class StationeryViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textViewName;
            TextView textViewPrice;

            StationeryViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageViewStationery);
                textViewName = itemView.findViewById(R.id.textViewStationeryName);
                textViewPrice = itemView.findViewById(R.id.textViewStationeryPrice);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (recyclerViewInterface != null){
                            int pos = getAdapterPosition();

                            if (pos != RecyclerView.NO_POSITION){
                                recyclerViewInterface.onItemClick(pos);
                            }

                        }
                    }
                });
            }
        }
        
    }

    @Override
    public void onItemClick(int position) {
        Stationery stationery = stationeryList.get(position);
        Intent intent = new Intent(ItemActivity.this, DetailActivity.class);
        intent.putExtra("Name", stationery.getName());
        intent.putExtra("Price", stationery.getPrice());
        intent.putExtra("Image", stationery.getImageResourceId());
        startActivity(intent);
    }
}

