package com.anncode.offersandcoupons.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData

class CouponObservable: BaseObservable() {
    //Heredar de la calse BaseObservable de Android X

    private var couponRepository: CouponRepository = CouponRepositoryImpl()

    //Repositorio
    fun callCoupons(){
        couponRepository.callCouponsAPI()
    }

    //ViewModel
    fun getCoupons(): MutableLiveData<List<Coupon>>{
        return couponRepository.getCoupons()
    }
}