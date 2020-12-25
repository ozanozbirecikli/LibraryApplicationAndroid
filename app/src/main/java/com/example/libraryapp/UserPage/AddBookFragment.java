package com.example.libraryapp.UserPage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapp.R;
import com.example.libraryapp.Requests.AddBook;

import org.json.JSONObject;

public class AddBookFragment extends Fragment {

    private EditText book_name, type_name, author_name, book_year, book_amount;
    private String mName, mType, mAuthor;
    private int mBook_year, mBook_amount;
    private TextView add_book_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_book, container, false);

        mDefineview(view);

        return view;
    }


    public void mDefineview(final View view) {


        book_name = view.findViewById(R.id.book_name);
        type_name = view.findViewById(R.id.type_name);
        author_name = view.findViewById(R.id.author_name);
        book_year = view.findViewById(R.id.book_year);
        book_amount = view.findViewById(R.id.book_amount);

        add_book_button = view.findViewById(R.id.add_book_button);

        add_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAddBook(view);

            }
        });


    }

    public void mAddBook(View view) {

        mName = book_name.getText().toString().trim();
        mType = type_name.getText().toString().trim();
        mAuthor = author_name.getText().toString().trim();

        String year = book_year.getText().toString().trim();
        String amount = book_amount.getText().toString().trim();

        if (TextUtils.isEmpty(mName)) {
            book_name.setError("Please Enter Book Name!");
            book_name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mType)) {
            type_name.setError("Please Enter Book Type!");
            type_name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(mAuthor)) {
            author_name.setError("Please Enter Author Name!");
            author_name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(year)) {
            book_year.setError("Please Enter Author Name!");
            book_year.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(amount)) {
            book_amount.setError("Please Enter Author Name!");
            book_amount.requestFocus();
            return;
        }

        mBook_year = Integer.parseInt(year);
        mBook_amount = Integer.parseInt(amount);


        AddBook.addBook(getActivity(), mName, mType, mBook_year, mAuthor, mBook_amount);
        AddBook addBook = new AddBook();
        addBook.setListener(new AddBook.Listener() {
            @Override
            public void sendResponse(Object meta) {
                try {
                    JSONObject response = (JSONObject) meta;


                    if (response.getBoolean("result")) {
                        Toast.makeText(getActivity(), response.getString("message"), Toast.LENGTH_SHORT).show();
                        getActivity().onBackPressed();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}