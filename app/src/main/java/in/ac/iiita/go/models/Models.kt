package `in`.ac.iiita.go.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Created by betterclever on 4/16/2017.
 */

open class Lecture() : RealmObject() {

    @PrimaryKey open var id: String? = null

    open var startTime: Long = 0

    open var endTime: Long = 0

    open var courseId: String? = null

    open var day: String? = null

    open var location: String? = null

    open var lectureType: String? = null

    open var section: String? = null

    open var teachersRef: RealmList<Faculty>? = null

    open var notificationEnabled: Boolean = false

    constructor(id: String?, startTime: Long, endTime: Long, section: String?, courseId: String?, day: String?, location: String?, lectureType: String?, teachersRef: RealmList<Faculty>?) : this() {
        this.id = id
        this.startTime = startTime
        this.endTime = endTime
        this.courseId = courseId
        this.day = day
        this.section = section
        this.location = location
        this.lectureType = lectureType
        this.teachersRef = teachersRef
    }
}

open class Faculty() : RealmObject() {

    @PrimaryKey open var id: String? = null

    open var name: String? = null

    open var designation: String? = null

    open var office: String? = null

    open var primaryContact: String? = null

    open var email: String? = null

    constructor(id: String?, name: String?, designation: String?, office: String?, primaryContact: String?, email: String?) : this() {
        this.id = id
        this.name = name
        this.designation = designation
        this.office = office
        this.primaryContact = primaryContact
        this.email = email
    }
}


open class Course() : RealmObject() {
    @PrimaryKey open var id: String? = null

    open var name: String? = null

    open var theory_credit: Int = 0

    open var lab_credit: Int = 0

    open var type: String? = null

    open var teachers: RealmList<Faculty>? = null

    constructor(id: String?, name: String?, theory_credit: Int, lab_credit: Int, type: String?, teachers: RealmList<Faculty>?) : this() {
        this.id = id
        this.name = name
        this.theory_credit = theory_credit
        this.lab_credit = lab_credit
        this.type = type
        this.teachers = teachers
    }
}

open class MessEvent() : RealmObject() {
    @PrimaryKey open var id: String? = null

    open var day: String? = null

    open var type: String? = null

    open var startTime: Long? = 0

    open var endTime: Long? = 0

    open var hostelNum: String? = null

    open var foodItems: RealmList<RealmString>? = null

    open var notificationEnabled: Boolean = false

    constructor(id: String?, day: String?, type: String?, startTime: Long?, endTime: Long?, hostelNum: String?, foodItems: RealmList<RealmString>?) : this() {
        this.id = id
        this.day = day
        this.type = type
        this.startTime = startTime
        this.endTime = endTime
        this.hostelNum = hostelNum
        this.foodItems = foodItems
    }

    companion object {
    }
}

open class LibraryBook() : RealmObject() {
    @PrimaryKey open var accNo: String? = null

    open var name: String? = null

    open var issueDate: Date? = null

    open var additionalNotes: String? = null

    open var reminderEnabled = false

    constructor(accNo: String?, name: String?, issueDate: Date?, additionalNotes: String?, reminderEnabled: Boolean) : this() {
        this.accNo = accNo
        this.name = name
        this.issueDate = issueDate
        this.additionalNotes = additionalNotes
        this.reminderEnabled = reminderEnabled
    }
}

open class RealmString() : RealmObject() {
    open var stringVal: String? = null

    constructor(stringVal: String?) : this() {
        this.stringVal = stringVal
    }
}

class LostItem(
        val id: String,
        val name: String,
        val description: String,
        val ownerName: String,
        val extraDetail: String,
        val ownerPhone: Long
)

class FoundItem(
        val id: String,
        val name: String,
        val description: String,
        val founderName: String,
        val extraDetail: String,
        val founderPhone: Long
)

class Notification(
        val title: String,
        val body: String
)