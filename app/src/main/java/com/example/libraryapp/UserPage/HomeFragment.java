package com.example.libraryapp.UserPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.libraryapp.DTO.Statics;
import com.example.libraryapp.DTO.Users;
import com.example.libraryapp.R;

public class HomeFragment extends Fragment {

    private Button seeBooks, addBook, exit, reservedBook;
    private TextView name, surname, user_type;

    private NavController navController = null;

    private Users loggedInUser = Statics.getInstance().loggedInUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mDefineView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

    }

    public void mDefineView(View view) {

        seeBooks = view.findViewById(R.id.seeBooks);
        addBook = view.findViewById(R.id.addBook);
        exit = view.findViewById(R.id.exit);
        reservedBook = view.findViewById(R.id.reservedBook);

        name = view.findViewById(R.id.home_name);
        surname = view.findViewById(R.id.home_surname);
        user_type = view.findViewById(R.id.user_type);

        name.setText(loggedInUser.name);
        surname.setText(loggedInUser.surname);
        if (loggedInUser.iD_ROLE == 1) {
            user_type.setText("Library Staff");
            addBook.setVisibility(View.VISIBLE);

        } else {
            user_type.setText("Standard-User");
            addBook.setVisibility(View.GONE);
        }

        seeBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_seeBooksFragment);
            }
        });


        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_addBookFragment);
            }
        });

        reservedBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.action_homeFragment_to_allReservations);

            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

    }
}