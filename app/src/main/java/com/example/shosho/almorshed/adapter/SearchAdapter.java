package com.example.shosho.almorshed.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shosho.almorshed.R;
import com.example.shosho.almorshed.View.Saredata;
import com.example.shosho.almorshed.model.Quran;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private List<Quran> quranList;
     /*Saredata saredata;*/

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
   /* public void onclicks(Saredata saredata){

        this.saredata=saredata;
    }*/
    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, final int position) {
        holder.sourt.setText( quranList.get( position ).getSouret() );
        holder.meaning.setText( quranList.get( position ).getWord1() +" _ "+quranList.get( position ).getMeaning() );
        holder.part.setText(" ( " + quranList.get( position ).getPart() + " ) " );

        holder.share.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               /* Quran quran=new Quran(  );
                quran.setWord1( quranList.get( position ).getWord1() );
                quran.setMeaning( quranList.get( position ).getMeaning() );
                quran.setSouret( quranList.get( position ).getSouret() );
                quran.setPart( quranList.get( position ).getPart() );
                saredata.listquran( quran );*/
              String message = "كلمة "+quranList.get( position ).getWord1()+" ظهرت في: "+ quranList.get( position ).getSouret()+" "+quranList.get( position ).getPart()+ " ومعناها: "+quranList.get( position ).getMeaning();
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_TEXT, message);
                context.startActivity(Intent.createChooser(share, "نشر "+ quranList.get( position ).getWord2()+" الي "));

               /* Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "http://www.google.com");
               context.startActivity(Intent.createChooser(sharingIntent, "Share via"));

*/

            }
        } );



    }

    @Override
    public int getItemCount() {
        return quranList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView word1,meaning,sourt,part;
        private ImageView  share;
        public ViewHolder(View itemView) {
            super( itemView );
            meaning=itemView.findViewById( R.id.row_search_meaning );
            sourt=itemView.findViewById( R.id.row_search_sourt );
            part=itemView.findViewById( R.id.row_search_part );
            share=itemView.findViewById( R.id.row_search_icon_share );
        }
    }
}
