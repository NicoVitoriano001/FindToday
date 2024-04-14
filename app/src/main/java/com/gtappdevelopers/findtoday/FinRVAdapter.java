package com.gtappdevelopers.findtoday;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class FinRVAdapter extends ListAdapter<FinModal, FinRVAdapter.ViewHolder> {
    //creating a variable for on item click listner.
    private OnItemClickListener listener;

    //creating a constructor class for our adapter class.
    FinRVAdapter() {
        super(DIFF_CALLBACK);
    }

    //creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<FinModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<FinModal>() {
        @Override
        public boolean areItemsTheSame(FinModal oldItem, FinModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(FinModal oldItem, FinModal newItem) {
            //below line is to check the course name, description and course duration.
            return  //oldItem.getValorDesp() == newItem.getValorDesp() &&
                    oldItem.getValorDesp().equals(newItem.getValorDesp()) &&
                    oldItem.getTipoDesp().equals(newItem.getTipoDesp()) &&
                    oldItem.getFontDesp().equals(newItem.getFontDesp()) &&
                    oldItem.getDespDescr().equals(newItem.getDespDescr()) &&
                    oldItem.getDataDesp().equals(newItem.getDataDesp());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //below line is use to inflate our layout file for each item of our recycler view.
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fin_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //below line of code is use to set data to each item of our recycler view.
        FinModal model = getDespAt(position);
        //holder.valorDespTV.setText(String.valueOf(model.getValorDesp()));
        holder.valorDespTV.setText(model.getValorDesp());
        holder.tipoDespTV.setText(model.getTipoDesp());
        holder.fontDespTV.setText(model.getFontDesp());
        holder.despDescrTV.setText(model.getDespDescr());
        holder.dataDespTV.setText(model.getDataDesp());
    }

    //creating a method to get course modal for a specific position.
    public FinModal getDespAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //view holder class to create a variable for each view.
        TextView valorDespTV, tipoDespTV, fontDespTV, despDescrTV, dataDespTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initializing each view of our recycler view.
            valorDespTV = itemView.findViewById(R.id.idTVValorDesp);
            tipoDespTV = itemView.findViewById(R.id.idTVTipoDesp);
            fontDespTV = itemView.findViewById(R.id.idTVFontDesp);
            despDescrTV = itemView.findViewById(R.id.idTVDespDescr);
            dataDespTV = itemView.findViewById(R.id.idTVdataDesp);
            //adding on click listner for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //inside on click listner we are passing position to our item of recycler view.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(FinModal model);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}


