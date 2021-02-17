package com.typesafe.training.scalatrain

import com.typesafe.training.scalatrain.TestData.{dibru, ghy, jPlan, jorhat, moran, nfr724, nfr724MoranTime, nfr724TinsukiaTime, nfr726, nfr726MoranTime, nfr726TinsukiaTime, nfr750, nfr750MoranTime, tinsukia}
import com.typesafe.training.scalatrain.support.BlankValues.__
import com.typesafe.training.scalatrain.support.KoanSuite

class JourneyPlannerSpecKoan extends KoanSuite{
  koan("stations to be initialized correctly"){
    jPlan.stations shouldEqual Set(tinsukia, dibru, moran, jorhat, ghy)
  }

  koan("Calling trainsAt must return correct trains"){
    jPlan.trainsAt(tinsukia) shouldEqual Set(nfr724, nfr726)
    jPlan.trainsAt(jorhat) shouldEqual Set(nfr724, nfr750)
  }

  koan("Calling stopsAt must return correct stops"){
    jPlan.stopsAt(tinsukia) shouldEqual Set(nfr724TinsukiaTime -> nfr724, nfr726TinsukiaTime -> nfr726)
    jPlan.stopsAt(moran) shouldEqual Set(nfr724MoranTime -> nfr724, nfr726MoranTime -> nfr726, nfr750MoranTime -> nfr750)
  }

  koan("Calling inShortTrip must return 'true' if 0 or 1 station in between"){
    jPlan.isShortTrip(tinsukia, dibru) shouldBe __//true
    jPlan.isShortTrip(tinsukia, moran) shouldBe true
    jPlan.isShortTrip(dibru, moran) shouldBe true
    jPlan.isShortTrip(dibru, jorhat) shouldBe true
    jPlan.isShortTrip(dibru, ghy) shouldBe true
  }

  koan("Calling inShortTrip must return false if more than 1 station in between"){
    jPlan.isShortTrip(tinsukia, jorhat) shouldBe false
    jPlan.isShortTrip(tinsukia, ghy) shouldBe false
  }
}
