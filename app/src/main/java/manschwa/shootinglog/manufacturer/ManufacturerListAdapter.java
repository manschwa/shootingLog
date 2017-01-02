package manschwa.shootinglog.manufacturer;

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

public class ManufacturerListAdapter extends RecyclerView.Adapter<ManufacturerListAdapter.ManufacturerViewHolder> implements View.OnClickListener {

    private List<Manufacturer> manufacturers;
    private OnItemClickListener onItemClickListener;

    public ManufacturerListAdapter(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override public ManufacturerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.manufacturer_recycler_view, parent, false);
        v.setOnClickListener(this);
        return new ManufacturerViewHolder(v);
    }

    @Override public void onBindViewHolder(ManufacturerViewHolder holder, int position) {
        Manufacturer manufacturer = this.manufacturers.get(position);
        holder.manufacturerName.setText(manufacturer.getName());
        holder.itemView.setTag(manufacturer);
    }

    @Override public int getItemCount() {
        return this.manufacturers.size();
    }

    @Override public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override public void run() {
                    onItemClickListener.onItemClick(v, (Manufacturer) v.getTag());
                }
            }, 200);
        }
    }

    protected static class ManufacturerViewHolder extends RecyclerView.ViewHolder {
        public TextView manufacturerName;

        public ManufacturerViewHolder(View itemView) {
            super(itemView);
            manufacturerName = (TextView) itemView.findViewById(R.id.manufacturer_recycler_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Manufacturer manufacturer);

    }
}
