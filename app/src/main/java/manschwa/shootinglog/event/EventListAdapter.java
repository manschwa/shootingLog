package manschwa.shootinglog.event;

import android.os.Handler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;

import java.util.List;

import manschwa.shootinglog.R;
/**
 * Created by root on 15.12.16.
 */

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder> implements View.OnClickListener {

    private List<Event> events;
    private OnItemClickListener onItemClickListener;

    public EventListAdapter(List<Event> events) {
        this.events = events;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_recycler_view, parent, false);
        v.setOnClickListener(this);
        return new EventViewHolder(v);
    }

    @Override public void onBindViewHolder(EventViewHolder holder, int position) {
        Event event = this.events.get(position);
        holder.eventName.setText(event.getName());
        holder.itemView.setTag(event);
    }

    @Override public int getItemCount() {
        return this.events.size();
    }

    @Override public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    onItemClickListener.onItemClick(v, (Event) v.getTag());
                }
            }, 200);
        }
    }

    protected static class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView eventName;

        public EventViewHolder(View itemView) {
            super(itemView);
            eventName = (TextView) itemView.findViewById(R.id.event_recycler_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Event event);

    }
}
