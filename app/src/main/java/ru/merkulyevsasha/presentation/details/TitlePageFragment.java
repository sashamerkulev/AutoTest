package ru.merkulyevsasha.presentation.details;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import ru.merkulyevsasha.model.TitleItem;
import ru.merkulyevsasha.R;


public class TitlePageFragment extends Fragment {

    public static final String ARGUMENT_IMAGE_URL = "arg_image_url";

    private String imgUrl;

    public static TitlePageFragment newInstance(TitleItem item) {
        TitlePageFragment pageFragment = new TitlePageFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_IMAGE_URL, item.getImg());
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgUrl = getArguments().getString(ARGUMENT_IMAGE_URL);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, null);

        ImageView image = (ImageView) view.findViewById(R.id.imageview);
        Picasso.with(getActivity()).load(imgUrl).into(image);

        return view;
    }
}
