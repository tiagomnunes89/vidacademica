package online.vidacademica.view.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import online.vidacademica.entities.SubjectDTO;

public class SubjectsAdapter extends RecyclerView.Adapter<SubjectsAdapter.SubjectViewHolder> {
        List<SubjectDTO> subjects = new ArrayList<>();

    public SubjectsAdapter(List<SubjectDTO> subjects ) {
        this.subjects = subjects;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder {
        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
