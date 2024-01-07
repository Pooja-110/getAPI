package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private ArrayList<User> userList;

    public UserAdapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (userList != null && position < userList.size()) {
            User user = userList.get(position);
            holder.bind(user);
        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView idTextView, emailTextView, firstNameTextView, lastNameTextView;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.pro_id);
            emailTextView = itemView.findViewById(R.id.email);
            firstNameTextView = itemView.findViewById(R.id.fname);
            lastNameTextView = itemView.findViewById(R.id.lname);
        }

        public void bind(User user) {
            idTextView.setText(String.valueOf(user.getId()));
            emailTextView.setText(user.getEmail());
            firstNameTextView.setText(user.getFirst_name());
            lastNameTextView.setText(user.getLast_name());
        }
    }
}
