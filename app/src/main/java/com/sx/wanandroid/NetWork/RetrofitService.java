package com.sx.wanandroid.NetWork;

import com.sx.wanandroid.DataBean.BannerBean;
import com.sx.wanandroid.DataBean.BoWenXiangMuBean;
import com.sx.wanandroid.DataBean.LoginBean;
import com.sx.wanandroid.DataBean.RegistBean;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {
    /**
     * 注册接口
     *
     * @param username   用户名
     * @param password   密码
     * @param repassword 确认密码
     * @return
     */
    @POST("user/register")
    Observable<RegistBean> Regist(@Query("username") String username, @Query("password") String password, @Query("repassword") String repassword);

    /**
     * 登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @POST("user/login")
    Observable<LoginBean> Login(@Query("username") String username, @Query("password") String password);

    /**
     * Banner轮播图接口
     *
     * @return
     */
    @GET("banner/json")
    Observable<BannerBean> Banner();

    /**
     * 知识体系下的文章列表接口
     * @param pageNumum 当前请求的页数 从0开始
     * @param cid  文章类型ID (cid不传 代表热门文章)
     * @return
     */
    @GET("article/list/{pageNum}/json")
    Observable<BoWenXiangMuBean> BoWen(@Path("pageNum") int pageNumum, @Query("cid") Integer cid);

    /**
     * 项目列表接口
     * @param pageNumum 当前请求的页数 从1开始
     * @param cid 项目类型ID (cid不传 代表热门项目)
     * @return
     */
    @GET("project/list/{pageNum}/json")
    Observable<BoWenXiangMuBean> XiangMu(@Path("pageNum") int pageNumum, @Query("cid") Integer cid);

}
