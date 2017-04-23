package `in`.ac.iiita.go.models

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by betterclever on 4/16/2017.
 */

open class Lecture(
        @PrimaryKey open var _id: String,

        open var startTime: Long,

        open var endTime: Long,

        open var courseId: String,

        open var day: String,

        open var location: String,

        open var lectureType: String,

        open var teachersRef: List<String>

) : RealmObject()

open class Faculty(
        @PrimaryKey open var _id: String,

        open var name: String,

        open var designation: String,

        open var office: String,

        open var primaryContact: String,

        open var email: String

) : RealmObject()


open class Course(
        @PrimaryKey open var _id: String,

        open var name: String,

        open var labCredits: String,

        open var theoryCredits: String,

        open var type: String

) : RealmObject()

open class MessEvent(
        @PrimaryKey open var _id: String,

        open var startTime: Long,

        open var endTime: Long,

        open var day: String,

        open var hostelId: String,

        open var type: String,

        open var foodItems: List<String>

) : RealmObject()


