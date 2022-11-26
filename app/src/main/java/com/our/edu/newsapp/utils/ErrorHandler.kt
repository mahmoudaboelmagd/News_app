package com.our.edu.newsapp.utils

import android.content.Context
import com.google.gson.GsonBuilder
import com.our.edu.newsapp.R
import com.our.edu.newsapp.dataModel.error.ErrorModel
import okhttp3.ResponseBody
import java.io.IOException

object ErrorHandler {

     /*
     this function return true
     if there is no errors in the server response
     otherwise toast the error
     */
    fun handleError(context: Context, responseCode: Int, errorBody: ResponseBody?): Boolean {

        when (responseCode) {

            ResponseCode.BAD_REQUEST -> {
                Utilities.toastyError(
                    context,
                    context.getString(R.string.try_again_with_correct_data)
                )
                return false
            }

            ResponseCode.NOT_ALLOWED -> {
                Utilities.toastyError(context, context.getString(R.string.not_allowed))
                return false
            }

            ResponseCode.NOT_ACCEPTABLE -> {
                Utilities.toastyError(context, context.getString(R.string.not_acceptable))
                return false
            }

            ResponseCode.UNAUTHORIZED -> {
                Utilities.toastyError(context, context.getString(R.string.unauthorized))
                return false
            }

            ResponseCode.NOT_FOUND -> {
                Utilities.toastyError(context, context.getString(R.string.not_found))
                return false
            }

            ResponseCode.INTERNAL_SERVER -> {
                Utilities.toastyError(context, context.getString(R.string.server_error))
                return false
            }

            ResponseCode.UNPROCESSABLE_ENTITY -> {
                val gson = GsonBuilder().create()
                try {
                    val errorModel = gson.fromJson(
                        errorBody!!.string(),
                        ErrorModel::class.java
                    )
                    try {
                        var errorMessage = ""
                        for (element in errorModel.errors){
                            errorMessage += element.detail+"\n"
                        }

                        Utilities.toastyError(context, errorMessage)

                        return false
                    } catch (e: Exception) {
                        Utilities.toastyError(context, e.message.toString())
                        e.printStackTrace()
                        return false
                    }

                } catch (e: IOException) {
                    Utilities.toastyError(context, e.message.toString())
                    e.message
                    return false
                }
            }

        }

        return true

    }

}