/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

import com.typesafe.training.scalatrain.TestData._
import org.scalatest.{Matchers, WordSpec}

import java.lang.{IllegalArgumentException => IAE}

class TrainSpec extends WordSpec with Matchers {

  "Train nfr724" should {
    "stop in Dibru" in {
      nfr724.timeAt(dibru) shouldEqual Some(nfr724DibruTime)
    }
    "not stop in Ghy" in {
      nfr724.timeAt(ghy) shouldEqual None
    }
  }

  "Train nfr726" should {
    "stop in Tinsukia" in {
      nfr726.timeAt(tinsukia) shouldEqual Some(nfr726TinsukiaTime)
    }
    "not stop in Jorhat" in {
      nfr726.timeAt(jorhat) shouldEqual None
    }
  }

  "Train nfr750" should {
    "stop in Dibru" in {
      nfr750.timeAt(dibru) shouldEqual Some(nfr750DibruTime)
    }
    "not stop in Tinsukia" in {
      nfr750.timeAt(tinsukia) shouldEqual None
    }
  }

  "Creating a Train" should {
    "throw an IllegalArgumentException for a schedule with 0 or 1 elements" in {
      an[IAE] should be thrownBy Train(InterCityExpress(724), Vector())
      an[IAE] should be thrownBy Train(InterCityExpress(724), Vector(nfr724TinsukiaTime -> tinsukia))
    }
  }

  "stations" should {
    "be initialized correctly" in {
      nfr724.stations shouldEqual Vector(tinsukia, dibru, moran, jorhat)
      nfr750.stations shouldEqual Vector(dibru, moran, jorhat, ghy)
    }
  }
}
