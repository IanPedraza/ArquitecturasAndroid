package com.anncode.offersandcoupons.model

import com.anncode.offersandcoupons.presenter.CouponPresenter

class CouponRepositoryImpl(var couponPresenter: CouponPresenter): CouponRepository {

    //TODA LA LOGICA DE CONEXIÓN
    override fun getCouponsAPI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //couponPresenter.showCoupons()
    }

}