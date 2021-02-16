package com.typesafe.training.scalatrain

import com.typesafe.training.scalatrain.TestData._
import org.scalatest.{Matchers, WordSpec}

class JourneyPlannerSpec extends WordSpec with Matchers {
  "stations" should {
    "be correctly initialized" in {
      jPlan.stations shouldEqual Set(tinsukia, dibru, moran, jorhat, ghy)
    }
  }

  "Calling trainsAt" should {
    "return the correct trains" in {
      jPlan.trainsAt(tinsukia) shouldEqual Set(nfr724, nfr726)
      jPlan.trainsAt(jorhat) shouldEqual Set(nfr724, nfr750)
    }
  }

  "Calling stopsAt" should {
    "return correct stops" in {
      jPlan.stopsAt(tinsukia) shouldEqual Set(nfr724TinsukiaTime -> nfr724, nfr726TinsukiaTime -> nfr726)
      jPlan.stopsAt(moran) shouldEqual Set(nfr724MoranTime -> nfr724, nfr726MoranTime -> nfr726, nfr750MoranTime -> nfr750)
    }
  }

  "Calling inShortTrip" should {
    "return true if 0 or 1 station in between" in {
      jPlan.isShortTrip(tinsukia, dibru) shouldBe true
      jPlan.isShortTrip(tinsukia, moran) shouldBe true
      jPlan.isShortTrip(dibru, moran) shouldBe true
      jPlan.isShortTrip(dibru, jorhat) shouldBe true
      jPlan.isShortTrip(dibru, ghy) shouldBe true
    }
    "return false if more than 1 station in between" in {
      jPlan.isShortTrip(tinsukia, jorhat) shouldBe false
      jPlan.isShortTrip(tinsukia, ghy) shouldBe false
    }
  }
}
