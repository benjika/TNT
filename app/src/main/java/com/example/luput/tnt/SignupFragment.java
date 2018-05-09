package com.example.luput.tnt;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import java.util.Calendar;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment
        implements
        View.OnClickListener,
        View.OnFocusChangeListener,
        DatePickerDialog.OnDateSetListener,
        DateDialog.OnInputSelected {

    private static EditText etFirstName;
    private static EditText etLastName;
    private static EditText etEmail;
    private static EditText etPhone;
    private static EditText etCity;
    private static EditText etDateOfbirth;
    private static EditText etGender;
    private static ImageButton ibPhoto;
    private static Button btnTakePhoto;
    private static String[] Genders;
    private static ArrayAdapter<CharSequence> genderAdapter;
    private static int DateOfbirth_day;
    private static int DateOfbirth_month;
    private static int DateOfbirth_year;

    private static Button btnSignUp;
    private static RadioGroup radioTrainerOrTrainee;
    static View view;
    private static Integer REQUEST_CAMERA = 0, REQUEST_STORAGE = 1;


    public SignupFragment() {
        // Required empty public constructor
    }

    public static SignupFragment newInstance() {
        SignupFragment signupFragment = new SignupFragment();
        return signupFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_signup, container, false);

        // Inflate the layout for this fragment

        radioTrainerOrTrainee = (RadioGroup) view.findViewById(R.id.radio_trainerOrTrainee);
        etFirstName = (EditText) view.findViewById(R.id.signup_firstName);
        etLastName = (EditText) view.findViewById(R.id.signup_lastName);
        etGender = (EditText) view.findViewById(R.id.signup_gender);
        ibPhoto = (ImageButton) view.findViewById(R.id.signup_ibPhoto);
        btnTakePhoto = (Button) view.findViewById(R.id.signup_btnTakePhoto);
        //etEmail = (EditText) view.findViewById(R.id.signup_email);
        etPhone = (EditText) view.findViewById(R.id.signup_phone);
        etDateOfbirth = (EditText) view.findViewById(R.id.signup_dateOfBith);
        etCity = (EditText) view.findViewById(R.id.signup_city);

        etDateOfbirth.setOnFocusChangeListener(this);
        etGender.setOnFocusChangeListener(this);

        ibPhoto.setOnClickListener(this);
        btnTakePhoto.setOnClickListener(this);

        return view;
    }

    @Override
    public void onFocusChange(View view1, boolean b) {
        switch (view1.getId()) {
            case R.id.signup_dateOfBith:
                if (etDateOfbirth.hasFocus()) {
                    DialogFragment datePicker = new DateDialog();
                    datePicker.show(getFragmentManager(), "date picker");
                }
                return;
            case R.id.signup_gender:
                if (etGender.hasFocus()) {
                    Genders = getResources().getStringArray(R.array.genders);
                    final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                    mBuilder.setTitle(getResources().getString(R.string.select_gender))
                            .setSingleChoiceItems(Genders, -1, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    etGender.setText(Genders[i]);
                                    dialogInterface.dismiss();
                                }
                            });
                    mBuilder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
                    // AlertDialog mdialog = mBuilder.create();
                    //mdialog.show();
                }
                return;

        }
    }

    public void onClick(View view1) {
        switch (view1.getId()) {
            case (R.id.signup_btnTakePhoto):
            case (R.id.signup_ibPhoto):
                SelectImage();
                return;
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        super.onResume();
        EditText dateOfBirth = (EditText) view.findViewById(R.id.signup_dateOfBith);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        month++;

        String day_s = day > 9 ? "" + day : "0" + day;
        String month_s = month > 9 ? "" + month : "0" + month;

        dateOfBirth.setText(day_s + "/" + month_s + "/" + year);
    }

    @Override
    public void sendInput(DatePickerDialog datePickerDialog) {

    }

    private void SelectImage() {
        final String[] items = getResources().getStringArray(R.array.add_camera_arr);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getResources().getString(R.string.add_profile_pic));
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    if (ContextCompat.checkSelfPermission(getContext(), CAMERA)
                            == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, REQUEST_CAMERA);
                    } else {
                        requestPermission(CAMERA, REQUEST_CAMERA);
                    }
                } else {
                    if (ContextCompat.checkSelfPermission(getContext(),
                            READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(intent.createChooser(intent, getResources().getString(R.string.select_photo)), REQUEST_STORAGE);
                    } else {
                        requestPermission(READ_EXTERNAL_STORAGE, REQUEST_STORAGE);
                    }
                }
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();


    }

    private void requestPermission(final String permission, final Integer requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                permission)) {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getContext());
            alertBuilder.setCancelable(true);
            alertBuilder.setTitle(getResources().getString(R.string.permission_necessary));
            if (requestCode == REQUEST_CAMERA) {
                alertBuilder.setMessage(getResources().getString(R.string.camera_permission_necessary));
            } else if (requestCode == REQUEST_STORAGE) {
                alertBuilder.setMessage(getResources().getString(R.string.external_storage_permission_necessary));
            } else {
                alertBuilder.setMessage(getResources().getString(R.string.a_permission_necessary));
            }
            alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{permission}, requestCode);
                }
            });
            AlertDialog alert = alertBuilder.create();
            alert.show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{permission}, requestCode);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                final Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ibPhoto.setImageBitmap(bmp);
                btnTakePhoto.setText(getResources().getString(R.string.change_photo));
            } else if (requestCode == REQUEST_STORAGE) {

                Uri selectedImageUri = data.getData();
                ibPhoto.setImageURI(selectedImageUri);
                btnTakePhoto.setText(getResources().getString(R.string.change_photo));
            }
        }
    }

}