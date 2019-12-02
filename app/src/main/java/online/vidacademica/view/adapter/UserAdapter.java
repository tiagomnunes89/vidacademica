package online.vidacademica.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.R;
import online.vidacademica.entities.UserEntity;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersViewHolder> {
    List<UserEntity> users = new ArrayList<>();
    LayoutInflater layoutInflater;

    public UserAdapter(Context context, List<UserEntity> users) {
        this.users = users;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_recycler_student, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        UserEntity userEntity = users.get(position);
        holder.titulo.setText(userEntity.getName());

    }
    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {
        //item_titulo_materia
        TextView titulo;

        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_student_name);
        }
    }
}
