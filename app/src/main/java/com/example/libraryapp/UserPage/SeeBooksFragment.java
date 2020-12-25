package com.example.libraryapp.UserPage;

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
import com.example.libraryapp.R;
import com.example.libraryapp.Requests.GetAllItems;

import java.util.ArrayList;


public class SeeBooksFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_see_books, container, false);
        mDefineview(view);
        return view;
    }

    public void mDefineview(View view) {

        recyclerView = view.findViewById(R.id.Items_RecyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        GetAllItems.GetItems(getActivity());
        GetAllItems allItems = new GetAllItems();
        allItems.setListener(new GetAllItems.Listener() {
            @Override
            public void sendResponse(Object meta) {

                ArrayList<Items> Allitems = (ArrayList<Items>) meta;

                AdapterItemsList adapter = new AdapterItemsList(getContext(), Allitems);
                recyclerView.setAdapter(adapter);

                Log.wtf("AllItemsFormFragment", " " + Allitems.get(0).name);

            }
        });

    }
}

class AdapterItemsList extends RecyclerView.Adapter<AdapterItemsList.ItemsViewHolder> {

    private final LayoutInflater mInflater;
    public ArrayList<Items> mItems = new ArrayList<>();
    private Context mContext;

    public AdapterItemsList(Context context, ArrayList<Items> items) {
//        mItems.addAll(items);
        mItems = items;
        mContext = context;
        this.mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public AdapterItemsList.ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = mInflater.inflate(R.layout.model_book, parent, false);

        return new ItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterItemsList.ItemsViewHolder holder, int position) {

        Items selectedItem = mItems.get(position);

        if (selectedItem.name.length() > 15) {
            holder.itemName.setText(selectedItem.name.substring(0,15));
        }else{
            holder.itemName.setText(selectedItem.name);

        }
        holder.itemAuthor.setText(selectedItem.author);
        holder.itemType.setText(selectedItem.type);
        holder.itemYear.setText("Year: " + selectedItem.year);
        holder.itemAmount.setText("Amount: " + selectedItem.amount);

        holder.reserveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "Item Reserved", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    static class ItemsViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView itemAuthor;
        TextView itemType;
        TextView itemYear;
        TextView itemAmount;
        TextView reserveButton;

        public ItemsViewHolder(View itemView) {
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