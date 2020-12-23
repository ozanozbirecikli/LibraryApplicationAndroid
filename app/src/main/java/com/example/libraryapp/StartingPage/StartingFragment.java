package com.example.libraryapp.StartingPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.libraryapp.R;
import com.example.libraryapp.Requests.GetAllItems;


public class StartingFragment extends Fragment {

    private NavController navController  = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_starting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController = Navigation.findNavController(view);
        view.findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_startingFragment_to_loginFragment);
                /*
                GetAllItems.GetItems(getActivity());
                GetAllItems items = new GetAllItems();
                items.setListener(new GetAllItems.Listener(){
                    @Override
                    public void sendResponse(Object meta) {
//                        Log.wtf("logging", meta.toString());

                    }
                });*/
            }
        });

        view.findViewById(R.id.signup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_startingFragment_to_singUpFragment);

            }
        });
    }
}