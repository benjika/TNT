package com.example.luput.tnt;


import android.app.Dialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.joinersa.oooalertdialog.Animation;
import br.com.joinersa.oooalertdialog.OoOAlertDialog;


public class LoginFragment extends Fragment {

    TextInputEditText UserName;
    TextInputEditText Password;
    Button LoginBTN;
    Button ForgotpassBTN;
    String Email;
    String password;
    Dialog forgotPasswordDialog;
    DataSnapshot DBforlogin;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    DatabaseReference DBref = FirebaseDatabase.getInstance().getReference();
    Utils utils = new Utils();


    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        UserName = view.findViewById(R.id.Login_email);
        Password = view.findViewById(R.id.Login_password);
        LoginBTN = view.findViewById(R.id.btn_sign_in);
        ForgotpassBTN = view.findViewById(R.id.btn_forgot_password);

        getDB();

        ForgotpassBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPasswordDialog(view);
            }
        });
        //region Login Logic
        LoginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Email = UserName.getText().toString();
                password = Password.getText().toString();
                if(checkInput(Email,password)) {
                    final ProgressDialog dialog = new ProgressDialog(getActivity()); // this = YourActivity
                    dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    dialog.setMessage("Loading. Please wait...");
                    dialog.setIndeterminate(true);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.show();
                    mAuth.signInWithEmailAndPassword(Email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (DBforlogin != null) {
                                    String userUID = mAuth.getCurrentUser().getUid();
                                    if (DBforlogin.child(utils.TRAINEE_BRANCH).child(userUID).exists()) {
                                        Intent intent = new Intent(getActivity(), afterLoginActiviry.class);
                                        intent.putExtra("isCoach", false);
                                        intent.putExtra("Name",DBforlogin.child(utils.TRAINEE_BRANCH).child(userUID).getValue(Trainee.class).getFirstName());
                                        dialog.cancel();
                                        startActivity(intent);
                                        getActivity().finish();
                                    } else {
                                        Intent intent = new Intent(getActivity(), afterLoginActiviry.class);
                                        intent.putExtra("isCoach", true);
                                        intent.putExtra("Name",DBforlogin.child(utils.COACH_BRANCH).child(userUID).getValue(Coach.class).getFirstName());
                                        dialog.cancel();
                                        startActivity(intent);
                                        getActivity().finish();
                                    }
                                } else {
                                    getDB();
                                    utils.makeSimplePopup("Oops", "Something happend plz try again", getActivity());
                                    dialog.cancel();
                                }
                            } else {
                                utils.makeSimplePopup("Wrong Email or Password", "Please Make Sure you enter the right email and password", getActivity());
                                dialog.cancel();
                            }
                        }
                    });
                }
                else {
                    utils.makeSimplePopup("Error in input","Please make sure you fill all the input fields",getActivity());
                }
            }
        });
        //endregion
        return view;
    }

    private boolean checkInput(String email, String password) {
        boolean isValid = true;

        if(email.isEmpty() || password.isEmpty()){
            isValid = false;
        }

        return isValid;
    }

    private void getDB() {
        DBref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DBforlogin = dataSnapshot;
                Log.d("DB", "onDataChange: now has DB");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void forgotPasswordDialog(View view){
        forgotPasswordDialog = new Dialog(view.getContext());
        forgotPasswordDialog.setContentView(R.layout.forgot_password_dialog);
        forgotPasswordDialog.setTitle("Forgot Password");

        Button cancelBTN = forgotPasswordDialog.findViewById(R.id.forgot_password_cancel);
        Button sendBTN = forgotPasswordDialog.findViewById(R.id.forgot_password_send);
        final EditText EmailToSend = forgotPasswordDialog.findViewById(R.id.Forgot_password_email);

        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotPasswordDialog.cancel();
            }
        });

        sendBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EmailToSend.getText().toString();
                if(!email.isEmpty() && email != null){
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            new OoOAlertDialog.Builder(getActivity())
                                    .setTitle("Reset Email Send")
                                    .setMessage("Please check your Email adress")
                                    .setAnimation(Animation.POP)
                                    .setPositiveButton("Ok",null)
                                    .build();
                        }
                    });
                    forgotPasswordDialog.cancel();
                }
                else {
                    new OoOAlertDialog.Builder(getActivity())
                            .setTitle("Wrong Input")
                            .setMessage("Must enter valid email")
                            .setAnimation(Animation.POP)
                            .setPositiveButton("Ok",null)
                            .build();
                }
            }
        });
        forgotPasswordDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        forgotPasswordDialog.show();
    }
}
