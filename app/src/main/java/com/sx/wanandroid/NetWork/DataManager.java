package com.sx.wanandroid.NetWork;

import com.sx.wanandroid.DataBean.BannerBean;
import com.sx.wanandroid.DataBean.BoWenXiangMuBean;
import com.sx.wanandroid.DataBean.GongZhongHaoBean;
import com.sx.wanandroid.DataBean.GongZhongSomeOneBean;
import com.sx.wanandroid.DataBean.GuangChangBean;
import com.sx.wanandroid.DataBean.LoginBean;
import com.sx.wanandroid.DataBean.MeiTuBean;
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

    public void getRegist(String username, String password, String repassword, final RequestListener requestListener) {
        retrofitService.Regist(username, password, repassword)
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

    public void getBoWen(int pageNum, Integer cid, final RequestListener requestListener) {
        retrofitService.BoWen(pageNum, cid)
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

    public void getXiangMu(int pageNum, Integer cid, final RequestListener requestListener) {
        retrofitService.XiangMu(pageNum, cid)
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

    public void getGongZhongHao(final RequestListener requestListener) {
        retrofitService.GongZhongHao()
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<GongZhongHaoBean>() {
                    @Override
                    public void onNext(GongZhongHaoBean gongZhongHaoBean) {
                        requestListener.succeed(gongZhongHaoBean);
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

    public void getGongZhongSomeOne(int id,int pageNum,final RequestListener requestListener) {
        retrofitService.GongZhongSomeOne(id,pageNum)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<GongZhongSomeOneBean>() {
                    @Override
                    public void onNext(GongZhongSomeOneBean gongZhongSomeOneBean) {
                        requestListener.succeed(gongZhongSomeOneBean);
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

    public void getGuangChang(int pageNum,final RequestListener requestListener) {
        retrofitService.GuangChang(pageNum)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<GuangChangBean>() {
                    @Override
                    public void onNext(GuangChangBean guangChangBean) {
                        requestListener.succeed(guangChangBean);
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

    public void getMeiTu(int pageNum,final RequestListener requestListener) {
        RetrofitHelper.baseUrl = "";
        retrofitService.MeiTu(pageNum)
                .subscribeOn(ioScheduler)
                .observeOn(mainScheduler)
                .subscribe(new Observer<MeiTuBean>() {
                    @Override
                    public void onNext(MeiTuBean meiTuBean) {
                        RetrofitHelper.baseUrl = "https://www.wanandroid.com";
                        requestListener.succeed(meiTuBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        RetrofitHelper.baseUrl = "https://www.wanandroid.com";
                        requestListener.failed();
                    }

                    @Override
                    public void onCompleted() {
                        RetrofitHelper.baseUrl = "https://www.wanandroid.com";
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

