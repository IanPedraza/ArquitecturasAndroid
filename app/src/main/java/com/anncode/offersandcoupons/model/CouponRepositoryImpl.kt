package com.anncode.offersandcoupons.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CouponRepositoryImpl(): CouponRepository {

    /*
    Una lista de lista live, que pueda refrescar
    automaticamente a los demas
     */

    private var coupons = MutableLiveData<List<Coupon>>()

    //subject MutableLiveData
    //COntendra una lista de observers -> ListCoupon en este caso
    //Cuando la lista cambia genera un cambio en MutableLiveData
    //el metodo observe notificara/actualizará los cambios

    override fun getCoupons(): MutableLiveData<List<Coupon>>{
        return coupons
    }

    //TODA LA LOGICA DE CONEXIÓN
    override fun callCouponsAPI() {
        //CONTROLLER
        var couponsList: ArrayList<Coupon>? = ArrayList<Coupon>()
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getCoupons()

        call.enqueue(object : Callback<JsonObject> {
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t.stackTrace
            }

            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                val offersJsonArray = response.body()?.getAsJsonArray("offers")
                offersJsonArray?.forEach { jsonElement: JsonElement ->
                    var jsonObject = jsonElement.asJsonObject
                    var coupon = Coupon(jsonObject)
                    couponsList?.add(coupon)
                }
                //VIEW
                coupons.value = couponsList
            }

        })
        //CONTROLLER
    }

}