package `in`.ac.iiita.go.api

import okhttp3.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by affan on 28/4/17.
 */

interface LoginAPI {
    @GET("/from_ldap.php")
    fun checkAuth(@Query("u") username: String, @Query("p") password: String): Call
}
