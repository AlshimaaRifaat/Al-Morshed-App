package com.example.shosho.almorshed.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shosho.almorshed.R;
import com.example.shosho.almorshed.model.Quran;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<Quran> quranList;


    public SearchAdapter(Context context, List<Quran> quranList) {
        this.context = context;
        this.quranList = quranList;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from( context ).inflate(R.layout.row_search,parent,false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, final int position) {
        holder.sourt.setText( quranList.get( position ).getSouret() );
        holder.meaning.setText( quranList.get( position ).getWord1() +" _ "+quranList.get( position ).getMeaning() );
        holder.part.setText(" ( " + quranList.get( position ).getPart() + " ) " );


    }

    @Override
    public int getItemCount() {
        return quranList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView word1,meaning,sourt,part;
        public ViewHolder(View itemView) {
            super( itemView );
            meaning=itemView.findViewById( R.id.row_search_meaning );
            sourt=itemView.findViewById( R.id.row_search_sourt );
            part=itemView.findViewById( R.id.row_search_part );
        }
    }
}
