package com.example.mobile_hw7_ex1.ui.slideshow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.mobile_hw7_ex1.R;
import com.google.android.material.navigation.NavigationView;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel loginViewModel;
    View dialogView;
    ImageView headerImageView;
    TextView textViewName_h, textViewEmail_h;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loginViewModel = new ViewModelProvider(this).get(SlideshowViewModel.class);

        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_b);

        loginViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });

        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
        final View header = navigationView.getHeaderView(0);
        final Menu menu = navigationView.getMenu();
        final MenuItem menuitem = menu.findItem(R.id.nav_slideshow);

        headerImageView = header.findViewById(R.id.imageView);
        textViewName_h = header.findViewById(R.id.textView_name_h);
        textViewEmail_h = header.findViewById(R.id.textView_email_h);

        if (menuitem.getTitle().equals((String) "Login")) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());

            dlg.setTitle("사용자 입력");
            dlg.setIcon(R.mipmap.ic_launcher);
            dialogView = View.inflate(getActivity(), R.layout.dialog_login, null);
            dlg.setView(dialogView);

            final String[] animalArray = new String[]{"dog", "cat", "horse"};
            final String[] animalName = new String[1];

            dlg.setSingleChoiceItems(animalArray, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    animalName[0] = animalArray[which];
                }
            });

            dlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText name = dialogView.findViewById(R.id.dlgEditName);
                    EditText email = dialogView.findViewById(R.id.dlgEditEmail);

                    textViewName_h.setText(name.getText().toString());
                    textViewEmail_h.setText(email.getText().toString());

                    if (animalName[0].equals("dog"))
                        headerImageView.setImageResource(R.drawable.dog);
                    if (animalName[0].equals("cat"))
                        headerImageView.setImageResource(R.drawable.cat);
                    if (animalName[0].equals("horse"))
                        headerImageView.setImageResource(R.drawable.horse1);

                    Toast.makeText(getContext(), "로그인", Toast.LENGTH_SHORT).show();
                    menuitem.setTitle("Logout");

                }
            });
            dlg.setNegativeButton("Cancel", null);
            dlg.show();

        }
        else{
            textViewName_h.setText(getString(R.string.nav_header_title));
            textViewEmail_h.setText(getString(R.string.nav_header_subtitle));

            Toast.makeText(getContext(),"로그아웃",Toast.LENGTH_SHORT).show();

            menuitem.setTitle("Login");
            headerImageView.setImageResource(R.mipmap.ic_launcher);
        }
        return root;
    }
}