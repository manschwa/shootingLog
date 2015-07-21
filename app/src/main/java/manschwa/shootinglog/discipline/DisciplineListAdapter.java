package manschwa.shootinglog.discipline;

import android.os.Handler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.List;

import manschwa.shootinglog.R;

/**
 * Created by Manuel on 13.07.15.
 */



public class DisciplineListAdapter extends RecyclerView.Adapter<DisciplineListAdapter.DisciplineViewHolder> implements View.OnClickListener {

    private List<Discipline> disciplines;
    private OnItemClickListener onItemClickListener;

    public DisciplineListAdapter(List<Discipline> disciplines) {

        this.disciplines = disciplines;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override public DisciplineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.discipline_recycler_view, parent, false);
        v.setOnClickListener(this);
        return new DisciplineViewHolder(v);
    }

    @Override public void onBindViewHolder(DisciplineViewHolder holder, int position) {
        Discipline discipline = disciplines.get(position);
        holder.disciplineName.setText(discipline.getName());
        // TODO Yard/Meters problem / better String appending
        holder.disciplineShots.setText(discipline.getTotalShots() + " Shots");
        holder.disciplineDistance.setText(discipline.getDistanceInMeters() + " Meters");
        holder.disciplineTime.setText(discipline.getTimeInMinutes() + " Minutes");
//        holder.image.setImageBitmap(null);
//        Picasso.with(holder.image.getContext()).load(item.getImage()).into(holder.image);
        holder.itemView.setTag(discipline);
    }

    @Override public int getItemCount() {

        return disciplines.size();
    }

    @Override public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    onItemClickListener.onItemClick(v, (Discipline) v.getTag());
                }
            }, 200);
        }
    }

    protected static class DisciplineViewHolder extends RecyclerView.ViewHolder {
        public TextView disciplineName;
        public TextView disciplineShots;
        public TextView disciplineDistance;
        public TextView disciplineTime;


        public DisciplineViewHolder(View itemView) {
            super(itemView);
            disciplineName = (TextView) itemView.findViewById(R.id.discipline_recycler_name);
            disciplineShots = (TextView) itemView.findViewById(R.id.discipline_recycler_shots);
            disciplineDistance = (TextView) itemView.findViewById(R.id.discipline_recycler_distance);
            disciplineTime = (TextView) itemView.findViewById(R.id.discipline_recycler_time);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, Discipline discipline);

    }
}