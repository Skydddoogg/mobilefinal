package com.example.lab203_62.mobilefinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    private TextView helloMessage, quoteMessage;
    private ListView menuList;
    private String name, userId;
    ArrayList<String> menu = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initMenu();
    }

    void initViews(){
        helloMessage = getView().findViewById(R.id.home_hello_message);
        quoteMessage = getView().findViewById(R.id.home_quote_message);

        menuList = getView().findViewById(R.id.home_list);

        SharedPreferences userDetails = getContext().getSharedPreferences("userdetails", MODE_PRIVATE);
        String userId = userDetails.getString("userId", "");
        String name = userDetails.getString("name", "");

//        Bundle bundle = getArguments();
//        if (bundle != null){
//            name = bundle.getString("name");
//            userId = bundle.getString("userId");
//        }

        helloMessage.setText("Hello " + name.toUpperCase());
        quoteMessage.setText("this is my quote" + " ''" +"today is my day" + "''");

    }

    void initMenu(){
        menu.clear();
        menu.add("PROFILE SETUP");
        menu.add("MY FRIENDS");
        menu.add("SIGN OUT");

        ArrayAdapter<String> menuAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menu
        );

        menuList.setAdapter(menuAdapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedMenuItem = menu.get(i);
                if (selectedMenuItem.equals("PROFILE SETUP")){

                } else if (selectedMenuItem.equals("MY FRIENDS")){

                } else if (selectedMenuItem.equals("SIGN OUT")){

                } else {

                }
            }
        });

    }
}
