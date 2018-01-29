package com.xiaochui.tradecircle.data.http;


import com.xiaochui.tradecircle.data.model.CommonNetModel;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * @author cauchy
 * @date 2017/12/1 9:19
 * @since 1.0.0
 */

public interface ApiService {

    String BASE_URL = "http://hammmer.cz1225.com:9090/restful/";

    String getVerifyCode = "user/getVerifyCode/{codeType}/{telephone}";

    /**
     * 获取手机验证码
     *
     * @param codeType  0注册，1短信登录，2修改密码,3更换手机号
     * @param telephone
     * @param no
     * @return
     */
    @FormUrlEncoded
    @POST(getVerifyCode)
    Observable<CommonNetModel> getVerifyCode(@Path("codeType") int codeType, @Path("telephone") String telephone, @Field("null") String no);

}
