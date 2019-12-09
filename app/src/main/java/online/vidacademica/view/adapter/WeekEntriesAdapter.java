package online.vidacademica.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import online.vidacademica.R;
import online.vidacademica.entities.WeekEntryEntity;
import online.vidacademica.presentation.SingletonClassEntity;
import online.vidacademica.presentation.WeekEntryPresentation;
import online.vidacademica.presentation.mapper.WeekEntryMapper;

public class WeekEntriesAdapter extends RecyclerView.Adapter<WeekEntriesAdapter.ViewHolder> {
    List<WeekEntryEntity> weekEntryEntities;
    LayoutInflater layoutInflater;

    public WeekEntriesAdapter(Context context, List<WeekEntryEntity> weekEntryEntities) {
        this.weekEntryEntities = weekEntryEntities;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_class_schedules, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeekEntryPresentation entryPresentation = WeekEntryMapper.get().convertTo(weekEntryEntities.get(position));
        holder.dayOfWeek.setText(entryPresentation.getDay());
        holder.startTime.setText(entryPresentation.getStartTime());
        holder.endTime.setText(entryPresentation.getEndTime());

    }

    @Override
    public int getItemCount() {
        return weekEntryEntities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dayOfWeek;
        TextInputEditText startTime;
        TextInputEditText endTime;
        ImageView close;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.text_view_day_name);
            startTime = itemView.findViewById(R.id.text_input_item_initial);
            endTime = itemView.findViewById(R.id.text_input_item_final);
            close = itemView.findViewById(R.id.close_item_icon);
            close.setOnClickListener(v -> {
                weekEntryEntities.remove(itemView);
                notifyDataSetChanged();
                if(SingletonClassEntity.INSTANCE.getClassEntity() != null)
                    SingletonClassEntity.INSTANCE.getClassEntity().getWeekEntries().remove(getLayoutPosition());
            });
        }
    }
}