package com.planetofheroes.amarioforester.poh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

public class CalculatorFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(getActivity(), CalculatorActivity.class);
        startActivity(intent);
    }
}
