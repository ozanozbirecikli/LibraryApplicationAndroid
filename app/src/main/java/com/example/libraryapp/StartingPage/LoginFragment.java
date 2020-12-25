package com.example.libraryapp.StartingPage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapp.R;
import com.example.libraryapp.Requests.LoginRequest;

import org.json.JSONObject;

public class LoginFragment extends Fragment {

    private NavController navController = null;

    private EditText tEmail, tPassword;
    private String email, password;
    private TextView login;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savdInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mDefineView(view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
    }

    public void mDefineView(final View view) {

        login = view.findViewById(R.id.Login_button);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                LoginUser(view);
            }
        });
    }

    public void LoginUser(View view) {

        tEmail = view.findViewById(R.id.email);
        tPassword = view.findViewById(R.id.password);

        email = tEmail.getText().toString().trim();
        password = tPassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            tEmail.setError("Please Enter Your Email!");
            tEmail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            tPassword.setError("Please Enter Your Password!");
            tPassword.requestFocus();
            return;
        }

        LoginRequest.SendLogInRequest(getActivity(), email, password);
        LoginRequest request = new LoginRequest();
        request.setListener(new LoginRequest.Listener() {
            @Override
            public void sendResponse(Object meta) {
                try {
                    if (meta != null) {

                        JSONObject tObj = (JSONObject) meta;

                        if (!tObj.getBoolean("result")) {
                            Toast.makeText(getActivity(), tObj.getString("message"), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getActivity(), tObj.getString("message"), Toast.LENGTH_SHORT).show();
                            navController.navigate(R.id.action_loginFragment_to_homeFragment);
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}