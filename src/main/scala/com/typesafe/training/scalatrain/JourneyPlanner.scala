package com.typesafe.training.scalatrain

class JourneyPlanner(trains: Set[Train]) {
  val stations: Set[Station] = trains.flatMap(tra => tra.stations)
//  'stations' will contain all the stations of all trains

  def trainsAt(station: Station): Set[Train]={trains.filter(train => train.stations contains station)}
//  method to find out those stations of all trains which are scheduled to be in this given station(in method)
//  'filter' is used here

  def stopsAt(station: Station): Set[(Time,Train)] = {
    for{
      train <- trains
      time <- train.timeAt(station)
    } yield(time, train)
//    for {
//      train <- trains.filter(tr => tr.stations contains station)
//      time <- train.schedule.find(stop => stop._2 == station).map(ret => ret._1)
//    } yield (time, train)

//    for ((time, train) <- Set
//      (trains.filter(tr => tr.stations contains station), trains.schedule.find(stop => stop._2 == station).map(ret => ret._1))
//    ) yield (train, time)
  }
//  method to find the time and train which stops at the given station

  def isShortTrip(from: Station, to: Station): Boolean = {
    trains.exists(
      train => train.stations.dropWhile(statn => statn != from) match{
        case `from` +: `to` +: _      => true
        case `from` +: _ +: `to` +: _ => true
        case _                        => false
      }
    )
  }
}


//val t1 = Train(BavarianRegional(1234), Seq((Time(2,20),Station("Tsk")),(Time(3,20),Station("Dbr")),(Time(4,20),Station("Sib"))))
//val t2 = Train(RegionalExpress(123), Seq((Time(1,20),Station("Tsk")),(Time(2,20),Station("Dbr")),(Time(3,10),Station("Sib"))))
//val j1 = new JourneyPlanner(Set(t1,t2))

//testing trainsAt(Station) -
//println(j1.trainsAt(Station("Tsk")))

//checking val stations -
//println(j1.stations)

//testing isShortTrip(Station, Station)
//println(j1.isShortTrip(Station("Dbr"), Station("Sib")))
//??

//testing stopsAt(Station)