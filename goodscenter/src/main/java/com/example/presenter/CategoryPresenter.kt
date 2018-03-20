package com.example.presenter

import com.example.baselibrary.ext.excute
import com.example.baselibrary.presenter.BasePresenter
import com.example.baselibrary.rx.BaseSubscriber
import com.example.data.protocol.Category
import com.example.service.CategoryServiceImpl
import com.example.view.CategoryView
import javax.inject.Inject

/**
 * Created by Administrator on 2018/3/7.
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryView>() {
    @Inject
    lateinit var categoryService: CategoryServiceImpl

   fun getCategory(parentId: Int){
       if (!checkNetWork()){
           return
       }
       mView.showLoading()
       categoryService.getCategory(parentId)
               .excute(object : BaseSubscriber<MutableList<Category>>(mView){
                   override fun onNext(t: MutableList<Category>) {
                       mView.onGetCategoryResult(t)
                   }
               },lifecycleProvider)
   }
}