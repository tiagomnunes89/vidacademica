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

public class StudentsAdapter extends RecyclerView.Adapter<StudentsAdapter.StudentsViewHolder> {

    List<UserEntity> students = new ArrayList<>();
    LayoutInflater layoutInflater;

    public StudentsAdapter(Context context, List<UserEntity> students) {
        this.students = students;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StudentsAdapter.StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_recycler_student, parent, false);
        return new StudentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsAdapter.StudentsViewHolder holder, int position) {
        UserEntity student = students.get(position);
        holder.StudentName.setText(student.getName());
        holder.dateOfBirth.setText(student.getDateOfBirth());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class StudentsViewHolder extends RecyclerView.ViewHolder {

        TextView StudentName;
        TextView dateOfBirth;

        public StudentsViewHolder(@NonNull View itemView) {
            super(itemView);
            StudentName = itemView.findViewById(R.id.item_student_name);
            dateOfBirth = itemView.findViewById(R.id.item_student_date_of_birth);
        }
    }
}
