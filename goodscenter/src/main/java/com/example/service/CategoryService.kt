package com.example.service

import com.example.baselibrary.data.protocol.BaseResp
import com.example.data.protocol.Category
import rx.Observable

/**
 * Created by Administrator on 2018/3/7.
 */
interface CategoryService {
   fun getCategory(parentId:Int):Observable<MutableList<Category>>

}