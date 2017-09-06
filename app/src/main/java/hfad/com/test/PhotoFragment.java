package hfad.com.test;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhotoFragment extends Fragment {
    public static final String TAG = "photo";
    private RecyclerView recyclerView;
    private GridLayoutManager layoutManager;
    private PhotoAdapter photoAdapter;
    private Api.ImagesService imagesApi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imagesApi = Api.getClient().create(Api.ImagesService.class);

        imagesApi.getImagesLinks().enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                imagesUrlsReceived(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        setRetainInstance(true);

        createGrid();
        return view;
    }

    private void createGrid() {
        recyclerView.setHasFixedSize(true);
        final int columns = getResources().getInteger(R.integer.columns);
        layoutManager = new GridLayoutManager(getActivity(), columns);
        recyclerView.setLayoutManager(layoutManager);
        photoAdapter = new PhotoAdapter(getActivity());
        recyclerView.setAdapter(photoAdapter);
    }

    private void imagesUrlsReceived(ArrayList<String> imagesUrls) {
        photoAdapter.setData(imagesUrls);
        recyclerView.setAdapter(photoAdapter);
        photoAdapter.notifyDataSetChanged();
    }
}
