package hfad.com.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<String> urls = new ArrayList<>();


    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.photo);
        }

        void bindImage(String url) {
            Picasso.with(context).load(url).into(imageView);
        }
    }

    PhotoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public PhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String url = this.urls.get(position);
        holder.bindImage(url);
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }


    void setData(ArrayList<String> data) {
        urls = data;
        notifyDataSetChanged();
    }

    void clearData() {
        urls.clear();
        notifyDataSetChanged();
    }
}