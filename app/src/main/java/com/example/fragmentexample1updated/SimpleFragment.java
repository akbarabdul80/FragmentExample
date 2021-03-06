package com.example.fragmentexample1updated;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class SimpleFragment extends Fragment {

    private static final int YES = 0;
    private static final int NO = 1;
    private static int NONE = 2;

    private int mCurrentChoice = NONE;
    private OnFragmentInteractionListener mListener;

    private static final String CHOICE_PARAM = "choice-param";

    interface OnFragmentInteractionListener {
        void onRadioButtonChoiceChecked(int choice);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(getResources().getString(R.string.exception_message));
        }
    }


    public SimpleFragment() {
    }

    public static SimpleFragment newInstance() {
        SimpleFragment fragment = new SimpleFragment();
        return fragment;
    }

    public static SimpleFragment newInstance(int choice) {
        SimpleFragment fragment = new SimpleFragment();
        Bundle args = new Bundle();
        args.putInt(CHOICE_PARAM, choice);
        fragment.setArguments(args);

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

        if (getArguments() != null && getArguments().containsKey(CHOICE_PARAM)) {
            mCurrentChoice = getArguments().getInt(CHOICE_PARAM);
            if (mCurrentChoice != NONE) {
                radioGroup.check(radioGroup.getChildAt(mCurrentChoice).getId());
                switch (mCurrentChoice) {
                    case YES:
                        articleQuestionTextView.setText(R.string.yes_message);
                        break;
                    case NO:
                        articleQuestionTextView.setText(R.string.no_message);
                        break;
                }
            }
        }

        radioGroup.setOnCheckedChangeListener((radioGroup1, i) -> {
            RadioButton btn = radioGroup1.findViewById(i);
            int selectedIndex = radioGroup1.indexOfChild(btn);

            switch (selectedIndex) {
                case YES:
                    articleQuestionTextView.setText(R.string.yes_message);
                    mCurrentChoice = YES;
                    mListener.onRadioButtonChoiceChecked(YES);
                    break;
                case NO:
                    articleQuestionTextView.setText(R.string.no_message);
                    mCurrentChoice = NO;
                    mListener.onRadioButtonChoiceChecked(NO);
                    break;
                default:
                    mCurrentChoice = NONE;
                    mListener.onRadioButtonChoiceChecked(NONE);
                    break;
            }
        });

        return view;
    }
}