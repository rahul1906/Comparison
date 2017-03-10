package com.hm

import akka.actor.Actor

import scala.concurrent.ExecutionContext

/**
  * Created by rahul on 2/3/17.
  */
class MyServiceActor extends Actor with MyService{

    // the HttpService trait defines only one abstract member, which
    // connects the services environment to the enclosing actor or test
    def actorRefFactory = context

    // this actor only runs our route, but you could add
    // other things here, like request stream processing
    // or timeout handling
    def receive = runRoute(myRoute)
    implicit def dispatcher: ExecutionContext = ServerActorSystem.ec

}
