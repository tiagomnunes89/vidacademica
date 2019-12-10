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
import java.util.Locale;

import online.vidacademica.R;
import online.vidacademica.entities.WeekDayDTO;
import online.vidacademica.view.enums.CrudEnum;

public class WeekDayAdapter extends RecyclerView.Adapter<WeekDayAdapter.WeekDayViewHolder> {
    private static final String TAG = CoursesAdapter.class.getSimpleName();

    private Context context;

    private List<WeekDayDTO> weekDayDTOList = new ArrayList<>();

    private LayoutInflater layoutInflater;

    public static final String CRUD_TYPE = "CRUD_TYPE";
    private static final CrudEnum UPDATE = CrudEnum.UPDATE;

    public static final String SELECTED_OBJECT = "SELECTED_OBJECT";

    public WeekDayAdapter(Context context, List<WeekDayDTO> weekDayDTOList) {
        this.context = context;
        this.weekDayDTOList = weekDayDTOList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WeekDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_recycler_subject_student, parent, false);
        return new WeekDayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekDayViewHolder holder, int position) {
        WeekDayDTO weekDay = weekDayDTOList.get(position);
        Locale.setDefault(Locale.US);
        holder.titulo_materia.setText(weekDay.getSubjectTitle());
        holder.diaSemana.setText(weekDay.getWeekDay());
        holder.horario.setText(weekDay.getTimeStart()+" - "+weekDay.getTimeEnd());
    }

    @Override
    public int getItemCount() {
        return weekDayDTOList.size();
    }

    public class WeekDayViewHolder extends RecyclerView.ViewHolder {

        TextView titulo_materia;
        TextView diaSemana;
        TextView horario;

        public WeekDayViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo_materia = itemView.findViewById(R.id.item_titulo_materia);
            diaSemana = itemView.findViewById(R.id.item_dia_semana);
            horario = itemView.findViewById(R.id.item_horarioInicioEFim);
        }
    }
}
