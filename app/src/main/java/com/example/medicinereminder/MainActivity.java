package com.example.medicinereminder;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

   private RelativeLayout DialogLayout,MainLayout;
   private TextView QuantityText;
   int quantity = 0;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      ImageView BackImage;
      FloatingActionButton FloatingButton;

      QuantityText = findViewById(R.id.quantity);
      QuantityText.setText(String.valueOf(quantity));

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

      quantity = quantity-1;
      displayQuantity(quantity);

   }


   public void add(View v){

      quantity = quantity+1;
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
}
