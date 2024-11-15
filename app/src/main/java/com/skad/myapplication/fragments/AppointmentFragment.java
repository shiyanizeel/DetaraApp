package com.skad.myapplication.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.skad.myapplication.R;

import java.util.Calendar;


public class AppointmentFragment extends Fragment {

EditText nameEditAppoitment,emailEditAppoitment,addressEditAppoitment,countryEditAppoitment,calenderEditAppoitment,timeEditAppoitment;
ImageView calenderImg,timeImg,countryImg;
TextView virtualBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_appointment, container, false);


        nameEditAppoitment= view.findViewById(R.id.nameEditAppoitment);
        emailEditAppoitment= view.findViewById(R.id.emailEditAppoitment);
        addressEditAppoitment= view.findViewById(R.id.addresEditAppoitment);
        countryEditAppoitment= view.findViewById(R.id.countryEditAppoitment);
        calenderEditAppoitment= view.findViewById(R.id.celenderEditAppoitment);
        timeEditAppoitment= view.findViewById(R.id.timeEditAppoitment);
        virtualBtn = view.findViewById(R.id.appoitmentBtn);
        countryImg = view.findViewById(R.id.countryImage);

// ============ click AppointmentBtn ===============
        virtualBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setVirtualAppointment();
            }
        });

// ================== set country ==============
        countryImg.setOnClickListener(v -> {
            // Define a list of countries excluding India
            String[] countries = { "Australia","Austria","Belgium","Canada","Czachia","Denmark","Finland","France", "Germany","Hong Kong SAR","Ireland",
                    "Israel","Itley", "Japan",  "Malesia", "Netherlands", "New Zeland","Norway","Poland", "Portugal",
                    "Singapor", "South Korea", "Spain", "Swedan", "Swezerland", "Japan",    "United Arab Emirates",
                    "United States", "United Kingdom"};

            // Create an AlertDialog to show the list of countries
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Select a Country");
            builder.setItems(countries, (dialog, which) -> {
                // Set selected country to countryEditAppoitment
                countryEditAppoitment.setText(countries[which]);
            });

            // Show the dialog
            builder.show();
        });

//======================  set calender ==============
        calenderImg = view.findViewById(R.id.calenderImage);
        calenderImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });


//        ======================= set time ===============
        timeImg = view.findViewById(R.id.timeImage);
        timeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        return  view;
    }
    private void showDatePickerDialog() {
        // Get the current date to set the DatePicker default values
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Format the selected date (e.g., "dd/MM/yyyy")
                        @SuppressLint("DefaultLocale") String selectedDate = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);

                        // Set the selected date to the EditText
                        calenderEditAppoitment.setText(selectedDate);
                    }
                }, year, month, day);

        // Show the DatePicker dialog
        datePickerDialog.show();
    }

    // Function to show TimePickerDialog
    private void showTimePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY); // Get current hour
        int minute = calendar.get(Calendar.MINUTE); // Get current minute

        // Create the TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Format the time in HH:mm format (24-hour format)
                        @SuppressLint("DefaultLocale") String formattedTime = String.format("%02d:%02d", hourOfDay, minute);

                        // Set the formatted time to the EditText (timeEditAppoitment)
                        timeEditAppoitment.setText(formattedTime);
                    }
                }, hour, minute, true); // "true" for 24-hour format

        // Show the TimePickerDialog
        timePickerDialog.show();
    }

    public  void  setVirtualAppointment(){
        // Get text from each field
        String name = nameEditAppoitment.getText().toString();
        String email = emailEditAppoitment.getText().toString();
        String address = addressEditAppoitment.getText().toString();
        String country = countryEditAppoitment.getText().toString();
        String calendarDate = calenderEditAppoitment.getText().toString();
        String time = timeEditAppoitment.getText().toString();

        // Validation checks
        if (TextUtils.isEmpty(name)) {
            nameEditAppoitment.setError(" Name is required");
            return;
        }

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches() || !email.endsWith(".com")) {
            emailEditAppoitment.setError("Valid Email ending with .com is required");
            return;
        }





        // Prepare the email content
        String emailBody = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Address: " + address + "\n" +
                "Country: " + country + "\n" +
                "Appointment Date: " + calendarDate + "\n" +
                "Appointment Time: " + time;

        // Create an email intent
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"detaragemologisk@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Appointment Details");
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody);

        // Start email activity
        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}