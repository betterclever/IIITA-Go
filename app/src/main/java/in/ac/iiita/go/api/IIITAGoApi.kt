package `in`.ac.iiita.go.api

import `in`.ac.iiita.go.models.*
import android.content.Context
import io.realm.Realm
import io.realm.RealmList
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.info
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/*
 * Created by betterclever on 4/26/2017.
 */

interface IIITAGoApi {

    @GET("Faculty")
    fun getAllFaculty(): Call<List<Faculty>>

    @GET("LostItems")
    fun getLostItems(@Query("where") name: String): Call<List<LostItem>>

    @GET("FoundItems")
    fun getFoundItems(@Query("where") name: String): Call<List<FoundItem>>

}

class GoService(val context: Context) : AnkoLogger {

    private val iiitaGoApi: IIITAGoApi
    private val client = OkHttpClient()

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://172.31.1.111:3000/api/v1/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
        info(retrofit.baseUrl().toString())
        iiitaGoApi = retrofit.create(IIITAGoApi::class.java)
    }

    fun storeData() {

        saveMessSchedule()

        doAsync {

            Realm.init(context)
            val realm = Realm.getDefaultInstance()

            val faculty = iiitaGoApi.getAllFaculty().execute().body()
            info(iiitaGoApi.getAllFaculty().execute().raw())
            info("Here")


            realm.beginTransaction()
            realm.copyToRealmOrUpdate(faculty)
            realm.commitTransaction()
            saveCourses()

        }
    }

    fun saveCourses() {

        doAsync {

            Realm.init(context)
            val realm = Realm.getDefaultInstance()

            val request = Request.Builder()
                    .url("http://172.31.1.111:3000/api/v1/Courses")
                    .build()

            val response = client.newCall(request).execute()
            val rstr = response.body().string()
            val jsonArray = JSONArray(rstr)

            val courses = ArrayList<Course>()

            for (i in 0..jsonArray.length() - 1) {
                val jsonObject = jsonArray.getJSONObject(i)

                val array = jsonObject["teachers"] as JSONArray
                val list = ArrayList<String>()
                for (i in 0..array.length() - 1) {
                    list.add(array.getString(i))
                }

                val ar = list.toTypedArray()

                val results = realm.where(Faculty::class.java).
                        `in`("id", ar).findAll()

                val teachers = RealmList<Faculty>()
                teachers.addAll(results)

                courses.add(Course(
                        id = jsonObject["id"] as String?,
                        name = jsonObject["name"] as String?,
                        theory_credit = jsonObject["theory_credit"] as Int,
                        lab_credit = jsonObject["lab_credit"] as Int,
                        type = jsonObject["type"] as String?,
                        teachers = teachers
                ))
            }

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(courses)
            realm.commitTransaction()

            saveLectures()
        }
    }

    fun saveLectures() {

        doAsync {

            Realm.init(context)
            val realm = Realm.getDefaultInstance()

            val request = Request.Builder()
                    .url("http://172.31.1.111:3000/api/v1/Lectures")
                    .build()

            val response = client.newCall(request).execute()
            val rstr = response.body().string()
            val jsonArray = JSONArray(rstr)

            val lectures = ArrayList<Lecture>()

            for (i in 0..jsonArray.length() - 1) {
                val jsonObject = jsonArray.getJSONObject(i)

                val array = jsonObject["teachersRef"] as JSONArray
                val list = ArrayList<String>()
                for (i in 0..array.length() - 1) {
                    list.add(array.getString(i))
                }

                val ar = list.toTypedArray()

                val results = realm.where(Faculty::class.java).
                        `in`("id", ar).findAll()

                val teachers = RealmList<Faculty>()
                teachers.addAll(results)

                lectures.add(Lecture(
                        id = jsonObject["id"] as String?,
                        startTime = (jsonObject["startTime"] as Int).toLong(),
                        endTime = (jsonObject["endTime"] as Int).toLong(),
                        courseId = jsonObject["courseId"] as String,
                        day = jsonObject["day"] as String?,
                        location = jsonObject["location"] as String?,
                        section = jsonObject["section"] as String?,
                        lectureType = jsonObject["lectureType"] as String?,
                        teachersRef = teachers
                ))
            }

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(lectures)
            realm.commitTransaction()
        }
    }

    fun saveMessSchedule() {

        doAsync {

            Realm.init(context)
            val realm = Realm.getDefaultInstance()

            val request = Request.Builder()
                    .url("http://172.31.1.111:3000/api/v1/MessSchedule")
                    .build()

            val response = client.newCall(request).execute()
            val responseString = response.body().string()
            val jsonArray = JSONArray(responseString)

            val messSchedule = ArrayList<MessEvent>()


            for (i in 0..jsonArray.length() - 1) {
                val jsonObject = jsonArray.getJSONObject(i)

                val foodItems = RealmList<RealmString>()
                val foodJson = jsonObject.getJSONArray("foodItems")

                for (j in 0..foodJson.length()-1) {
                    val str = foodJson.getString(j)
                    foodItems.add(RealmString(str))
                }

                messSchedule.add(MessEvent(
                        id = jsonObject["id"] as String?,
                        day = jsonObject["day"] as String?,
                        type = jsonObject["type"] as String?,
                        startTime = (jsonObject["startTime"] as Int).toLong(),
                        endTime = (jsonObject["endTime"] as Int).toLong(),
                        foodItems = foodItems,
                        hostelNum = jsonObject["hostelNum"] as String?
                ))
            }

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(messSchedule)
            realm.commitTransaction()
        }
    }



}