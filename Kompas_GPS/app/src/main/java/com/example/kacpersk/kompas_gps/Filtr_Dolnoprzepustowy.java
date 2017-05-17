package com.example.kacpersk.kompas_gps;



public class Filtr_Dolnoprzepustowy {
    private  static  final  float   ALFA =0.2f;
    private Filtr_Dolnoprzepustowy() {}
        /**
         * Filtr ma dwa parametry wartosci wejsciowych
         * @param input jako macierz typu zmiennoprzecinkowych danych wejsciowych
         * @param output jako macierz wartości zmiennoprzecinkowych przeliczonych
         *
         */
        public static  float [] filtr (float[] input, float[] output)
        {
            if (output== null)
                return input;

            for ( int i=0; i< input.length; i++) {
                output[i]=output[i]+ALFA*(input[i]-output[i]);
            }
            return output;
        }

}
