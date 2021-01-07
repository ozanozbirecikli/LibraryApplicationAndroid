package com.example.libraryapp.UserPage;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapp.DTO.Items;
import com.example.libraryapp.DTO.Statics;
import com.example.libraryapp.R;
import com.example.libraryapp.Requests.GetAllItems;
import com.example.libraryapp.Requests.GetAllReservations;
import com.example.libraryapp.Requests.ReserveItem;
import com.example.libraryapp.Requests.ReturnItem;

import org.json.JSONObject;

import java.util.ArrayList;


public class AllReservations extends Fragment {

    private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_reservations, container, false);
        mDefineView(view);
        return view;
    }

    public void mDefineView(View view){

        recyclerView = view.findViewById(R.id.Reservations_RecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        GetAllReservations.getAllReservations(getActivity(), Statics.getInstance().loggedInUser.iD_USERS);
        GetAllReservations getAllReservations = new GetAllReservations();
        getAllReservations.setListener(new GetAllReservations.Listener() {
            @Override
            public void sendResponse(Object meta) {
                ArrayList<Items> Allreservations = (ArrayList<Items>) meta;

                AdapterReservationList adapter = new AdapterReservationList(getActivity(), getContext(), Allreservations);
                recyclerView.setAdapter(adapter);

            }
        });


    }
}


class AdapterReservationList extends RecyclerView.Adapter<AdapterReservationList.ReservationsViewHolder> {

    private final LayoutInflater mInflater;
    public ArrayList<Items> mItems = new ArrayList<>();
    private Context mContext;
    private Activity mActivity;

    public AdapterReservationList(Activity activity, Context context, ArrayList<Items> items) {
//        mItems.addAll(items);
        mItems = items;
        mContext = context;
        mActivity = activity;
        this.mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public AdapterReservationList.ReservationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = mInflater.inflate(R.layout.model_book, parent, false);

        return new ReservationsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterReservationList.ReservationsViewHolder holder, int position) {

        final Items selectedItem = mItems.get(position);

        if (selectedItem.name.length() > 15) {
            holder.itemName.setText(selectedItem.name.substring(0,15));
        }else{
            holder.itemName.setText(selectedItem.name);

        }
        holder.itemAuthor.setText(selectedItem.author);
        holder.itemType.setText(selectedItem.type);
        holder.itemYear.setText("Year: " + selectedItem.year);
        holder.itemAmount.setText("Amount: " + selectedItem.amount);
        holder.reserveButton.setText("Return");

        holder.reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReturnItem.returnItem(mActivity, Statics.getInstance().loggedInUser.iD_USERS, selectedItem.id);
                ReturnItem returnItem = new ReturnItem();
                returnItem.setListener(new ReturnItem.Listener() {
                    @Override
                    public void sendResponse(Object meta) {
                        try {

                            JSONObject response = (JSONObject) meta;

                            if (response.getBoolean("result")) {
                                Toast.makeText(mActivity, response.getString("message"), Toast.LENGTH_SHORT).show();
                                mActivity.onBackPressed();
                            }else{
                                Toast.makeText(mActivity, response.getString("message"), Toast.LENGTH_SHORT).show();

                            }

                        } catch (Exception e) { e.printStackTrace(); }
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    static class ReservationsViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView itemAuthor;
        TextView itemType;
        TextView itemYear;
        TextView itemAmount;
        TextView reserveButton;

        public ReservationsViewHolder(View itemView) {
            super(itemView);

            itemName = (TextView) itemView.findViewById(R.id.model_name);
            itemAuthor = (TextView) itemView.findViewById(R.id.model_author);
            itemType = (TextView) itemView.findViewById(R.id.model_Type);
            itemYear = (TextView) itemView.findViewById(R.id.model_year);
            itemAmount = (TextView) itemView.findViewById(R.id.model_amount);
            reserveButton = (TextView) itemView.findViewById(R.id.reserve_button);

        }
    }

}