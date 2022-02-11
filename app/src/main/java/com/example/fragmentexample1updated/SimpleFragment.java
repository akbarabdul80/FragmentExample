package com.example.fragmentexample1updated;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SimpleFragment extends Fragment {

    private static final int YES = 0;
    private static final int NO = 1;

    public SimpleFragment() {
    }

    public static SimpleFragment newInstance() {
        SimpleFragment fragment = new SimpleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_simple, container, false);

        RadioGroup radioGroup = view.findViewById(R.id.radio_group);
        TextView articleQuestionTextView = view.findViewById(R.id.question_textview);

        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            RadioButton btn = radioGroup1.findViewById(i);
            int selectedIndex = radioGroup1.indexOfChild(btn);

            switch (selectedIndex) {
                case YES:
                    articleQuestionTextView.setText(R.string.yes_message);
                    break;
                case NO:
                    articleQuestionTextView.setText(R.string.no_message);
                    break;
                default:
                    break;
            }
        });

        return view;
    }
}