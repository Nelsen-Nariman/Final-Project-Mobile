package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {

    TextView createAccountText;
    Button login, backBtn;
    String email, password;
    EditText emailInput, passInput;
    SharedPreferences sp;
    View.OnClickListener toRegister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, new RegisterFragment())
                    .addToBackStack(null)
                    .commit();
        }
    };

    View.OnClickListener back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getActivity().onBackPressed();
        }
    };



    private FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        createAccountText = view.findViewById(R.id.create_account_et);
        createAccountText.setOnClickListener(toRegister);
        backBtn = view.findViewById(R.id.back_btn1);
        login = view.findViewById(R.id.confirm_btn);
        emailInput = view.findViewById(R.id.email_et);
        passInput = view.findViewById(R.id.password_et);
        backBtn.setOnClickListener(back);
        sp = requireActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);

//        Data data = getIntent().getParcelableExtra("data");
        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailInput.getText().toString();
                password = passInput.getText().toString();
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(requireActivity(), "Login Success", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("name", task.getResult().getUser().getDisplayName());
                                    editor.putString("email", task.getResult().getUser().getEmail());
                                    editor.apply();

                                    Intent toMain = new Intent(requireActivity(), MainActivity.class);
                                    startActivity(toMain);
                                }
                                else Toast.makeText(requireActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}