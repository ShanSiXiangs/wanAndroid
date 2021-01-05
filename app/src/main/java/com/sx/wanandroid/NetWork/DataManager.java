package com.sx.wanandroid.NetWork;

import com.sx.wanandroid.DataBean.BannerBean;
import com.sx.wanandroid.DataBean.BoWenXiangMuBean;
import com.sx.wanandroid.DataBean.LoginBean;
import com.sx.wanandroid.DataBean.RegistBean;

import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DataManager {

    private static DataManager dataManager;
    private final RetrofitService retrofitService;
    private final Scheduler ioScheduler;
    private final Scheduler mainScheduler;

    public static DataManager instance() {
        if (null == dataManager)
            dataManager = new DataManager();
        return dataManager;
    }

    private DataManager() {
        ioScheduler = Schedulers.io();
        mainScheduler = AndroidSchedulers.mainThread();
        retrofitService = RetrofitHelper.Instance().getRetrofitService();
    }

    public void getRegist(String username, String password,String repassword, final RequestListener requestListener) {
        retrofitService.Regist(username, password,repassword)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<RegistBean>() {
                    @Override
                    public void onNext(RegistBean RegistBean) {
                        requestListener.succeed(RegistBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestListener.failed();
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    public void getLogin(String username, String password, final RequestListener requestListener) {
        retrofitService.Login(username, password)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        requestListener.succeed(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestListener.failed();
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    public void getBanner(final RequestListener requestListener) {
        retrofitService.Banner()
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onNext(BannerBean bannerBean) {
                        requestListener.succeed(bannerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestListener.failed();
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    public void getBoWen(int pageNum,Integer cid , final RequestListener requestListener) {
        retrofitService.BoWen(pageNum,cid)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<BoWenXiangMuBean>() {
                    @Override
                    public void onNext(BoWenXiangMuBean boWenXiangMuBean) {
                        requestListener.succeed(boWenXiangMuBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestListener.failed();
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }

    public void getXiangMu(int pageNum,Integer cid , final RequestListener requestListener) {
        retrofitService.XiangMu(pageNum,cid)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<BoWenXiangMuBean>() {
                    @Override
                    public void onNext(BoWenXiangMuBean boWenXiangMuBean) {
                        requestListener.succeed(boWenXiangMuBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestListener.failed();
                    }

                    @Override
                    public void onCompleted() {

                    }
                });
    }



    /**
     * 网络请求回调
     */
    public interface RequestListener {
        /**
         * 请求成功回调
         */
        void succeed(Object dataBean);

        /**
         * 请求失败回调
         */
        void failed();
    }
}

