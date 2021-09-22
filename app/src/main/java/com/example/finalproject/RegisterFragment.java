package com.example.finalproject;

import android.content.Context;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;


public class RegisterFragment extends Fragment {

    Button backBtn;
    Button register;
    String name, email, password, conpassword;
    EditText nameInput, emailInput, passInput, conpasswordInput;
    SharedPreferences sp;
    View.OnClickListener back = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getParentFragmentManager().popBackStack();
        }
    };

    private FirebaseAuth auth;

    View.OnClickListener signup = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            email = emailInput.getText().toString();
            password = passInput.getText().toString();
            name = nameInput.getText().toString();
            conpassword = conpasswordInput.getText().toString();

            if(name.length()>=5 && password.length()>0 && email.length()>0
            && conpassword.equals(password) && email.contains("@") && email.endsWith(".com") ){
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    UserProfileChangeRequest InputName = new UserProfileChangeRequest.Builder().setDisplayName(name).build();

                                    task.getResult().getUser().updateProfile(InputName);
                                    Toast.makeText(requireActivity(), "Register Success", Toast.LENGTH_SHORT).show();
                                    SharedPreferences.Editor editor = sp.edit();
                                    editor.putString("name", task.getResult().getUser().getDisplayName());
                                    editor.putString("email", task.getResult().getUser().getEmail());
                                    editor.apply();
                                    getParentFragmentManager().popBackStack();
                                }
                                else Toast.makeText(requireActivity(), "Register Failed", Toast.LENGTH_SHORT).show();

                            }
                        });
            }else {
                Toast.makeText(requireActivity(), "Input yang bener ya", Toast.LENGTH_SHORT).show();
            }


        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        backBtn = view.findViewById(R.id.back_btn2);
        backBtn.setOnClickListener(back);

        auth = FirebaseAuth.getInstance();
        register = view.findViewById(R.id.create_btn);
        emailInput = view.findViewById(R.id.email_et);
        passInput = view.findViewById(R.id.password_et);
        nameInput = view.findViewById(R.id.name_et);
        conpasswordInput = view.findViewById(R.id.con_password);
        sp = requireActivity().getSharedPreferences("sp", Context.MODE_PRIVATE);

        register.setOnClickListener(signup);
    }
}