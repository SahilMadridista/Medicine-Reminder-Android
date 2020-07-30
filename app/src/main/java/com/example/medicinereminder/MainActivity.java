package com.example.medicinereminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

   private RelativeLayout DialogLayout,MainLayout;
   private TextView QuantityText,TimeText;
   private EditText MedicineNameET;
   int quantity = 0;
   private CheckBox SundayBox,MondayBox,TuesdayBox,WednesdayBox,ThursdayBox,FridayBox,SaturdayBox;
   private boolean[] dayOfWeekList = new boolean[7];

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      ImageView BackImage;
      FloatingActionButton FloatingButton;
      Button ClearButton;
      final Button TimePickerButton,SaveMedicineForAlarm;

      MedicineNameET = findViewById(R.id.medicine_name_et);
      TimeText = findViewById(R.id.time_text);
      TimePickerButton = findViewById(R.id.time_btn);
      SaveMedicineForAlarm = findViewById(R.id.save_medicine);

      SundayBox = findViewById(R.id.sundaybox);
      MondayBox = findViewById(R.id.mondaybox);
      TuesdayBox = findViewById(R.id.tuesdaybox);
      WednesdayBox = findViewById(R.id.wednesdaybox);
      ThursdayBox = findViewById(R.id.thursdaybox);
      FridayBox = findViewById(R.id.fridaybox);
      SaturdayBox = findViewById(R.id.saturdaybox);


      SaveMedicineForAlarm.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            SaveMedicine();
         }
      });

      TimePickerButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

            DialogFragment timePicker = new TimePickerFragment();
            timePicker.show(getSupportFragmentManager(), "time picker");

         }
      });

      QuantityText = findViewById(R.id.quantity);
      QuantityText.setText(String.valueOf(quantity));

      ClearButton = findViewById(R.id.cancel_btn);
      ClearButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            MedicineNameET.setText("");
            TimeText.setText("");
            SundayBox.setChecked(false);
            MondayBox.setChecked(false);
            TuesdayBox.setChecked(false);
            WednesdayBox.setChecked(false);
            ThursdayBox.setChecked(false);
            FridayBox.setChecked(false);
            SaturdayBox.setChecked(false);
            int zero = 0;
            QuantityText.setText(String.valueOf(zero));

            Toast.makeText(getApplicationContext(),"Data cleared !",Toast.LENGTH_LONG).show();

         }
      });

      DialogLayout = findViewById(R.id.dialog_layout);
      MainLayout = findViewById(R.id.main_layout);
      FloatingButton = findViewById(R.id.fab);
      BackImage = findViewById(R.id.back_image);

      FloatingButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            MedicineDialog();
         }
      });

      BackImage.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            CloseMedicineDialog();
         }
      });

   }

   private void SaveMedicine(){

      if(MedicineNameET.getText().toString().trim().isEmpty()){

         MedicineNameET.setError("Medicine name can't be empty");
         MedicineNameET.requestFocus();
         return;

      }

      if(TimeText.getText().toString().isEmpty()){

         Toast.makeText(getApplicationContext(),"Please choose a time",Toast.LENGTH_LONG).show();
         return;

      }

      if(!(SundayBox.isChecked() || MondayBox.isChecked() || TuesdayBox.isChecked() || WednesdayBox.isChecked() ||
              ThursdayBox.isChecked() || FridayBox.isChecked() || SaturdayBox.isChecked())){

         Toast.makeText(getApplicationContext(),"Please select at least a day of week",Toast.LENGTH_LONG).show();
         return;

      }

      int q = Integer.parseInt(QuantityText.getText().toString());

      if(q<0){

         Toast.makeText(getApplicationContext(),"Quantity can't be negative",Toast.LENGTH_LONG).show();
         return;

      }

      if(q==0){

         Toast.makeText(getApplicationContext(),"Quantity can't be zero",Toast.LENGTH_LONG).show();
         return;

      }


      Toast.makeText(getApplicationContext(),"BINGO ! Working fine.",Toast.LENGTH_LONG).show();




   }

   private void MedicineDialog() {

      MainLayout.setVisibility(View.GONE);
      DialogLayout.setVisibility(View.VISIBLE);

      YoYo.with(Techniques.SlideInUp)
              .repeat(0)
              .duration(500)
              .playOn(DialogLayout);

   }

   private void CloseMedicineDialog() {

      YoYo.with(Techniques.SlideOutDown)
              .duration(300)
              .repeat(0)
              .playOn(DialogLayout);

      new Handler().postDelayed(new Runnable() {
         @Override
         public void run() {
            DialogLayout.setVisibility(View.GONE);
         }
      },300);

      MainLayout.setVisibility(View.VISIBLE);

   }

   public void subtract(View v){

      int number = Integer.parseInt(QuantityText.getText().toString());
      quantity = number - 1;
      displayQuantity(quantity);

   }


   public void add(View v){

      int number = Integer.parseInt(QuantityText.getText().toString());
      quantity = number + 1;
      displayQuantity(quantity);

   }

   public void displayQuantity(int quantity) {

      QuantityText.setText(String.valueOf(quantity));

   }

   @Override
   public void onBackPressed() {

      if(DialogLayout.getVisibility() == View.VISIBLE){

         YoYo.with(Techniques.SlideOutDown)
                 .duration(300)
                 .repeat(0)
                 .playOn(DialogLayout);

         new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               DialogLayout.setVisibility(View.GONE);
            }
         },300);

         MainLayout.setVisibility(View.VISIBLE);

      }

      else{

         AlertDialog.Builder builder = new AlertDialog.Builder(this);
         builder.setMessage("Do you really want to exit ?").setCancelable(false)
                 .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                       MainActivity.super.onBackPressed();
                       finish();

                    }
                 })

                 .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                       dialogInterface.cancel();

                    }
                 });

         AlertDialog alertDialog = builder.create();
         alertDialog.show();

      }

   }

   @Override
   public void onTimeSet(TimePicker timePicker, int i, int i1) {

      TimeText.setText(i+":"+i1);

   }

}
