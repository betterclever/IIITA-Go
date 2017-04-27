package `in`.ac.iiita.go.api

import `in`.ac.iiita.go.models.Course
import `in`.ac.iiita.go.models.Faculty
import `in`.ac.iiita.go.models.Lecture
import android.content.Context
import io.realm.Realm
import io.realm.RealmList
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

/**
 * Created by betterclever on 4/26/2017.
 */

interface IIITAGoApi {

    @GET("Courses")
    fun getAllCourses() : Call<List<Course>>

    @GET("Faculty")
    fun getAllFaculty() : Call<List<Faculty>>

    @GET("Lectures")
    fun getAllLectures() : Call<List<Lecture>>
}

class GoService : AnkoLogger {
    private val iiitaGoApi : IIITAGoApi
    private val client  = OkHttpClient()

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://172.26.46.140:3000/api/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        info(retrofit.baseUrl().toString())
        iiitaGoApi = retrofit.create(IIITAGoApi::class.java)
    }

    fun storeData(context: Context){
        Realm.init(context)
        val realm = Realm.getDefaultInstance()

        doAsync {
            val faculty =  iiitaGoApi.getAllFaculty().execute().body()
            info(iiitaGoApi.getAllFaculty().execute().raw())
            info("Here")

            uiThread {
                realm.beginTransaction()
                realm.copyToRealmOrUpdate(faculty)
                realm.commitTransaction()
            }

            //val courses = iiitaGoApi.getAllCourses().execute().body()
            info(iiitaGoApi.getAllCourses().execute().raw())

        }
    }

    fun saveCourses(context: Context){

        doAsync {

            Realm.init(context)
            val realm = Realm.getDefaultInstance()

            val request = Request.Builder()
                    .url("http://172.26.46.140:3000/api/v1/Courses")
                    .build()

            val response = client.newCall(request).execute()
            val jsonArray = JSONArray(response.body())

            val courses = ArrayList<Course>()

            for (i in 0..jsonArray.length()-1){
                val ob = jsonArray.getJSONObject(i)

                val teachers = realm.where(Faculty::class.java).
                        `in`("id",ob["teachers"] as Array<String>).findAll() as RealmList<Faculty>


                courses.add(Course(
                        id = ob["id"] as String?,
                        name = ob["name"] as String?,
                        theory_credit = ob["theoryCredit"] as Int,
                        lab_credit = ob["labCredit"] as Int,
                        type = ob["type"] as String?,
                        teachers = teachers
                ))
            }

            realm.copyToRealmOrUpdate(courses)
        }
    }
}