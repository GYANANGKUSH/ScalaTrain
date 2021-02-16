package com.typesafe.training.scalatrain

import play.api.libs.json.{JsValue, Json}

import scala.util.Try

case class Time (hours: Int = 0, minutes: Int = 0) extends Ordered[Time] {

//  TODO Verify that hours is within 0 and 23
  require(hours<=23 && hours>=0, "INPUT HOURS RANGE 0 - 23")
//  TODO Verify that minutes is within 0 and 59
  require(minutes<=59 && minutes>=0, "INPUT MINUTES RANGE 0 - 59")

//  to convert the class fields (hours and minutes) to total minutes
  val asMinutes: Int = hours * 60 + minutes

//  to find the difference between the time passed in class param. and the time passed via minus method
//  val t1 = new com.typesafe.training.scalatrain.Time(1,30)
//  t.minus(new com.typesafe.training.scalatrain.Time(1,20))
  def minus(that: Time): Int = this.asMinutes - that.asMinutes

//  making the - methods as an alias of 'minus' by delegating to 'minus'
//  val t2 = new com.typesafe.training.scalatrain.Time(1,20)
//  t1-t2
//  above method is in infix operator notation
  def -(that: Time): Int = minus(that)

  override lazy val toString: String = f"$hours%02d:$minutes%02d"

  override def compare(that: Time): Int =  this - that

  def toJson: JsValue = Json.obj("hours"->hours,"minutes"->minutes)
}

//  companion object which returns normalized values for hours and minutes
//  ie. total minutes will be converted to eqv. hours and minutes
object Time {
  def fromMinutes(minutes: Int): Time = {
    Time(minutes/60, minutes%60)
  }

//  method inside object Time so calling    > Time.fromJson(jsonValue)
  def fromJson(json: JsValue):Option[Time] = {
    val tryTime = for {
      hrs <- Try((json \ "hours").as[Int])
      mins <- Try((json \ "minutes").as[Int]).recover({case _ : Exception => 0})
//      if parsing minutes fails then the default value for minutes will be 0
    } yield Time(hrs, mins)
    tryTime.toOption
//    if success - wrap Time in Some
//    if failure - return None
  }
}