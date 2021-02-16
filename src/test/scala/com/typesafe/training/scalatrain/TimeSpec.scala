package com.typesafe.training.scalatrain

import org.scalatest.{Matchers, WordSpec}
import play.api.libs.json.Json

import java.lang.{IllegalArgumentException => IAE}

class TimeSpec extends WordSpec with Matchers {
  "asMinutes" should {
    "be initialized correctly" in {
      Time(1,30).asMinutes shouldEqual 90
    }
  }

  "Calling minus or -" should {
    "return the difference in minutes" in {
      Time(1,50) minus Time(1,30) shouldEqual 20
//      Time(1,30) minus Time(1,50) shouldEqual 20
      Time(1,50) - Time(1,30) shouldEqual 20
    }
  }

  "Calling val toString" should {
    "return proper formatted string representation" in {
      Time(9, 45).toString shouldEqual "09:45"
      Time(9).toString shouldEqual "09:00"
      Time(9, 1).toString shouldEqual "09:01"
    }
  }

  "Calling Ordered operators - compare" should {
    "work as expected" in {
      Time() < Time(1) shouldBe true
      Time() >= Time(1) shouldBe false
    }
  }

  "Default args in Time() for hours and minutes" should {
    "be equal to 0" in {
      val t: Time = Time()
      t.hours shouldEqual 0
      t.minutes shouldEqual 0
    }
  }

  "Creating a Time" should {
    "throw an IllegalArgumentException for hours not in range[0,23]" in {
      an[IAE] should be thrownBy Time(24)
      an[IAE] should be thrownBy Time(-1)
    }
    "throw an IllegalArgumentException for minutes not in range[0,59]" in {
      an[IAE] should be thrownBy Time(0,60)
      an[IAE] should be thrownBy Time(0,-1)
    }
  }

  "Calling fromJson" should {
    "return Some wrapping a properly initialized Time for a valid JsonObject" in {
      (Time fromJson Json.obj("hours" -> 5, "minutes" -> 20)) shouldEqual Some(Time(5,20))
    }
    "return None for an invalid json" in {
      Time fromJson Json.obj() shouldBe None
    }
  }

  "Calling fromJson after toJson" should {
    "return Some wrapping the original Time" in {
      val t: Time = Time(6,40)
      (Time fromJson t.toJson) shouldEqual Some(t)
    }
  }
}
