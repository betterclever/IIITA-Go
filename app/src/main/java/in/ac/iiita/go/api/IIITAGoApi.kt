package `in`.ac.iiita.go.api

import `in`.ac.iiita.go.models.Course
import `in`.ac.iiita.go.models.Faculty
import `in`.ac.iiita.go.models.Lecture
import android.content.Context
import io.realm.Realm
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * Created by betterclever on 4/26/2017.
 */

interface IIITAGoApi {

    @GET("/Courses")
    fun getAllCourses() : Call<List<Course>>

    @GET("/Faculty")
    fun getAllFaculty() : Call<List<Faculty>>

    @GET("Lectures")
    fun getAllLectures() : Call<List<Lecture>>
}

class GoService : AnkoLogger {
    private val iiitaGoApi : IIITAGoApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://172.26.46.140:3000/api/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        iiitaGoApi = retrofit.create(IIITAGoApi::class.java)
    }

    fun storeData(context: Context){
        Realm.init(context)
        val realm = Realm.getDefaultInstance()

        doAsync {
            val courses =  iiitaGoApi.getAllCourses().execute().body()
            info("Here")
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(courses)
            realm.commitTransaction()
        }
    }
}