package com.example.service

import com.example.baselibrary.data.net.RetrofitFactory
import com.example.baselibrary.ext.convert
import com.example.data.api.CategoryApi
import com.example.data.protocol.Category
import com.example.data.protocol.GetCategoryReq
import rx.Observable
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class CategoryServiceImpl @Inject constructor() : CategoryService {
    override fun getCategory(parentId: Int): Observable<MutableList<Category>> {
        return RetrofitFactory.instance.create(CategoryApi::class.java)
                .getCategory(GetCategoryReq(parentId)).convert()
    }


}