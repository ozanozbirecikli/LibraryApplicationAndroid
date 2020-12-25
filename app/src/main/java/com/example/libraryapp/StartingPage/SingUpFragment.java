package com.example.libraryapp.StartingPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapp.R;
import com.example.libraryapp.Requests.SignUpRequest;

import org.json.JSONObject;


public class SingUpFragment extends Fragment {

    private EditText Sign_Name, Sign_Surname, Sign_Email, Sign_Password, Sign_Password2;
    private String name, surname, email, password, password2;
    private int user_role;
    private TextView signUp;
    private CheckBox is_admin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sing_up, container, false);
        mDefineView(view);
        return view;
    }

    public void mDefineView(final View view) {


        signUp = view.findViewById(R.id.Sign_signup);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpUser(view);
            }

        });
    }

    public void SignUpUser(View view) {

        Sign_Name = view.findViewById(R.id.name);
        Sign_Surname = view.findViewById(R.id.surname);
        Sign_Email = view.findViewById(R.id.Sign_email);
        Sign_Password = view.findViewById(R.id.Sign_password);
        Sign_Password2 = view.findViewById(R.id.Sign_password2);
        is_admin = view.findViewById(R.id.is_admin);

        name = Sign_Name.getText().toString().trim();
        surname = Sign_Surname.getText().toString().trim();
        email = Sign_Email.getText().toString().trim();
        password = Sign_Password.getText().toString().trim();
        password2 = Sign_Password2.getText().toString().trim();

        if(is_admin.isSelected()){
            user_role = 1;
        }else{
            user_role = 0;
        }

        if (TextUtils.isEmpty(name)) {
            Sign_Name.setError("Please Enter Your Name!");
            Sign_Name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(surname)) {
            Sign_Surname.setError("Please Enter Your Surname!");
            Sign_Surname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Sign_Email.setError("Please Enter Your Email!");
            Sign_Email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Sign_Password.setError("Please Enter Your Password!");
            Sign_Password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password2)) {
            Sign_Password2.setError("Please Enter Your Password Again!");
            Sign_Password2.requestFocus();
            return;
        }
        if (!password2.equals(password)) {
            Sign_Password2.setError("Your Password Does Not Match!");
            Sign_Password2.requestFocus();
            return;
        }

        SignUpRequest.SendSignUpRequest(getActivity(), name, surname, email, password, user_role);
        SignUpRequest request = new SignUpRequest();
        request.setListener(new SignUpRequest.Listener() {
            @Override
            public void sendResponse(Object meta) {

                try {

                    JSONObject response = (JSONObject) meta;
                    if (response.getBoolean("result")) {
                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    }
                    Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}