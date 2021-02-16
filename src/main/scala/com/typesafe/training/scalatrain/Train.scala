package com.typesafe.training.scalatrain

case class Train (info: TrainInfo, schedule: Seq[(Time, Station)]) {
//  TODO Verify that schedule is strictly increasing in time
  require(schedule.size>=2, "Schedule must contain minimum 2 elements")

//  test condition for checking timeSeq (it is invalid)
//  val tra = Train(RegionalExpress(123), Seq((Time(1,20),Station("Tsk")),(Time(2,20),Station("Dbr")),(Time(2,10),Station("Sib"))))
  val tim: Seq[Time] = schedule.map(x => x._1)
  def timeSeq: Boolean = {
      for (i <- 0 to tim.length - 2) {
        if (tim(i + 1) - tim(i) <= 0) {
          return false
        }
      }
    true
  }
  require(timeSeq,"Invalid time sequence! Needs to be increasing in time order!")

//  testing after including the schedule: Seq() param and creating a val of Train
//  val n = Train(1,"inter",Seq((Time(1,2),Station("Ghy"))))

//  testing - creating instance of 'Train' after including 'TrainInfo' with case class of diff trains
//  val t1 = Train(InterCityExpress(13,true),Seq((Time(4,20),Station("Tsk")),(Time(16,20),Station("Ghy"))))


  val stations: Seq[Station] = schedule.map(x => x._2)
//  this will create a seq of stations from the schedule parameter's second field

  def timeAt(stat: Station): Option[Time] = {
    schedule.find(p=> p._2 == stat).map(found=>found._1)
  }
//  this method will return some Time if the Train stops at the station, else return None
}

case class Station(name: String)

//sealed class restricts the class or trait from being extended by its subclasses outside the source file
sealed abstract class TrainInfo {
  def number: Int
}

//  case classes storing the info about various trains
//  InterCityExpress has an extra param hasWifi
case class InterCityExpress(number: Int, hasWifi: Boolean = false) extends TrainInfo
case class RegionalExpress(number: Int) extends TrainInfo
case class BavarianRegional(number: Int) extends TrainInfo

//using this sealed class and making case classes by extending it is called ADT(Alzebraic Data type is a composite type formed by a fixed set of possible values implementing a standard interface)
//useful for pattern matching