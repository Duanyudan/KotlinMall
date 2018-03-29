package com.example.ordercenter.service

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.ext.convert
import com.kotlin.order.data.api.ShipAddressApi
import com.kotlin.order.data.protocol.AddShipAddressReq
import com.kotlin.order.data.protocol.DeleteShipAddressReq
import com.kotlin.order.data.protocol.EditShipAddressReq
import com.kotlin.order.data.protocol.ShipAddress
import rx.Observable
import javax.inject.Inject

/**
 * Created by user on 2018/3/23.
 */
class ShipAddressServiceImpl @Inject constructor() : ShipAddressService {
    override fun getShipAddressList(): Observable<MutableList<ShipAddress>?> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java)
                .getShipAddressList()
                .convert()
    }

    override fun editShipAddress(id: Int, shipUserName: String,
                                 shipUserMobile: String, shipAddress: String,
                                 shipIsDefault: Int): Observable<String> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java)
                .editShipAddress(EditShipAddressReq(id, shipUserName, shipUserMobile, shipAddress, shipIsDefault))
                .convert()

    }

    override fun deleteShipAddress(id: Int): Observable<String> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java)
                .deleteShipAddress(DeleteShipAddressReq(id))
                .convert()

    }

    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<String> {
        return RetrofitFactory.instance.create(ShipAddressApi::class.java)
                .addShipAddress(AddShipAddressReq(shipUserName, shipUserMobile, shipAddress))
                .convert()
    }
}