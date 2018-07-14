package com.example.luput.tnt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import static android.app.Activity.RESULT_OK;

public class SettingFragment extends Fragment {

    Button SaveChangesBTN;
    EditText LastNameEditText;
    EditText PhoneEditText;
    EditText PasswordEditText;
    ImageButton ProfilePicImg;
    String NewLastName;
    String NewPhoneNumber;
    String NewPassword;
    String CurrentUID = FirebaseAuth.getInstance().getCurrentUser().getUid();
    DatabaseReference DB = FirebaseDatabase.getInstance().getReference();
    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,container,false);

        SaveChangesBTN = view.findViewById(R.id.Setting_Save_change_BTN);
        LastNameEditText = view.findViewById(R.id.Setting_New_LastName);
        PhoneEditText = view.findViewById(R.id.Setting_New_Phone);
        PasswordEditText = view.findViewById(R.id.Setting_New_Password);
        ProfilePicImg = view.findViewById(R.id.Setting_profile_pic);

        ProfilePicImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });

        SaveChangesBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewLastName = LastNameEditText.getText().toString();
                NewPhoneNumber = PhoneEditText.getText().toString();
                NewPassword = PasswordEditText.getText().toString();
                if(checkInput()){
                    UpdateInfo();
                }
            }
        });

        return view;

    }

    private void UpdateInfo() {

    }

    private boolean checkInput() {
        boolean isValid = true;

        if(NewLastName.isEmpty()){
            isValid = false;
        }
        if(NewPhoneNumber.isEmpty()){
            isValid = false;
        }
        if(NewPassword.isEmpty()){
            isValid = false;
        }
        if(filePath != null){
            isValid = false;
        }


        return isValid;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                ProfilePicImg.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
}
