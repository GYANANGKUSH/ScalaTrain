/*
 * Copyright © 2012 Typesafe, Inc. All rights reserved.
 */

package com.typesafe.training.scalatrain

object TestData {

  val tinsukia: Station = Station("Tinsukia")
  val dibru: Station = Station("Dibru")
  val moran: Station = Station("Moran")
  val jorhat: Station = Station("Jorhat")
  val ghy: Station = Station("Ghy")

  val nfr724TinsukiaTime: Time = Time(8, 50)
  val nfr724DibruTime: Time = Time(10)
  val nfr724MoranTime: Time = Time(12, 10)
  val nfr724JorhatTime: Time = Time(13, 39)

  val nfr724: Train = Train(
    InterCityExpress(724),
    Vector(
      nfr724TinsukiaTime -> tinsukia,
      nfr724DibruTime -> dibru,
      nfr724MoranTime -> moran,
      nfr724JorhatTime -> jorhat
    )
  )

  val nfr726TinsukiaTime: Time = Time(7, 50)
  val nfr726DibruTime: Time = Time(9)
  val nfr726MoranTime: Time = Time(11, 10)
  val nfr726JorhatTime: Time = Time(13, 2)

  val nfr726: Train = Train(
    InterCityExpress(726),
    Vector(
      nfr726TinsukiaTime -> tinsukia,
      nfr726DibruTime -> dibru,
      nfr726MoranTime -> moran,
      nfr726JorhatTime -> ghy
    )
  )

  val nfr750DibruTime: Time = Time(10,30)
  val nfr750MoranTime: Time = Time(12)
  val nfr750JorhatTime: Time = Time(14,45)
  val nfr750GhyTime: Time = Time(20,30)

  val nfr750: Train = Train(
    InterCityExpress(750),
    Vector(
      nfr750DibruTime -> dibru,
      nfr750MoranTime -> moran,
      nfr750JorhatTime -> jorhat,
      nfr750GhyTime -> ghy
    )
  )

  val jPlan = new JourneyPlanner(Set(nfr724, nfr726, nfr750))
}
