package com.our.edu.newsapp.utils

import android.app.Activity
import android.content.Intent
import android.net.Uri


object IntentClass {


    fun goToLink(activity: Activity, link: String) {
        if (link.isNotBlank()) {
            val uri = Uri.parse(link)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activity.startActivity(intent)
        }
    }

}