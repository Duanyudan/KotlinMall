package com.example.baselibrary.data.protocol

/**
 * Created by user on 2018/3/14.
 */
data class BaseResp<out T>(val status:Int,val message:String,val data:T) {
}