package br.com.movieapp.framework.data.remote

import br.com.movieapp.BuildConfig
import br.com.movieapp.framework.util.Constant
import okhttp3.Interceptor
import okhttp3.Response

class ParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.newBuilder()
            .addQueryParameter(Constant.LANGUAGE_PARAM, Constant.LANGUAGE_VALUE)
            .addQueryParameter(Constant.API_KEY_PARAM, BuildConfig.API_KEY)
            .build()
        val newRequest = request.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }

}