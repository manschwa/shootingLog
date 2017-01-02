package manschwa.shootinglog.location;

import android.os.Handler;

        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.LayoutInflater;
        import android.widget.TextView;

        import java.util.List;

        import manschwa.shootinglog.R;

/**
 * Created by root on 02.01.17.
 */
public class LocationListAdapter extends RecyclerView.Adapter<LocationListAdapter.LocationViewHolder> implements View.OnClickListener {

    private List<Location> locations;
    private OnItemClickListener onItemClickListener;

    public LocationListAdapter(List<Location> locations) {
        this.locations = locations;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_recycler_view, parent, false);
        v.setOnClickListener(this);
        return new LocationViewHolder(v);
    }

    @Override public void onBindViewHolder(LocationViewHolder holder, int position) {
        Location location = this.locations.get(position);
        holder.locationName.setText(location.getName());
        holder.itemView.setTag(location);
    }

    @Override public int getItemCount() {
        return this.locations.size();
    }

    @Override public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    onItemClickListener.onItemClick(v, (Location) v.getTag());
                }
            }, 200);
        }
    }

    protected static class LocationViewHolder extends RecyclerView.ViewHolder {
        public TextView locationName;

        public LocationViewHolder(View itemView) {
            super(itemView);
            locationName = (TextView) itemView.findViewById(R.id.location_recycler_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Location location);

    }
}
