package com.example.medicinereminder;

import android.provider.BaseColumns;

public class MedicineContract {

   public static final class MedicineEntry implements BaseColumns{

      public static final String TABLE_NAME = "MedicineInfo";
      public static final String MEDICINE_NAME_COLUMN = "MedicineName";
      public static final String MEDICINE_AMOUNT_COLUMN = "MedicineAmount";
      public static final String MEDICINE_DAY = "MedicineDay";
      public static final String MEDICINE_TIME = "MedicineTime";

   }

}
