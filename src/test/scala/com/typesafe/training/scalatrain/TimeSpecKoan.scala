package com.typesafe.training.scalatrain

import com.typesafe.training.scalatrain.support.KoanSuite
import play.api.libs.json.Json

class TimeSpecKoan extends KoanSuite {

  koan("Calling asMinutes") {
    Time(1,30).asMinutes should be(90)//should be 90
    assert(Time(1,30).asMinutes == 90, "1hr 30min = 90min")
  }

  koan("Calling minus or -") {
    Time(1,50) minus Time(1,30) should be(20)
    Time(1,50) - Time(1,30) should be(20)//should be 20
  }

  koan("Calling val toString") {
    Time(9, 45).toString should be ("09:45")
    Time(9).toString should be ("09:00")//"09:00"
    Time(9, 1).toString should be ("09:01")
  }

  koan("Default args in Time()") {
    val t: Time = Time()
    t.hours should be (0)
    t.minutes should be (0)//0
  }

  koan("Creating a Time with hours range[0,23]") {
    an[IllegalArgumentException] should be thrownBy Time(24)
    an[IllegalArgumentException] should be thrownBy Time(-1)//Time(-1)
  }
  koan("Creating a Time with minutes range[0,59]") {
    an[IllegalArgumentException] should be thrownBy Time(0,60)
    an[IllegalArgumentException] should be thrownBy Time(0,-1)//Time(0,-1)
  }

  koan("Calling fromJson") {
    Time fromJson Json.obj("hours"->5,"minutes"->20) shouldBe Some(Time(5,20))//Time(5,20)
    Time fromJson Json.obj() shouldBe None
  }

  koan("Calling fromJson after toJson") {
    val t: Time = Time(6,40)
    (Time fromJson t.toJson) shouldBe Some(t)
  }
}
