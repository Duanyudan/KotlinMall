package com.example.ordercenter.service

import com.example.baselibrary.data.protocol.BaseResp
import com.kotlin.order.data.protocol.ShipAddress
import retrofit2.http.POST
import rx.Observable

/**
 * Created by Administrator on 2018/3/7.
 */
interface ShipAddressService {
    /*
        添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<String>

    /*
        删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<String>

    /*
        修改收货地址
     */
    fun editShipAddress(id: Int, shipUserName: String, shipUserMobile: String,
                        shipAddress: String, shipIsDefault: Int): Observable<String>

    /*
        查询收货地址列表
     */
    fun getShipAddressList(): Observable<MutableList<ShipAddress>?>
}