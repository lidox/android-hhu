package de.hhu.a41fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class DetailsFragment extends Fragment {
    public static DetailsFragment newInstance(int index) {
        DetailsFragment f = new DetailsFragment();

        // Create arguments bundle
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    // Retrieve the index of the currently shown work group
    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public void onAttach(Context context) {
        Log.i(DetailsFragment.class.getSimpleName(), "onAttach");
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(DetailsFragment.class.getSimpleName(), "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(DetailsFragment.class.getSimpleName(), "onCreateView");
        // Create our WebView layout
        View layout = inflater.inflate(R.layout.fragment_details, container, false);
        WebView webView = (WebView) layout.findViewById(R.id.web_view);
        // Load web page
        webView.loadUrl(WorkGroups.HOMEPAGES[getShownIndex()]);
        return layout;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Log.i(DetailsFragment.class.getSimpleName(), "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i(DetailsFragment.class.getSimpleName(), "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        Log.i(DetailsFragment.class.getSimpleName(), "onViewStateRestored");
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.i(DetailsFragment.class.getSimpleName(), "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.i(DetailsFragment.class.getSimpleName(), "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.i(DetailsFragment.class.getSimpleName(), "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.i(DetailsFragment.class.getSimpleName(), "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.i(DetailsFragment.class.getSimpleName(), "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.i(DetailsFragment.class.getSimpleName(), "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.i(DetailsFragment.class.getSimpleName(), "onDetach");
        super.onDetach();
    }

    // use startActivityForResult(...) and not getActivity().startActivityForResult(...)
    // public void clickEventOrSomething() {
    //     getActivity().startActivityForResult(...); // will only call onActivityResult(...) in Activity
    //     startActivityForResult(...); // will call onActivityResult(...) in Activity and because of the super-call also onActivityResult(...) here in Fragment
    // }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // use it here
    }
}
