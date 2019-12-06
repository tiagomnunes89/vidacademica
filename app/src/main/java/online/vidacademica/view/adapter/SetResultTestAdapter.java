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

public class SetResultTestAdapter extends RecyclerView.Adapter<SetResultTestAdapter.SetResultTestViewHolder> {

    List<UserEntity> students = new ArrayList<>();
    LayoutInflater layoutInflater;

    public SetResultTestAdapter (Context context, List<UserEntity> students){
        this.students = students;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SetResultTestAdapter.SetResultTestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_set_result_test, parent, false);
        return new SetResultTestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SetResultTestViewHolder holder, int position) {
        UserEntity userEntity = students.get(position);
        holder.name_student.setText(userEntity.getName());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class SetResultTestViewHolder extends RecyclerView.ViewHolder {
        TextView name_student;

        public SetResultTestViewHolder(@NonNull View itemView) {
            super(itemView);
            name_student = itemView.findViewById(R.id.name_student);
        }
    }

}
