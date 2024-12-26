package com.example.note_app

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity

class ShareService (context : Any){

    fun shareTextToWhatsapp(text : String) {

        fun startActivity(sendIntent: Intent) {

        }

        fun shareTextToWhatsApp(message: String): Int {
            val packageManager = text
            try {
                // Create an Intent to share the text via WhatsApp
                val sendIntent = Intent(Intent.ACTION_SEND)
                sendIntent.type = "text/plain"
                sendIntent.putExtra(Intent.EXTRA_TEXT, message)

                // Check if WhatsApp is installed
                val whatsappPackage = "com.whatsapp"
                val whatsappIntent = packageManager.get(shareTextToWhatsApp(text))

                // If WhatsApp is installed, open it, else show a toast or error
                sendIntent.setPackage(whatsappPackage)
                startActivity(sendIntent)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return shareTextToWhatsApp(text)
        }

    }

}